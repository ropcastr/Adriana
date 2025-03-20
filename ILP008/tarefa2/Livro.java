public class Livro {
    private String titulo;
    private String autor;
    private int numeroPaginas;

    public Livro(String titulo, String autor, int numeroPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public void setNumeroPaginas(int numeroPaginas) { this.numeroPaginas = numeroPaginas; }

    public void abrir() {
        System.out.println("O livro " + titulo + " foi aberto!");
    }

    public void fechar() {
        System.out.println("O livro " + titulo + " foi fechado!");
    }

    public void mudarAutor(String novoAutor) {
        this.autor = novoAutor;
        System.out.println("O autor do livro " + titulo + " agora é " + novoAutor + "!");
    }
}