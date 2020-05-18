package pin;

import javax.swing.JOptionPane;

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
        if (mains.ctegoria.exixste(cat)) {
            //si el nodo existe
            mains.ctegoria.setRoot(cat);
        } else {
            //si no existe la categoria
            mains.ctegoria.insert(cat, carnet);
            mains.ctegoria.setRoot(cat);
        }
        //luego que es seguro inserta el libro en el arbol b correspondiente
        mains.cat_curr.cuenta = mains.cat_curr.cuenta + 1;
        mains.libros.Insertx(isbn);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
            //System.out.println("yes");
        } else {
            ultimo.sig = nuevo;
            nuevo.ant = ultimo;
            ultimo = nuevo;
        }

        mains.cat_curr.primero = primero;
        mains.cat_curr.ultimo = ultimo;
        mains.cat_curr.raiz = mains.libros.root;
    }

    public void del_libro(int isbn) {
        isb vista = primero;
        mains.cat_curr.cuenta = mains.cat_curr.cuenta - 1;
        while (vista != null) {
            if (vista.isbn == isbn) {
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
                mains.libros.root = new Node();

                isb vista2 = primero;
                while (vista2 != null) {
                    mains.libros.Insertx(vista2.isbn);
                    vista2=vista2.sig;
                }
                mains.cat_curr.raiz = mains.libros.root;
                break;
            } else {
                vista = vista.sig;
            }
        }
        mains.cat_curr.primero = primero;
        mains.cat_curr.ultimo = ultimo;
    }

    public boolean existe(int isbn) {
        //con joption pane 
        boolean s = false;
        isb visto = primero;
        while (visto != null) {
            if (visto.isbn == isbn) {
                s = true;
                break;
            } else {
                visto = visto.sig;
            }
        }
        return s;
    }

    public String ret_nombre(int isbn) {
        String j = "";
        isb visto = primero;
        while (visto != null) {
            if (visto.isbn == isbn) {
                j = visto.titulo;
                break;
            } else {
                visto = visto.sig;
            }
        }
        return j;
    }

    public void print_jop(int isbn) {
        String j = "";
        isb visto = primero;
        while (visto != null) {
            if (visto.isbn == isbn) {
                j = "ISBN: " + Integer.toString(isbn) + "\n";
                j += "Titulo: " + visto.titulo + "\n";
                j += "Autor: " + visto.autor + "\n";
                j += "Editorial: " + visto.editorial + "\n";
                j += "Año: " + Integer.toString(visto.anio) + "\n";
                j += "Edicion: " + visto.edicion + "\n";
                j += "Categoria: " + visto.categoria + "\n";
                j += "Idioma: " + visto.idioma + "\n";
                j += "Dueño: " + Integer.toString(visto.carnet) + "\n";
                break;
            } else {
                visto = visto.sig;
            }
        }
        JOptionPane.showMessageDialog(null, j);
    }

    public void edit(int isbn, String titulo, String autor, String editorial, int anio, String edicion, String cat, String idiom, int carnet) {
        isb visto = primero;
        while (visto != null) {
            if (visto.isbn == isbn) {
                //procedemos a editar
                
                visto.titulo = titulo;
                visto.autor = autor;
                visto.editorial = editorial;
                visto.anio = anio;
                visto.edicion = edicion;
                //visto.categoria = cat;
                visto.idioma = idiom;
                //visto.carnet = carnet;
                break;
            } else {
                visto = visto.sig;
            }
        }
    }
    
    public isb ret_node(int isbn){
        isb visto=primero;
        while (visto != null) {
            if (visto.isbn == isbn) {
                break;
            } else {
                visto = visto.sig;
            }
        }
        return visto;
    }

}
