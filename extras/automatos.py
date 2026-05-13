# Projeto com Expressões regulares - Dom Casmurro

# Amanda Bialer 24.01621-7
# Ruy Monteiro 24.00846-0


import re
import urllib.request
from collections import Counter

# Parte 1 – Preparação

URL = "https://www.gutenberg.org/cache/epub/55752/pg55752.txt"

print(f"\n[1] Baixando o texto")

req = urllib.request.Request(
    URL,
    headers={"User-Agent": "Mozilla/5.0 (compatible; DomCasmurroAnalysis/1.0)"}
)

import ssl

try:
    with urllib.request.urlopen(req) as response:
        raw_bytes = response.read()
except urllib.error.URLError as e:
    if "CERTIFICATE_VERIFY_FAILED" in str(e) or "SSL" in str(e):
        ctx = ssl.create_default_context()
        ctx.check_hostname = False
        ctx.verify_mode = ssl.CERT_NONE
        with urllib.request.urlopen(req, context=ctx) as response:
            raw_bytes = response.read()
    else:
        raise

try:
    texto = raw_bytes.decode("utf-8")
except UnicodeDecodeError:
    texto = raw_bytes.decode("latin-1")

# Se o cabeçalho do arquivo declarar Latin-1, re-decodifica corretamente.
enc_match = re.search(r"Character set encoding:\s*(\S+)", texto, re.IGNORECASE)
if enc_match and "latin" in enc_match.group(1).lower():
    texto = raw_bytes.decode("latin-1")

print(f" Total de caracteres: {len(texto):,}")

inicio = -1
for marcador in ["CAPÍTULO I", "CAP\u00cdTULO I", "CAPITULO I", "Dom Casmurro", "DOM CASMURRO"]:
    pos = texto.find(marcador)
    if pos != -1:
        inicio = pos
        break
if inicio == -1:
    inicio = 0  # fallback: usa texto completo

fim = texto.rfind("End of the Project Gutenberg")
texto_livro = texto[inicio:fim] if fim != -1 else texto[inicio:]

print(f"[2] Texto do livro carregado em variável (sem metadados Gutenberg).")
print(f"    Tamanho do corpus: {len(texto_livro):,} caracteres")
# Diagnóstico: mostra os primeiros 120 chars para confirmar que o texto foi isolado.
trecho = texto_livro[:120].replace("\n", " ").strip()
print(f"    Início: {trecho!r}")

# Parte 2 – Atividades

# Contagem de personagens
print("\n" + "=" * 65)
print("Contagem de personagens")

def contar_personagem(texto: str, nome: str) -> int:
    """Conta ocorrências case-insensitive de um nome no texto."""
    return len(re.findall(re.escape(nome), texto, flags=re.IGNORECASE))

personagens = ["Capitu", "Bentinho", "Escobar"]
contagens   = {p: contar_personagem(texto_livro, p) for p in personagens}

print("\nOcorrências de cada personagem:")
for nome, qtd in contagens.items():
    barra = "█" * (qtd // 10)
    print(f"  {nome:<12}: {qtd:>4}  {barra}")

mais_citado  = max(contagens, key=contagens.get)
menos_citado = min(contagens, key=contagens.get)

print(f"""
    '{mais_citado}' é o personagem mais citado ({contagens[mais_citado]} vezes).
    Apesar de Dom Casmurro ser narrado em 1ª pessoa por Bentinho, Capitu aparece com frequência comparável, sendo o centro do romance.
    'Capitu' ({contagens['Capitu']} vezes): protagonista feminina célebre pelos
    "olhos de ressaca". Mesmo sendo o eixo da suspeita de traição, sua voz
    é sempre mediada pelo narrador pouco confiável.
    'Escobar' ({contagens['Escobar']} vezes): presença menor em termos numéricos,
    mas narrativamente decisiva — é o pivô do ciúme de Bentinho e o
    catalisador do desfecho trágico.
""")

# Extração de diálogos

print("2.2 Extração de diálogos")

dialogos = re.findall(r"--[^\n]+", texto_livro)

print(f"\nTotal de falas iniciadas por '--': {len(dialogos):,}")
print("\nExemplos de falas extraídas:")
for i, fala in enumerate(dialogos[:5], 1):
    trecho = fala.strip()
    if len(trecho) > 110:
        trecho = trecho[:107] + "..."
    print(f"\n  [{i}] {trecho}")

# Estilo narrativo 

print("2.3 Estilo narrativo ")

reticencias   = len(re.findall(r"\.\.\.", texto_livro))
interrogacoes = len(re.findall(r"\?",     texto_livro))
exclamacoes   = len(re.findall(r"!",      texto_livro))

recursos = {
    "Reticências (...)": reticencias,
    "Interrogações (?)": interrogacoes,
    "Exclamações (!)":   exclamacoes,
}

print("\nUso de recursos estilísticos:")
for recurso, qtd in sorted(recursos.items(), key=lambda x: -x[1]):
    barra = "█" * (qtd // 5)
    print(f"  {recurso:<22}: {qtd:>5}  {barra}")

mais_usado = max(recursos, key=recursos.get)
print(f"""
  '{mais_usado}' é o recurso mais empregado ({recursos[mais_usado]} ocorrências).
  
    As interrogações refletem o estado permanente de dúvida de Bentinho; as exclamações são mais raras, coerentes com o tom melancólico e contido da narrativa.
""")

# Padrões lexicais

print("2.4 Padrões lexicais")

palavras = re.findall(r"\b[a-zA-ZÀ-ú]+\b", texto_livro)

freq_ao = Counter(p.lower() for p in palavras if p.lower().endswith("ão"))
top_ao  = freq_ao.most_common(10)

print("\n10 palavras mais frequentes terminadas em 'ão':")
for i, (p, n) in enumerate(top_ao, 1):
    print(f"  {i:>2}. {p:<20} ({n} ocorrências)")

freq_des = Counter(p.lower() for p in palavras if p.lower().startswith("des"))
top_des  = freq_des.most_common(10)

print("\n10 palavras mais frequentes iniciadas por 'des':")
for i, (p, n) in enumerate(top_des, 1):
    print(f"  {i:>2}. {p:<20} ({n} ocorrências)")

# Estrutura do livro 

print("\n 2.5 Estrutura do livro")

padrao_capitulo = re.compile(
    r"^(CAP[IÍI]TULO\s+[IVXLCDM]+\.?|[IVXLCDM]{1,10}\.)\s*$",
    re.MULTILINE | re.IGNORECASE,
)

partes = padrao_capitulo.split(texto_livro)

capitulos: list[tuple[str, int]] = []
i = 1
while i < len(partes) - 1:
    titulo    = partes[i].strip()
    conteudo  = partes[i + 1] if i + 1 < len(partes) else ""
    n_palavras = len(re.findall(r"\b[a-zA-ZÀ-ú]+\b", conteudo))
    capitulos.append((titulo, n_palavras))
    i += 2

total_capitulos  = len(capitulos)

if total_capitulos == 0:
    print(" Nenhum capítulo detectado.")
    print("  O texto pode não ter sido isolado corretamente.")
    print("  Verifique o campo 'Início' impresso na Parte 1.")
    raise SystemExit(1)

total_palavras   = sum(n for _, n in capitulos)
media_palavras   = total_palavras / total_capitulos
cap_mais_longo   = max(capitulos, key=lambda x: x[1])
cap_mais_curto   = min(capitulos, key=lambda x: x[1])

print(f"\nPalavras por capítulo (todos):")
print(f"  {'Capítulo':<35} {'Palavras':>8}")
for titulo, n in capitulos:
    marcador = "  MAIS LONGO" if titulo == cap_mais_longo[0] else ""
    print(f"  {titulo:<35} {n:>8,}{marcador}")
    
print(f""" \n\n 
      FIM DA ANÁLISE
""")
