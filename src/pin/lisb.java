package pin;

public class lisb {

    isb primero;
    isb ultimo;

    public lisb() {
        primero = ultimo = null;
    }

    public void insert_l(int isbn, String titulo, String autor, String edit, int anio, String edicion, String cat, String idiom, int carnet) {
        isb nuevo = new isb();
        nuevo.isbn=isbn;
        nuevo.titulo=titulo;
        nuevo.autor=autor;
        nuevo.editorial=edit;
        nuevo.anio=anio;
        nuevo.edicion=edicion;
        nuevo.categoria=cat;
        nuevo.idioma=idiom;
        nuevo.carnet=carnet;
        
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.sig=nuevo;
            nuevo.ant=ultimo;
            ultimo=nuevo;
        }
    }
}
