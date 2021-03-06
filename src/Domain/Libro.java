package Domain;

import java.io.Serializable;

public class Libro implements Serializable {

    private Metadata metadata;
    private Contenido contenido;
    static final long serialVersionUID = 1L;

    public Libro(Metadata metadata, Contenido contenido) {
        this.metadata = metadata;
        this.contenido = contenido;
    }

    public Libro() {

    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Libro{" + "metadata=" + metadata.toString() + ", contenido=" + contenido.toString() + '}';
    }

}
