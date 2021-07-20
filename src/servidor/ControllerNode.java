package servidor;

import Data.DiskNodeData;
import Domain.DiskNode;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.SQLException;
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

                String peticionLlegada = new String(datagramPacketReceived.getData(), 0, datagramPacketReceived.getLength());

                if (peticionLlegada.equalsIgnoreCase("registrar")) {

                }

                if (peticionLlegada.equalsIgnoreCase("lista")) {

                }

                Thread.sleep(100);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error ocurrido recibiendo algún paquete\n message:" + e.getMessage(), "Atención!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void addDisk() throws ClassNotFoundException, SQLException {
        DiskNodeData d = new DiskNodeData();
        d.insert();
        
    }

    public void disableDisk(int diskNumber) throws ClassNotFoundException, SQLException {
        DiskNodeData d = new DiskNodeData();
        d.disable(diskNumber);
    }

}
