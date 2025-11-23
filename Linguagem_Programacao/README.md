# Linguagem de Programação – Entregas E1 e E2

Projeto acadêmico da disciplina Linguagem de Programação (Profª Adriana) dividido em duas entregas:

- **E1**: Primeira entrega. Persistência simples em **CSV** ou memória, foco em modelagem OO, JavaFX e interação básica.
- **E2**: Segunda entrega. Funcionalidades espelhadas da E1, porém migradas para **SQLite** para demonstrar persistência em banco relacional. Inclui DAOs, criação de tabelas e operações CRUD básicas.

Java 24 + JavaFX 24.0.1 + Maven.

## Sumário
1. [Tecnologias e Pré‑requisitos](#tecnologias-e-pré-requisitos)
2. [Como Executar (Perfis Maven)](#como-executar-perfis-maven)
3. [Estrutura de Diretórios](#estrutura-de-diretórios)
4. [Pacotes E1 – Descrição e Classes](#pacotes-e1)
5. [Pacotes E2 – Descrição e Classes](#pacotes-e2)
6. [Diferenças entre E1 e E2](#diferenças-e1-vs-e2)
7. [Banco de Dados SQLite – Tabelas](#banco-de-dados-sqlite)
8. [Testes](#testes)
9. [Melhorias Futuras / Ideias](#melhorias-futuras)

---
## Tecnologias e Pré-requisitos
- JDK 24 (ou compatível). 
- Maven 3.9+.
- JavaFX (gerenciado via dependências Maven).
- SQLite (driver `org.xerial:sqlite-jdbc`).

> Observação: Warnings de *native access* podem aparecer ao iniciar JavaFX. Para suprimir, executar com: `--enable-native-access=javafx.graphics --enable-native-access=javafx.media` (já configurado parcialmente no plugin).

---
## Como Executar (Perfis Maven)
Cada aplicação JavaFX tem um **profile** Maven. Exemplo para rodar:

```powershell
# Cadastro CSV (E1)
mvn -Pcadastro-db javafx:run   # (caso exista perfil específico para versão DB ou ajuste mainClass)

# Cálculo de Média (E1)
mvn -Pcalculo-media javafx:run

# Módulo Vídeo (E1)
mvn -Pvideo javafx:run

# Imagens versão DB (E2)
mvn -Pimagens-db javafx:run

# Doodle versão DB
mvn -Pdoodle-db javafx:run

# Música versão DB (Dezesseis)
mvn -Pmusica-db javafx:run

# Video versão DB
mvn -Pvideo-db javafx:run

# Cálculo de Média versão DB
mvn -Pcalculomedia-db javafx:run
```

> Se algum profile não existir para a versão CSV original (ex.: `cadastro`), ajustar `pom.xml` ou criar um novo perfil apontando para a classe principal adequada.

---
## Estrutura de Diretórios
```
src/main/java/fatec/gov/br/e1/  # Entrega 1 (CSV/memória)
src/main/java/fatec/gov/br/e2/  # Entrega 2 (SQLite / DAO)
src/main/resources/ei/ui/       # FXML das interfaces
src/main/resources/ei/assets/   # Imagens / vídeo
```

---
## Pacotes E1
### 1. `e1.cadastrocsv`
- **Pessoa**: Modelo simples com atributos (ex.: nome, idade, etc.).
- **CsvEscritaLeitura**: Responsável pela serialização e desserialização dos registros em arquivo `.csv`.
- **PessoaController**: Lógica da interface JavaFX (carregar, validar, gravar em CSV, atualizar tabela).
- **App / Main**: Inicialização da aplicação (carrega FXML).

### 2. `e1.calculomedia`
- **CalculoMedia**: Contém a lógica de cálculo (ex.: médias de notas). Valida faixas de valores.
- **ResultadoMedia**: Modelo que encapsula resultado (média, situação, timestamp).
- **CalculoMediaController**: Integra campos da UI, valida entradas, monta `ResultadoMedia` e atualiza exibição.
- **CalculoMediaApp**: Classe JavaFX para iniciar a tela.

### 3. `e1.imagens`
#### `e1.imagens.doodle`
- **Alien**: Simula ações (invade, defende, abduz); altera estado de `GamerRobot`.
- **GamerRobot**: Representa jogador; pode atacar alien e melhorar armas (upgrade de nível).
- **Spaceship / Main (se presente)**: Entrada do módulo / utilitário complementar.
- **DoodleController**: Interação UI: leitura de campos, ações de jogo, gravação/leitura CSV (`aliens.csv`, `robots.csv`).

#### `e1.imagens.vikmuniz`
- **Camera**: Atributos (modelo, lente, resolução) + método `capturarImagem`.
- **Fotografo**: Dados do fotógrafo, método `tirarFoto`.
- **Microfone**: Características do microfone, método `capturarAudio`.
- **Pessoa**: Participante da corrida (nome, força, posição) método `atacar`.
- **Trilhos**: Estrutura ferroviária (bitola, comprimento) método `suportarTrem`.
- **Vencedor**: Resultado de corrida (pontuação, tempo) método `declararVitoria`.
- **VikMunizController**: Coleta atributos, escreve CSVs (`papparazzi.csv`, `trackbrawl.csv`), abre popups.
- **PopUpPapparazziController / PopUpTrackBrawlController**: Leitura dos CSVs e exibição em tabelas + resultados de métodos.

### 4. `e1.musica`
- **Pessoa**: Perfil (nome, idade, reputação) e ações (dirigir, tocar, info).
- **Carro**: Modelo, cor, motor; métodos `roncarEmPega`, `pilotarCarro`.
- **Violao**: Cordas, madeira, timbre; métodos `produzirSomParaConquistar`, `pegarViolao`.
- **CurvaDoDiabo**: Localização, perigosidade; métodos `provocarAcidente`, `acidentePega`.
- **DezesseisController**: Agrupa inputs, grava linha no CSV `dezesseis.csv` com atributos + saídas dos métodos e exibe tabelas.

### 5. `e1.video`
- **Chef**: Nome, experiência, especialidade; prepara ingredientes, cozinha e prova alimento.
- **Ingrediente**: Nome, quantidade, unidade; métodos de preparo (cortar, temperar, limpar).
- **Utensilio**: Tipo, material e tamanho; métodos (preparar, aquecer, mexer).
- **VideoController**: Integração com JavaFX MediaView para reproduzir vídeo; valida campos, registra ação em CSV (`cozinha.csv`) e atualiza três tabelas (Chef, Ingrediente, Utensílio) + log de preparo.

---
## Pacotes E2
> Replicam conceitos de E1, substituindo CSV por **SQLite**. Cada módulo cria suas tabelas no primeiro uso.

### 1. `e2.cadastrocsv`
- **AppDbFx**: Inicializa versão banco do cadastro.
- **PessoaDbFxController** (nome conforme implementado): Usa DAO embutido para CRUD SQLite (inserção/listagem pessoas). Substitui gravação CSV por tabela `pessoa` (estrutura adaptada).
- **Tabela**: Criada dinamicamente (ex.: `pessoas.db` ou arquivo definido no código dentro do pacote) para armazenar atributos.

### 2. `e2.calculomedia`
- **CalculoMediaDbApp**: Starter JavaFX.
- **ResultadoMedia** (reaproveitado) + **ResultadoMediaDAO**: Insere resultados em tabela `medias` (`medias.db`).
- **CalculoMediaDbController**: Valida notas, calcula média, insere, recarrega, limpa banco.

### 3. `e2.imagens`
- **VikMunizDbApp / DoodleDbApp**: Entradas JavaFX para os dois submódulos.
- **ImagensDatabase**: Cria `vikmuniz.db` (tabelas `paparazzi`, `trackbrawl`).
- **DoodleDatabase / AlienRobotDAO**: Cria `doodle.db` para `alien` e `robot`.
- **PaparazziRecordDAO / TrackBrawlRecordDAO**: Persistem mensagens e atributos de câmera/fotógrafo/microfone e pessoa/trilhos/vencedor.
- **VikMunizDbController**: Reaproveita entrada de atributos e grava no banco. Popups DB (`PopUpPapparazziDbController`, `PopUpTrackBrawlDbController`) leem mensagens direto das tabelas.
- **DoodleDbController**: Insere alien e robot, recarrega tabelas, mostra status, limpa campos.

### 4. `e2.musica`
- **MusicaDatabase**: Cria `musica.db` com tabelas: `pessoa`, `carro`, `violao`, `curva`, `metodo_log`.
- **MusicaDAO**: CRUD genérico + listagem + exportação CSV + limpeza.
- **DezesseisDbController**: Converte fluxo antigo (CSV) para banco: grava dados e histórico, carrega tabelas do BD, valida idade > 0 e cordas > 0, limpa banco e exporta CSV.
- **DezesseisDbApp**: Starter do módulo.

### 5. `e2.video`
- **VideoDatabase**: Cria `video.db` com tabelas `chef`, `ingrediente`, `utensilio`, `log`.
- **VideoDAO**: Inserção de registros + log e limpeza total.
- **VideoDbController**: Captura inputs, valida números positivos, executa lógica de preparo/cozimento, grava em banco e registra log (exibido na interface). Botão para limpar banco.
- **VideoDbApp**: Inicializa aplicação DB.

---
## Diferenças E1 vs E2
| Aspecto              | E1 (CSV/Memória)                               | E2 (SQLite)                                                                  |
|----------------------|------------------------------------------------|-------------------------------------------------------------------------------|
| Persistência         | Arquivos `.csv` simples                        | Bancos `.db` (SQLite)                                                         |
| Acesso               | Leitura/escrita linha a linha                  | PreparedStatements / CRUD estruturado                                         |
| Escalabilidade       | Limitada (concatenação de linhas)              | Melhor controle de dados, consultas, limpeza e futuras expansões              |
| Reuso de lógica      | Controllers focados em UI e CSV                | Separação em DAO + Controllers                                                |
| Validações extras    | Básicas                                        | Verificação numérica (ex.: idade > 0, cordas > 0, tamanho > 0)                |
| Exportação           | CSV original                                   | CSV gerado a partir de tabelas (ex.: Música)                                  |
| Limpeza              | Apagar arquivo manual                          | Botões de limpeza de tabelas                                                  |

---
## Banco de Dados SQLite
Resumo das principais tabelas:

- **musica.db**: `pessoa(nome, idade, reputacao)`, `carro(modelo, cor, motor)`, `violao(cordas, madeira, timbre)`, `curva(localizacao, perigosidade, nome)`, `metodo_log(pessoa_msg, carro_msg, violao_msg, curva_msg)`.
- **vikmuniz.db**: `paparazzi(modelo, lente, resolucao, marca, comp_cabo, sensibilidade, nome_fotografo, experiencia, camera_msg, fotografo_msg, microfone_msg)`, `trackbrawl(pessoa_nome, pessoa_forca, pessoa_posicao, trilho_bitola, trilho_comprimento, trilho_material, vencedor_nome, vencedor_pontuacao, vencedor_tempo, pessoa_msg, trilho_msg, vencedor_msg)`.
- **doodle.db**: `alien(tipo, forca, vida)`, `robot(nivel, vida, pontos)`.
- **video.db**: `chef(nome, experiencia, especialidade, criado_em)`, `ingrediente(nome, quantidade, unidade)`, `utensilio(tipo, material, tamanho)`, `log(mensagem, ts)`.
- **medias.db**: `medias(id, nota1, nota2, nota3, media, situacao, ts)` (estrutura conforme implementação do DAO).

> Todos os bancos são arquivos SQLite criados dentro da árvore `src/main/java/...` (poderiam ser movidos para um diretório externo como `data/` para evitar versionamento do binário).

---
## Testes
Testes JUnit presentes para alguns modelos em `src/test/java/fatec/gov/br/e1/...` (ex.: música e vídeo). Para expandir:
- Criar testes DAO para garantir inserção/listagem/limpeza (E2).
- Testar validações (valores negativos ou campos vazios).

Execução de testes:
```powershell
mvn test
```

---
## Melhorias Futuras
- Normalização de relacionamentos (ex.: tabelas de junção entre chef – utensílio – ingrediente).
- Migração dos arquivos `.db` para pasta `data/` fora de `src`.
- Adição de perfis separados para versões CSV caso desejado em paralelo aos perfis DB.
- Internacionalização (i18n) de rótulos da UI.
- Logs unificados em uma abstração (ex.: SLF4J + appender de banco).
- Testes automatizados para todos DAOs e controladores críticos.
- Exportação genérica (qualquer tabela → CSV) via utilitário comum.

---
## Licença / Uso
Projeto acadêmico para fins educacionais na disciplina Linguagem de Programação. Uso livre para estudo.



