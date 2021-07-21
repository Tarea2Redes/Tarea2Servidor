
package Domain;

/**
 *
 * @author Estudiante
 */
public class FragmentoLibro {
   
    private int tipo; // 1 metadata, 2 fragmentoContenido;
    private int isbn;
    private int numFragmento; // en caso de que el tipo sea 2
    private String data; // titulo##editorial##autor

    public FragmentoLibro(int tipo, int isbn, int numFragmento, String data) {
        this.tipo = tipo;
        this.isbn = isbn;
        this.numFragmento = numFragmento;
        this.data = data;
    }

    public FragmentoLibro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getData() {
        return data;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getNumFragmento() {
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
