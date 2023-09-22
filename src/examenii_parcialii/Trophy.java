
package examenii_parcialii;


public enum Trophy {
    Platino(5),Oro(3),Plata(2),Bronce(1);

    public final int puntos;

    Trophy(int puntos) {
        this.puntos = puntos;
    }
}

