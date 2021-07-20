
package Domain;

import java.io.RandomAccessFile;

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
    
    
    
    
}
