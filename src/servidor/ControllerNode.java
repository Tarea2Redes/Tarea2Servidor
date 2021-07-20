
package servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.swing.JOptionPane;

/**
 *
 * @author Estudiante
 */
public class ControllerNode extends Thread {

    public ControllerNode() {

    }

    @Override
    public void run() {

        System.out.println("controller node is running... listen in port: 69");

        try {
            int puerto = 69;

            DatagramSocket socketUDP = new DatagramSocket(puerto);

            byte[] buffer = new byte[1000];
            
            while (true) {

                DatagramPacket datagramPacketReceived = new DatagramPacket(buffer, buffer.length);//envio de un  paquete 
                socketUDP.receive(datagramPacketReceived);
                
                System.out.println("solicitud recibida!");

                // trabajar con el datagramPacketReceived aquí  (hacer un if para ver que tipo de solicitud es)

                Thread.sleep(100);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error ocurrido recibiendo algún paquete\n message:" + e.getMessage(), "Atención!", JOptionPane.ERROR_MESSAGE);
        }

    }

}
