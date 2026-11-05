import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Aplicação: abre uma imagem, o usuário clica no objeto e o programa
 * gera um sticker (PNG com fundo transparente).
 *
 * Uso com janela:      java -cp out App
 * Uso por terminal:    java -cp out App entrada.jpg x y saida.png [bfs]
 */
public class App extends JFrame {

    private static final String CORTE = "Corte mínimo (Edmonds-Karp)";
    private static final String BFS = "BFS (flood fill)";

    private final PainelImagem painelOriginal = new PainelImagem(false);
    private final PainelImagem painelSticker = new PainelImagem(true);
    private final JComboBox<String> metodo = new JComboBox<>(new String[] {CORTE, BFS});
    private final JSlider limiar = new JSlider(10, 150, 60);
    private final JButton salvar = new JButton("Salvar sticker");
    private final JLabel status = new JLabel("Abra uma imagem e clique no objeto.");

    private BufferedImage imagem;
    private BufferedImage sticker;
    private int ultimoX = -1;
    private int ultimoY = -1;

    public App() {
        super("Sticker por corte mínimo — ECM306");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton abrir = new JButton("Abrir imagem");
        abrir.addActionListener(e -> abrirImagem());
        salvar.setEnabled(false);
        salvar.addActionListener(e -> salvarSticker());
        metodo.addActionListener(e -> {
            limiar.setEnabled(BFS.equals(metodo.getSelectedItem()));
            refazer();
        });
        limiar.setEnabled(false);
        limiar.addChangeListener(e -> {
            if (!limiar.getValueIsAdjusting()) refazer();
        });

        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        barra.add(abrir);
        barra.add(new JLabel("Método:"));
        barra.add(metodo);
        barra.add(new JLabel("Limiar da BFS:"));
        barra.add(limiar);
        barra.add(salvar);

        JPanel imagens = new JPanel(new GridLayout(1, 2, 12, 0));
        imagens.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        imagens.add(comTitulo("Original (clique no objeto)", painelOriginal));
        imagens.add(comTitulo("Sticker", painelSticker));

        status.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        add(barra, BorderLayout.NORTH);
        add(imagens, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        painelOriginal.aoClicar((x, y) -> {
            ultimoX = x;
            ultimoY = y;
            segmentar();
        });

        setPreferredSize(new Dimension(1200, 700));
        pack();
        setLocationRelativeTo(null);
    }

    private static JPanel comTitulo(String titulo, JPanel conteudo) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.add(new JLabel(titulo), BorderLayout.NORTH);
        p.add(conteudo, BorderLayout.CENTER);
        conteudo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        return p;
    }

    private void abrirImagem() {
        JFileChooser escolha = new JFileChooser(new File("."));
        escolha.setFileFilter(new FileNameExtensionFilter("Imagens", "png", "jpg", "jpeg", "bmp", "gif"));
        if (escolha.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
        try {
            BufferedImage lida = ImageIO.read(escolha.getSelectedFile());
            if (lida == null) throw new IOException("formato não suportado");
            imagem = lida;
            sticker = null;
            ultimoX = -1;
            painelOriginal.setImagem(imagem);
            painelOriginal.setMarcador(-1, -1);
            painelSticker.setImagem(null);
            salvar.setEnabled(false);
            status.setText("Imagem " + imagem.getWidth() + " × " + imagem.getHeight() + ". Clique no objeto.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível abrir: " + ex.getMessage());
        }
    }

    private void refazer() {
        if (imagem != null && ultimoX >= 0) segmentar();
    }

    private void segmentar() {
        painelOriginal.setMarcador(ultimoX, ultimoY);
        boolean usarBfs = BFS.equals(metodo.getSelectedItem());
        int valorLimiar = limiar.getValue();
        int x = ultimoX;
        int y = ultimoY;
        status.setText("Calculando...");

        new SwingWorker<Object[], Void>() {
            @Override
            protected Object[] doInBackground() {
                Segmentador.Resultado r = usarBfs
                        ? Segmentador.bfs(imagem, x, y, valorLimiar)
                        : Segmentador.corteMinimo(imagem, x, y);
                return new Object[] {r, Segmentador.gerarSticker(imagem, r.mascara())};
            }

            @Override
            protected void done() {
                try {
                    Object[] saida = get();
                    Segmentador.Resultado r = (Segmentador.Resultado) saida[0];
                    sticker = (BufferedImage) saida[1];
                    painelSticker.setImagem(sticker);
                    salvar.setEnabled(true);
                    status.setText(descrever(r));
                } catch (Exception ex) {
                    status.setText("Erro: " + ex.getMessage());
                }
            }
        }.execute();
    }

    private void salvarSticker() {
        JFileChooser escolha = new JFileChooser(new File("."));
        escolha.setSelectedFile(new File("sticker.png"));
        if (escolha.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        File arquivo = escolha.getSelectedFile();
        if (!arquivo.getName().toLowerCase(Locale.ROOT).endsWith(".png")) {
            arquivo = new File(arquivo.getParentFile(), arquivo.getName() + ".png");
        }
        try {
            ImageIO.write(sticker, "png", arquivo);
            status.setText("Salvo em " + arquivo.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível salvar: " + ex.getMessage());
        }
    }

    static String descrever(Segmentador.Resultado r) {
        int total = r.mascara().length * r.mascara()[0].length;
        String texto = String.format(Locale.ROOT, "%s · grafo com %,d vértices e %,d arestas · objeto: %d%% dos pixels · %d ms",
                r.metodo(), r.vertices(), r.arestas(), 100 * r.pixelsObjeto() / total, r.milissegundos());
        if (r.fluxo() > 0) texto += String.format(Locale.ROOT, " · fluxo máximo = %,d", r.fluxo());
        return texto;
    }

    // ------------------------------------------------------------------

    public static void main(String[] args) throws IOException {
        if (args.length >= 4) {
            executarNoTerminal(args);
            return;
        }
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }

    private static void executarNoTerminal(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(new File(args[0]));
        if (img == null) throw new IOException("Não foi possível ler " + args[0]);
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        boolean usarBfs = args.length >= 5 && args[4].equalsIgnoreCase("bfs");
        Segmentador.Resultado r = usarBfs ? Segmentador.bfs(img, x, y, 60) : Segmentador.corteMinimo(img, x, y);
        ImageIO.write(Segmentador.gerarSticker(img, r.mascara()), "png", new File(args[3]));
        System.out.println(descrever(r));
    }
}
