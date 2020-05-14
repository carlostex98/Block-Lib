package pin;

public class lisb {

    isb primero;
    isb ultimo;

    public lisb() {
        primero = ultimo = null;
    }

    public void insert_l(int isbn, String titulo, String autor, String edit, int anio, String edicion, String cat, String idiom, int carnet) {
        isb nuevo = new isb();
        nuevo.isbn = isbn;
        nuevo.titulo = titulo;
        nuevo.autor = autor;
        nuevo.editorial = edit;
        nuevo.anio = anio;
        nuevo.edicion = edicion;
        nuevo.categoria = cat;
        nuevo.idioma = idiom;
        nuevo.carnet = carnet;
        
        //mains.libros.in_lib(mains.libros, isbn);
        mains.cat_curr.raiz=mains.libros.root;

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.sig = nuevo;
            nuevo.ant = ultimo;
            ultimo = nuevo;
        }
    }

    public void del_libro(int isbn) {
        isb vista = primero;
        while (vista != null) {
            if (vista.isbn == isbn) {
                //elimina
                if (vista == primero) {
                    if (vista == ultimo) {
                        primero = ultimo = null;
                    } else {
                        primero = vista.sig;
                        primero.ant = null;
                    }

                } else if (vista == ultimo) {
                    if (vista == primero) {
                        primero = ultimo = null;
                    } else {
                        ultimo = vista.ant;
                        ultimo.sig = null;
                    }
                } else {
                    //middle
                    vista.ant.sig=vista.sig;
                    vista.sig.ant=vista.ant;
                }
                //mains.libros.root=new BNode(null);
                
                isb vista2=primero;
                while(vista2!=null){
                    //mains.libros.in_lib(mains.libros, vista2.isbn);
                }
                mains.cat_curr.raiz=mains.libros.root;
            } else {
                vista = vista.sig;
            }
        }
    }

    public void ret_libro(int isbn) {
        //con joption pane 
    }
}
