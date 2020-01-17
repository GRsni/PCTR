
/**
 * Clase Libro. Modela una entidad almacenada en el servidor SLibros
 * 
 * @author Santiago Jesús Mas Peña
 * @version 14/01/20
 */
public class Libro {
    private String contenido;
    private String titulo;
    private String autor;

    /**
     * Contructor de clase.
     * 
     * @param contenido
     * @param titulo
     * @param autor
     */
    public Libro(String contenido, String titulo, String autor) {
        this.autor = autor;
        this.contenido = contenido;
        this.titulo = titulo;
    }

    /**
     * Metodo observador.
     * 
     * @return String Devuelve el contenido del libro
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Metodo modificador del contenido del libro
     * 
     * @param contenido Contenido del libro para guardar
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Metodo observador.
     * 
     * @return String Devuelve el titulo del libro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Metodo observador.
     * 
     * @return String Devuelve el autor del libro
     */
    public String getAutor() {
        return autor;
    }
}