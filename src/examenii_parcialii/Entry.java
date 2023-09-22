
package examenii_parcialii;

public class Entry {
    
    private String usuario;
    private long posicion;
    private Entry siguiente;

    public Entry(String usuario, long posicion) {
        this.usuario = usuario;
        this.posicion = posicion;
        this.siguiente = null;
    }

    public String getUsuario() {
        return usuario;
    }

    public long getPosition() {
        return posicion;
    }

    public Entry getNext() {
        return siguiente;
    }

    public void setNext(Entry next) {
        this.siguiente = next;
    }
}
