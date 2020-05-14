package pin;

public class lisb {

    isb primero;
    isb ultimo;

    public lisb() {
        primero = ultimo = null;
    }

    public void insert_l(int isbn, String titulo, String autor, String editorial, int anio, String edicion, String cat, String idiom, int carnet) {
        isb nuevo = new isb();
        nuevo.isbn = isbn;
        nuevo.titulo = titulo;
        nuevo.autor = autor;
        nuevo.editorial = editorial;
        nuevo.anio = anio;
        nuevo.edicion = edicion;
        nuevo.categoria = cat;
        nuevo.idioma = idiom;
        nuevo.carnet = carnet;
        
        //miramos si existe la categoria
        if(mains.ctegoria.exixste(cat)){
            //si el nodo existe
            mains.ctegoria.setRoot(cat);
        }else{
            //si no existe la categoria
            mains.ctegoria.insert(cat, carnet);
            mains.ctegoria.setRoot(cat);
        }
        //luego que es seguro inserta el libro en el arbol b correspondiente
        mains.cat_curr.cuenta = mains.cat_curr.cuenta + 1;
        mains.libros.Insertx(isbn);
        mains.cat_curr.raiz = mains.libros.root;

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.sig = nuevo;
            nuevo.ant = ultimo;
            ultimo = nuevo;
        }
        
        mains.cat_curr.primero=primero;
        mains.cat_curr.ultimo=ultimo;
        
    }

    public void del_libro(int isbn) {
        isb vista = primero;
        mains.cat_curr.cuenta = mains.cat_curr.cuenta - 1;
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
                    vista.ant.sig = vista.sig;
                    vista.sig.ant = vista.ant;
                }
                //mains.libros.root=new BNode(null);

                isb vista2 = primero;
                while (vista2 != null) {
                    mains.libros.Insertx(vista2.isbn);
                }
                mains.cat_curr.raiz = mains.libros.root;
            } else {
                vista = vista.sig;
            }
        }
    }

    public void ret_libro(int isbn) {
        //con joption pane 
    }
    
    public String ret_nombre(int isbn){
        String j="";
        isb visto=primero;
        while(visto!=null){
            if(visto.isbn==isbn){
                j=visto.titulo;
                break;
            }else{
                visto=visto.sig;
            }
        }
        return j;
    }
}
