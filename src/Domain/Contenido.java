package Domain;

import java.io.Serializable;

public class Contenido implements Serializable {

    private int isbn;
    private String contenido;
    static final long serialVersionUID = 2L;

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

    @Override
    public String toString() {
        return "contenido=" + contenido;
    }

}
