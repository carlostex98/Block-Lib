package pin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.*;

public class guarda_datos {

    //vamos a guardar todos las datos
    String usuarios;
    String categorias;
    String json;
    JSONArray arx = new JSONArray();
    JSONArray ar = new JSONArray();

    public void general() {

        usr();
        libros(mains.ctegoria.root);
        JSONObject obj = new JSONObject();

        obj.put("usuarios", ar);
        obj.put("Libros", arx);
        //System.out.println(obj.toString());

        new File("/path/directory").mkdirs();
        try {
            PrintWriter writer = new PrintWriter("datos.json", "UTF-8");
            writer.println(obj.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void usr() {
        indice_usuarios vista = mains.usuario.pi_usuarios;
        usuarios n;

        String u = "";

        while (vista != null) {
            //ahora las relaciones de los usuarios
            n = vista.primero_usr;
            while (n != null) {
                //save to json
                JSONObject obj3 = new JSONObject();
                obj3.put("Carnet", n.carnet);
                obj3.put("Nombre", n.nombre);
                obj3.put("Apellido", n.apellido);
                obj3.put("Carrera", n.carrera);
                obj3.put("pass", n.psw);
                ar.put(obj3);

                n = n.siguiente;
            }
            vista = vista.siguiente;
        }
        //System.out.println(ar.toString());
        //usuarios=ar.toString();
    }

    public void libros(categoria n) {
        if (n != null) {

            libros(n.izq);
            isb aux = n.primero;

            while (aux != null) {
                JSONObject obj3 = new JSONObject();
                obj3.put("ISBN", aux.isbn);
                obj3.put("Titulo", aux.titulo);
                obj3.put("Autor", aux.autor);
                obj3.put("Editorial", aux.editorial);
                obj3.put("Anio", aux.anio);
                obj3.put("Edicion", aux.edicion);
                obj3.put("Categoria", aux.categoria);
                obj3.put("Idioma", aux.idioma);
                obj3.put("Carnet", aux.carnet);
                arx.put(obj3);
                aux = aux.sig;
            }

            //System.out.println("ye");
            //arx.toList();
            libros(n.der);

        }
    }
}
