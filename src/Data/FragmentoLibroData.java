package Data;

import Domain.CintaDisco;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FragmentoLibroData {

    //atributos
    public RandomAccessFile randomAccessFile;
    private int cantidadRegistros;//cantidad de registros en el archivo
    private int tamagnoDelRegistro;//tamaño del registro
    private String ruta;//ruta 

    //constructor
    public FragmentoLibroData(int number) throws IOException {
        //almaceno la ruta
        File file = new File("./Disk" + String.valueOf(number) + ".dat");
        start(file);
    }

    private void start(File file) throws IOException {

        this.ruta = file.getPath();
        //indico el tamanno máximo por cada registro 
        this.tamagnoDelRegistro = 140;

        //una validación sencilla
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " es un archivo inválido.");
        } else {
            //crear la nueva instancia de RAF
            randomAccessFile = new RandomAccessFile(file, "rw");

            //necesitamos indicar cuántos registros tiene el archivo
            this.cantidadRegistros
                    = (int) Math.ceil((double) randomAccessFile.length() / (double) tamagnoDelRegistro);
        }

    }

    //insertar un nuevo registro en una posicion específica
    private boolean create(int position, CintaDisco frafg) throws IOException {
        //primero: verificar que sea válida la inserción
        if (position < 0 && position > this.cantidadRegistros) {
            return false;
        } else {
            if (frafg.sizeInBytes() > this.tamagnoDelRegistro) {
                return false;
            } else {
                //escrbir registro en archivo
                randomAccessFile.seek(position * this.tamagnoDelRegistro);
                randomAccessFile.writeUTF(frafg.getData());
                randomAccessFile.writeInt(frafg.getIsbn());
                randomAccessFile.writeInt(frafg.getBitparidad());
                randomAccessFile.writeInt(frafg.getFragOrder());
                return true;
            }
        }
    }

    //insertar al final del archivo
    public boolean add(CintaDisco producto) throws IOException {
        boolean success = create(this.cantidadRegistros, producto);
        if (success) {
            ++this.cantidadRegistros;
        }
        return success;
    }

    //obtener un registro
    private CintaDisco getRegister(int position) throws IOException {
        
        CintaDisco objetoNulo = null;
        
        //validar la posición
        if (position >= 0 && position <= this.cantidadRegistros) {
            // pegamos el salto al lugar adecuado
            randomAccessFile.seek(position * this.tamagnoDelRegistro);

            //llevamos a cabo la lectura
            CintaDisco temp = new CintaDisco();
            temp.setData(randomAccessFile.readUTF());
            temp.setIsbn(randomAccessFile.readInt());
            temp.setTipo(randomAccessFile.readInt());
            temp.setNumFragmento(randomAccessFile.readInt());

            return temp;
        } else {
            return objetoNulo;
        }
    }

    //retornar una lista de productos escritos en el archivo
    public ArrayList<CintaDisco> getAll() throws IOException {
        ArrayList<CintaDisco> lista = new ArrayList<>();

        for (int i = 0; i < this.cantidadRegistros; i++) {
            CintaDisco temp = this.getRegister(i);

            if (temp != null) {
                lista.add(temp);
            }
        }//end for
        return lista;
    }

    //indicar la cantidad de registros de nuestro archivo
    public int fileSize() {
        return this.cantidadRegistros;
    }

    //MUY IMPORTANTE cerrar nuestros archivos
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    public RandomAccessFile getFile() {
        return this.randomAccessFile;
    }

}//class
