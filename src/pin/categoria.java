package pin;

public class categoria {
    String nombre;
    int carnet; //dueño de la categoria
    categoria der;
    categoria izq;
    int height;
    categoria(String nombre, int carnet) {
        this.nombre = nombre;
        this.carnet=carnet;
    }
}