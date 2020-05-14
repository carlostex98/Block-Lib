package pin;

public class categoria {
    String nombre;
    int carnet; //dueño de la categoria
    categoria der;
    categoria izq;
    int height;
    Node raiz;
    isb primero;
    isb ultimo;
    int cuenta;
    categoria(String nombre, int carnet) {
        this.nombre = nombre;
        this.carnet=carnet;
    }
    
}
