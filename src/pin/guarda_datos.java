package pin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

        obj.put("Usuarios", ar);
        obj.put("Libros", arx);
        //System.out.println(obj.toString());

        //new File("/path/directory").mkdirs();
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
                obj3.put("ISBN", aux.isbn);//int
                obj3.put("Titulo", aux.titulo);
                obj3.put("Autor", aux.autor);
                obj3.put("Editorial", aux.editorial);
                obj3.put("Anio", aux.anio);//int
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

    public void lee_libros(String jison) {
        JSONObject obj = new JSONObject(jison);
        JSONArray arr = obj.getJSONArray("libros");
        for (int i = 0; i < arr.length(); i++) {
            int isbn = arr.getJSONObject(i).getInt("ISBN");//int
            int anio = arr.getJSONObject(i).getInt("Año");//
            String idioma = arr.getJSONObject(i).getString("Idioma");
            String titulo = arr.getJSONObject(i).getString("Titulo");
            String editorial = arr.getJSONObject(i).getString("Editorial");
            String autor = arr.getJSONObject(i).getString("Autor");
            String edicion = Integer.toString(arr.getJSONObject(i).getInt("Edicion"));
            String categoria = arr.getJSONObject(i).getString("Categoria");
            mains.libro_aux.insert_l(isbn, titulo, autor, editorial, anio, edicion, categoria, idioma, mains.nop.carnet);
        }
    }

    public void lee_usuarios(String jison) {
        JSONObject obj = new JSONObject(jison);
        JSONArray arr = obj.getJSONArray("Usuarios");
        for (int i = 0; i < arr.length(); i++) {
            int carnet = arr.getJSONObject(i).getInt("Carnet");
            String nombre = arr.getJSONObject(i).getString("Nombre");
            String apellido = arr.getJSONObject(i).getString("Apellido");
            String carrera = arr.getJSONObject(i).getString("Carrera");
            String pasw = arr.getJSONObject(i).getString("Password");
            mains.usuario.agregar_usuario(carnet, nombre, apellido, carrera, pasw);

        }
    }

    public void lee_datos() throws IOException {
        File file = new File("datos.json");
        boolean exists = file.exists();
        if (exists) {
            System.out.println("yes");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            String o = "";
            while ((st = br.readLine()) != null) {
                o += st;
            }

            JSONObject obj = new JSONObject(o);
            JSONArray arr = obj.getJSONArray("Usuarios");
            for (int i = 0; i < arr.length(); i++) {
                int carnet = arr.getJSONObject(i).getInt("Carnet");
                String nombre = arr.getJSONObject(i).getString("Nombre");
                String apellido = arr.getJSONObject(i).getString("Apellido");
                String carrera = arr.getJSONObject(i).getString("Carrera");
                String pasw = arr.getJSONObject(i).getString("pass");
                mains.usuario.agregar_sinhash(carnet, nombre, apellido, carrera, pasw);
            }

            JSONObject obj2 = new JSONObject(o);
            JSONArray arr2 = obj2.getJSONArray("Libros");
            for (int i = 0; i < arr2.length(); i++) {
                int isbn = arr2.getJSONObject(i).getInt("ISBN");//int
                int anio = arr2.getJSONObject(i).getInt("Anio");//
                String idioma = arr2.getJSONObject(i).getString("Idioma");
                String titulo = arr2.getJSONObject(i).getString("Titulo");
                String editorial = arr2.getJSONObject(i).getString("Editorial");
                String autor = arr2.getJSONObject(i).getString("Autor");
                String edicion = Integer.toString(arr2.getJSONObject(i).getInt("Edicion"));
                String categoria = arr2.getJSONObject(i).getString("Categoria");
                int carnet = arr2.getJSONObject(i).getInt("Carnet");
                mains.libro_aux.insert_l(isbn, titulo, autor, editorial, anio, edicion, categoria, idioma, carnet);
            }
        }

    }
}
