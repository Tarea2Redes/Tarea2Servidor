
package Domain;

/**
 *
 * @author Estudiante
 */
public class Contenido {
    
   private int isbn;
   private String contenido; 

    public Contenido(int isbn, String contenido) {
        this.isbn = isbn;
        this.contenido = contenido;
    }

    public Contenido() {
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
   
   
   
   
}