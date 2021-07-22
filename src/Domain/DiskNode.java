
package Domain;

import Data.FragmentoLibroData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Estudiante
 */
public class DiskNode {
    
    private int number;
    private int active;
    private  FragmentoLibroData data;

    public DiskNode(int number, int active) {
        this.number = number;
        this.active = active;
        try {
            contruirRaF();
        } catch (IOException ex) {
            Logger.getLogger(DiskNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FragmentoLibroData Storage() {
        return data;
    }

    public int getNumber() {
        return number;
    }

    public int getActive() {
        return active;
    }

    private void contruirRaF() throws IOException {
        
        data = new FragmentoLibroData(number);

    }
    
    
}
