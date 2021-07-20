package servidor;

import GUI.Window;

/**
 *
 * @author Estudiante
 */
public class Servidor {

    public static void main(String[] args) {
        
        ControllerNode cN = new ControllerNode();
        cN.start();
        
        Window w = new Window();
        w.setVisible(true);
        
    } 
}

/*

To serialize:

byte[] data = SerializationUtils.serialize(yourObject);


To deserialize:

Libro libro = SerializationUtils.deserialize(data);

*/