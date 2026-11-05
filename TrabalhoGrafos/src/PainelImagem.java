import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;
import javax.swing.JPanel;

/** Painel que mostra uma imagem ajustada ao tamanho e converte cliques para coordenadas da imagem. */
public class PainelImagem extends JPanel {

    private final boolean fundoXadrez;
    private BufferedImage imagem;
    private int marcadorX = -1;
    private int marcadorY = -1;

    // posição e escala em que a imagem foi desenhada
    private double escala = 1;
    private int origemX;
    private int origemY;

    public PainelImagem(boolean fundoXadrez) {
        this.fundoXadrez = fundoXadrez;
        setBackground(new Color(245, 243, 238));
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
        repaint();
    }

    public void setMarcador(int x, int y) {
        marcadorX = x;
        marcadorY = y;
        repaint();
    }

    /** Chama a ação com o (x, y) do clique em pixels da imagem. */
    public void aoClicar(BiConsumer<Integer, Integer> acao) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (imagem == null) return;
                int x = (int) ((e.getX() - origemX) / escala);
                int y = (int) ((e.getY() - origemY) / escala);
                if (x >= 0 && y >= 0 && x < imagem.getWidth() && y < imagem.getHeight()) {
                    acao.accept(x, y);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        if (imagem == null) return;
        Graphics2D g = (Graphics2D) g0;
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        escala = Math.min((double) getWidth() / imagem.getWidth(), (double) getHeight() / imagem.getHeight());
        int w = (int) (imagem.getWidth() * escala);
        int h = (int) (imagem.getHeight() * escala);
        origemX = (getWidth() - w) / 2;
        origemY = (getHeight() - h) / 2;

        if (fundoXadrez) {
            int lado = 12;
            for (int y = 0; y < h; y += lado) {
                for (int x = 0; x < w; x += lado) {
                    boolean escuro = ((x / lado) + (y / lado)) % 2 == 0;
                    g.setColor(escuro ? new Color(220, 216, 207) : Color.WHITE);
                    g.fillRect(origemX + x, origemY + y, Math.min(lado, w - x), Math.min(lado, h - y));
                }
            }
        }
        g.drawImage(imagem, origemX, origemY, w, h, null);

        if (marcadorX >= 0) {
            int mx = origemX + (int) (marcadorX * escala);
            int my = origemY + (int) (marcadorY * escala);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            g.fillOval(mx - 9, my - 9, 18, 18);
            g.setColor(new Color(217, 84, 44));
            g.fillOval(mx - 6, my - 6, 12, 12);
        }
    }
}
