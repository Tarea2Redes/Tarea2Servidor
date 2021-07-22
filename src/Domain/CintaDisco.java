
package Domain;

/**
 *
 * @author Estudiante
 */
public class CintaDisco {
   
    private int bitParidad; 
    private int isbn;
    private int numFragmento; // en caso de que el bitParidad sea 2
    private String data; // titulo##editorial##autor

    public CintaDisco(int tipo, int isbn, int numFragmento, String data) {
        this.bitParidad = tipo;
        this.isbn = isbn;
        this.numFragmento = numFragmento;
        this.data = data;
    }

    public CintaDisco() {

    }

    public String getData() {
        return data;
    }

    public int getBitparidad() {
        return bitParidad;
    }

    public void setTipo(int tipo) {
        this.bitParidad = tipo;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getFragOrder() {
        return numFragmento;
    }

    public void setNumFragmento(int numFragmento) {
        this.numFragmento = numFragmento;
    }
    
  
    public int sizeInBytes(){
        //String: necesita 2 bytes de espacio por cada caracter.
        //int: necesita 4 bytes por cifra
        return 4 + 4 + 4 + (data.length() * 2);
    }      

    public void setData(String data) {
        this.data = data;
    }

    
}
