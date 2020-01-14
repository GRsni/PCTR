public class Libro {
    private String contenido;
    private String titulo;
    private String autor;

    public Libro(String contenido, String titulo, String autor) {
        this.autor = autor;
        this.contenido = contenido;
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}