package servidor;

import Data.DiskNodeData;
import Domain.DiskNode;
import Domain.Libro;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author Estudiante
 */
public class ControllerNode extends Thread {

    public ControllerNode() {

    }

    @Override
    public void run() {

        try {

            DiskNodeData ddd = new DiskNodeData();

            ArrayList<DiskNode> discosActivos = ddd.getActiveList();

            System.out.println("controller node is running... listen in port: 69");

            int puerto = 69;
            DatagramSocket socketUDP = new DatagramSocket(puerto);

            byte[] buffer = new byte[1000];

            while (true) {

                DatagramPacket datagramPacketReceived = new DatagramPacket(buffer, buffer.length);//envio de un  paquete 
                socketUDP.receive(datagramPacketReceived);

                System.out.println("solicitud recibida!");

                String peticionLlegada = new String(datagramPacketReceived.getData(), 0, datagramPacketReceived.getLength());

                if (peticionLlegada.equalsIgnoreCase("registrar")) {
                    

                    String peticion = "si";
                    DatagramPacket datagramPacket = new DatagramPacket(peticion.getBytes(), peticion.getBytes().length, datagramPacketReceived.getAddress(), datagramPacketReceived.getPort());
                    socketUDP.send(datagramPacket);
                    System.out.println("server dijo si");

                    
                    byte[] buffer2 = new byte[10000];
                    DatagramPacket temp = new DatagramPacket(buffer2, buffer2.length);
                    socketUDP.receive(temp);

                    
                    Libro libro = SerializationUtils.deserialize(temp.getData());
                    System.out.println(libro.toString());
                    

                }

                if (peticionLlegada.equalsIgnoreCase("lista")) {

                    int size = discosActivos.size();
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

    public int getNumActivos() throws ClassNotFoundException, SQLException {
        DiskNodeData d = new DiskNodeData();
        return d.getActiveList().size();
    }

}
