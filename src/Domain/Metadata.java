package Domain;

import java.io.Serializable;

public class Metadata implements Serializable {

    private String titulo;
    private String autor;
    private String editorial;
    private String genero;
    private String paginas;
    private int isbn;
    static final long serialVersionUID = 3L;

    public Metadata() {
    }

    public Metadata(String titulo, String autor, String editorial, String genero, String paginas, int isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.genero = genero;
        this.paginas = paginas;
        this.isbn = isbn;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
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

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String fecha) {
        this.genero = fecha;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    @Override
    public String toString() {
        return "Metadata{" + "isbn=" + isbn + "titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", genero=" + genero + ", paginas=" + paginas + '}';
    }

}
