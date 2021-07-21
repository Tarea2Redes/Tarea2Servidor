
package Domain;

import java.io.File;
import java.io.Serializable;


public class Libro implements Serializable{
    
  
    private Metadata metadata;
    private Contenido contenido;

    public Libro(Metadata metadata, Contenido contenido) {
        this.metadata = metadata;
        this.contenido = contenido;
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

    
    
    
    
}
