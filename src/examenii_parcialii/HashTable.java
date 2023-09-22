
package examenii_parcialii;

public class HashTable {
    private Entry Siguiente;
    

    public void add(String usuario, long pos) {
        Entry newEntry = new Entry(usuario, pos);

        if (Siguiente == null) {
            Siguiente = newEntry;
        } else {
            Entry posicion = Siguiente;
            while (posicion.getNext() != null) {
                posicion = posicion.getNext();
            }
            posicion.setNext(newEntry);
        }
    }

    public void remove(String usuario) {
        if (Siguiente == null) {
            return;
        }

        if (Siguiente.getUsuario().equals(usuario)) {
            Siguiente = Siguiente.getNext();
            return;
        }

        Entry posicion = Siguiente;
        while (posicion.getNext() != null) {
            if (posicion.getNext().getUsuario().equals(usuario)) {
                posicion.setNext(posicion.getNext().getNext());
                return;
            }
            posicion = posicion.getNext();
        }
    }

    public long search(String usuario) {
        Entry posicion = Siguiente;
        while (posicion != null) {
            if (posicion.getUsuario().equals(usuario)) {
                return posicion.getPosition();
            }
            posicion = posicion.getNext();
        }
        return -1; 
    }

   
    public Entry getHead() {
        return Siguiente;
    }
}

