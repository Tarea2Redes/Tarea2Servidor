package Domain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationUtils;

public class ClientAssistant extends Thread {

    private final DatagramPacket packet;

    private boolean mantenerConexion;
    private final DatagramSocket socketUDP;

    public ClientAssistant(DatagramPacket packet) throws SocketException {

        this.packet = packet;
        this.mantenerConexion = true;
        this.socketUDP = new DatagramSocket();

    }

    @Override
    public void run() {

        while (mantenerConexion) {

            try {

                String peticionLlegada = new String(this.packet.getData(), 0, this.packet.getLength());

                if (peticionLlegada.equalsIgnoreCase("registrar") || peticionLlegada.equalsIgnoreCase("lista")) {

                    if (peticionLlegada.equalsIgnoreCase("registrar")) {

                        String msj = "si";
                        byte[] mensaje = msj.getBytes();
                        DatagramPacket datagramPacketSend = new DatagramPacket(mensaje, mensaje.length, this.packet.getAddress(), this.packet.getPort());
                        socketUDP.send(datagramPacketSend);

                         byte[] buffer = new byte[30000];
                        // recibir el packet que contiene el objeto libro serializado
                        DatagramPacket temp = new DatagramPacket(buffer, buffer.length);
                        socketUDP.receive(temp);

                        Libro libro = SerializationUtils.deserialize(temp.getData());
                        
                        
                        //  ya aqui teniendo el objeto libro se procede a partirlo y guardarlo 
                        

                    } else if (peticionLlegada.equalsIgnoreCase("lista") == true) {

                    }

                }

                Thread.sleep(1000);

            } catch (InterruptedException e) {

            } catch (IOException ex) {
                Logger.getLogger(ClientAssistant.class.getName()).log(Level.SEVERE, null, ex);
            }

        }//while

    }//run 

}
