package servidor;

import Data.DiskNodeData;
import Domain.Contenido;
import Domain.DiskNode;
import Domain.CintaDisco;
import Domain.Libro;
import Domain.Metadata;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        try {

            DiskNodeData ddd = new DiskNodeData();

            ArrayList<DiskNode> discosActivos = ddd.getActiveList();

            System.out.println("controller node is running... listen in port: 69");

            int puerto = 69;
            DatagramSocket socketUDP = new DatagramSocket(puerto);

            while (true) {

                byte[] buffer = new byte[1000];
                DatagramPacket datagramPacketReceived = new DatagramPacket(buffer, buffer.length);//envio de un  paquete 
                socketUDP.receive(datagramPacketReceived);
                System.out.println("new request!");
                String peticionLlegada = new String(datagramPacketReceived.getData(), 0, datagramPacketReceived.getLength());

                if (peticionLlegada.equalsIgnoreCase("registrar")) {

                    String peticion = "si";
                    DatagramPacket datagramPacket = new DatagramPacket(peticion.getBytes(), peticion.getBytes().length, datagramPacketReceived.getAddress(), datagramPacketReceived.getPort());
                    socketUDP.send(datagramPacket);

                    byte[] buffer2 = new byte[10000];
                    DatagramPacket temp = new DatagramPacket(buffer2, buffer2.length);
                    socketUDP.receive(temp);

//                    Libro libro = SerializationUtils.deserialize(temp.getData());
                    Libro libro = deserialize(temp.getData());

                    //codigo huffman aqui 
                    
                    
                    System.out.println(libro.toString());

                    String metadata = String.valueOf(libro.getMetadata().getIsbn()) + "##"
                            + libro.getMetadata().getTitulo() + "##"
                            + libro.getMetadata().getAutor() + "##"
                            + libro.getMetadata().getEditorial() + "##"
                            + libro.getMetadata().getGenero() + "##"
                            + libro.getMetadata().getPaginas();

                    CintaDisco fragmentoMetaData = new CintaDisco(1, libro.getMetadata().getIsbn(), 0, metadata);

                    String contenido = libro.getContenido().getContenido();
                    int contentSize = contenido.length();

                    int salto = contentSize / 3;

                    String aux1 = contenido.substring(0, salto);
                    String aux2 = contenido.substring(salto, salto * 2);
                    String aux3 = contenido.substring((salto * 2), contentSize);
                    
                    CintaDisco fragCont1 = new CintaDisco(2, libro.getContenido().getIsbn(), 1, aux1);
                    CintaDisco fragCont2 = new CintaDisco(2, libro.getContenido().getIsbn(), 2, aux2);
                    CintaDisco fragCont3 = new CintaDisco(2, libro.getContenido().getIsbn(), 3, aux3);

                    saveKey(libro.getMetadata().getIsbn());
                    // guardarlo en el disco general (backup)
                    DiskNode backup = discosActivos.get(0);
                    backup.Storage().add(fragmentoMetaData);
                    backup.Storage().add(fragCont1);
                    backup.Storage().add(fragCont2);
                    backup.Storage().add(fragCont3);

                    // repartir los fragmentos en los discos activos
                    int min = 1;
                    int max = discosActivos.size()-1; 

                    int random_disk;
                    for (int i = 0; i < 4; i++) {

                        random_disk = (int) Math.floor(Math.random() * (max - min + 1) + min);
                        DiskNode disk;

                        switch (i) {

                            case 0:
                                disk = discosActivos.get(random_disk);
                                disk.Storage().add(fragmentoMetaData);
                                break;

                            case 1:
                                disk = discosActivos.get(random_disk);
                                disk.Storage().add(fragCont1);
                                break;

                            case 2:
                                disk = discosActivos.get(random_disk);
                                disk.Storage().add(fragCont2);
                                break;

                            case 3:
                                disk = discosActivos.get(random_disk);
                                disk.Storage().add(fragCont3);
                                break;
                        }

                    }

                } else if (peticionLlegada.equalsIgnoreCase("lista")) {

                    // se reconstruye la lista de libros a partir de los fragmentos
                    // se envian los libros uno a uno
                    ArrayList<Integer> keysList = getKeys();
                    ArrayList<Libro> libros = new ArrayList<>();

                    for (int i = 0; i < keysList.size(); i++) {

                        Metadata metadata = null;
                        Contenido contenido = null;
                        Libro libro = null;

                        String cont1 = "";
                        String cont2 = "";
                        String cont3 = "";

                        for (int j = 1; j < discosActivos.size(); j++) {

                            DiskNode disk = discosActivos.get(j);
                            ArrayList<CintaDisco> cintasDiscoDuro = disk.Storage().getAll();

                            for (int k = 0; k < cintasDiscoDuro.size(); k++) {

                                if (cintasDiscoDuro.get(k).getIsbn() == keysList.get(i)) {

                                    if (cintasDiscoDuro.get(k).getBitparidad() == 1) { // no contiene errores

                                        String[] metadatas = cintasDiscoDuro.get(k).getData().split("##");
                                        metadata = new Metadata(metadatas[1], metadatas[2], metadatas[3], metadatas[4], metadatas[5], Integer.parseInt(metadatas[0]));

                                    } else {
                                        // fragmento con contenido
                                        if (cintasDiscoDuro.get(k).getFragOrder() == 1) {
                                            cont1 = cintasDiscoDuro.get(k).getData();
                                        } else if (cintasDiscoDuro.get(k).getFragOrder() == 2) {
                                            cont2 = cintasDiscoDuro.get(k).getData();
                                        } else if (cintasDiscoDuro.get(k).getFragOrder() == 3) {
                                            cont3 = cintasDiscoDuro.get(k).getData();
                                        }

                                    }

                                }
                            }

                        }

                        String contenidoCompleto = cont1 + cont2 + cont3;

                        if (cont1.equals("") || cont2.equals("") || cont3.equals("") || metadata == null) {
                            libros.add(buscarEnBackUp(keysList.get(i)));
                        } else {

                            contenido = new Contenido(metadata.getIsbn(), contenidoCompleto);

                            libro = new Libro(metadata, contenido);
                            libros.add(libro);
                        }

                    }

                    for (int i = 0; i < libros.size(); i++) {

//                      byte[] data = SerializationUtils.serialize(libros.get(i));
                        byte[] data = serialize(libros.get(i));
                        DatagramPacket datagramPacketEnvio = new DatagramPacket(data, data.length, datagramPacketReceived.getAddress(), datagramPacketReceived.getPort());
                        socketUDP.send(datagramPacketEnvio);
                        sleep(300);

                    }

                    String peticion = "end";
                    DatagramPacket datagramPacket = new DatagramPacket(peticion.getBytes(), peticion.getBytes().length, datagramPacketReceived.getAddress(), datagramPacketReceived.getPort());
                    socketUDP.send(datagramPacket);

                }

            }

        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "error \n message:" + ex.getMessage(), "Atención!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerNode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "error \n message:" + ex.getMessage(), "Atención!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControllerNode.class.getName()).log(Level.SEVERE, null, ex);
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

    public void enableDisk(int diskNumber) throws ClassNotFoundException, SQLException {
        DiskNodeData d = new DiskNodeData();
        d.enable(diskNumber);
    }

    public int getNumActivos() throws ClassNotFoundException, SQLException {
        DiskNodeData d = new DiskNodeData();
        return d.getActiveList().size();
    }

    private void saveKey(int isbn) {
        try {
            FileWriter myWriter = new FileWriter("./keys.txt", true);
            myWriter.write(String.valueOf(isbn) + "\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> getKeys() throws FileNotFoundException {
        ArrayList<Integer> lista = new ArrayList<>();

        File myObj = new File("./keys.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            int key = Integer.parseInt(myReader.nextLine());
            lista.add(key);

        }
        return lista;
    }

    private Libro buscarEnBackUp(int key) throws ClassNotFoundException, IOException, SQLException {

        Metadata metadata = null;
        Contenido contenido = null;
        Libro libro = null;

        String cont1 = "";
        String cont2 = "";
        String cont3 = "";

        DiskNodeData ddd = new DiskNodeData();

        DiskNode disk = ddd.getActiveList().get(0);
        ArrayList<CintaDisco> cintasDiscoDuro = disk.Storage().getAll();

        for (int k = 0; k < cintasDiscoDuro.size(); k++) {

            if (cintasDiscoDuro.get(k).getIsbn() == key) {

                if (cintasDiscoDuro.get(k).getBitparidad() == 1) { // es la metadata

                    String[] metadatas = cintasDiscoDuro.get(k).getData().split("##");
                    metadata = new Metadata(metadatas[1], metadatas[2], metadatas[3], metadatas[4], metadatas[5], Integer.parseInt(metadatas[0]));

                } else {
                    // fragmento con contenido
                    if (cintasDiscoDuro.get(k).getFragOrder() == 1) {
                        cont1 = cintasDiscoDuro.get(k).getData();
                    } else if (cintasDiscoDuro.get(k).getFragOrder() == 2) {
                        cont2 = cintasDiscoDuro.get(k).getData();
                    } else if (cintasDiscoDuro.get(k).getFragOrder() == 3) {
                        cont3 = cintasDiscoDuro.get(k).getData();
                    }

                }

            }
        }

        String contenidoCompleto = cont1 + cont2 + cont3;

        contenido = new Contenido(metadata.getIsbn(), contenidoCompleto);

        libro = new Libro(metadata, contenido);

        return libro;

    }

    private byte[] serialize(Libro object) {
        if (object == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream(3000);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), ex);
        }
        return baos.toByteArray();
    }

    private Libro deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInputStream in = new ObjectInputStream(bis)) {
            return (Libro) in.readObject();
        }
    }

}
