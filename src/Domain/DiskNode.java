
package Domain;

import Data.FragmentoLibroData;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Estudiante
 */
public class DiskNode {
    
    private RandomAccessFile raf; // contiene objetos Fragmento Libro
    private int number;
    private int active;

    public DiskNode(int number, int active) {
        this.raf = raf;
        this.number = number;
        this.active = active;
        try {
            contruirRaF();
        } catch (IOException ex) {
            Logger.getLogger(DiskNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RandomAccessFile getRaf() {
        return raf;
    }

    public int getNumber() {
        return number;
    }

    public int getActive() {
        return active;
    }

    private void contruirRaF() throws IOException {
        
        
        FragmentoLibroData d = new FragmentoLibroData(number);
        this.raf = d.getFile(); 

    }
    
    
}
