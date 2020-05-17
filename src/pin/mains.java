package pin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.*;

public class mains {

    public static m_usuarios usuario = new m_usuarios();
    public static usuarios nop = new usuarios();
    public static m_categorias ctegoria = new m_categorias();
    public static Btree libros = new Btree();
    public static lisb libro_aux = new lisb();
    public static int state = 0;
    public static String action = "";
    public static String content = "";
    public static String img = "";
    public static categoria cat_curr = null;
    public static guarda_datos x=new guarda_datos();
    public static block_generator genera=new block_generator();
    public static m_bloques gbloque=new m_bloques();

    public static void main(String[] args) throws InterruptedException, IOException {
        //vars
        usuario.llenado(45);
        usuario.agregar_usuario(201700317, "Carlos", "Tenes", "Ciencias y Sistemas", "1234");
        //ctegoria.insert("Terror", 201700317);
        /*ctegoria.insert("Comedia", 201700317);
        libro_aux.insert_l(12, "Las aventuras del universitario", "el bicho", "casa sola", 2020, "la uno", "Comedia","Espanolo", 201700317);
        libro_aux.insert_l(165, "Las aventuras del universitario 1", "el bicho", "casa sola", 2020, "la uno", "Comedia","Espanolo", 201700317);
        libro_aux.insert_l(1445, "Las aventuras del universitario 2", "el bicho", "casa sola", 2020, "la uno", "Comedia","Espanolo", 201700317);
        libro_aux.insert_l(17, "Las aventuras del universitario 3", "el bicho", "casa sola", 2020, "la uno", "Comedia","Espanolo", 201700317);
        libro_aux.insert_l(98, "Las aventuras del universitario 4", "el bicho", "casa sola", 2020, "la uno", "Comedia","Espanolo", 201700317);
        libro_aux.insert_l(782, "Las aventuras del universitario final", "el bicho", "casa sola", 2020, "la uno", "Comedia","Espanolo", 201700317);*/
        x.lee_datos();
        logueo loguex = new logueo();
        loguex.setVisible(true);
        JSONObject obj = new JSONObject();
        obj.put("DATA", "minombre");
        genera.recibe_bloque(obj.toString());
        //genera.cat_nuevo("Comedia");
        
        /*try {
            URL url = new URL("http://502tec.com/eddx/index.php");
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            //System.out.println("Protocol: " + url.toString());
             BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
             
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

        } catch (Exception e) {
            System.out.println(e);
        }*/
    }

}
