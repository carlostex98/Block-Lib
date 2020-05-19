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
    public static m_clientes clt = new m_clientes();
    public static usuarios nop = new usuarios();
    public static m_categorias ctegoria = new m_categorias();
    public static Btree libros = new Btree();
    public static lisb libro_aux = new lisb();
    public static int state = 99;
    public static String action = "";
    public static String content = "";
    public static String img = "";
    public static categoria cat_curr = null;
    public static guarda_datos x = new guarda_datos();
    public static block_generator genera = new block_generator();
    public static m_bloques gbloque = new m_bloques();
    public static String mip = "";

    public static void main(String[] args) throws InterruptedException, IOException {
        //vars
        usuario.llenado(45);
        usuario.agregar_usuario(201700317, "Carlos", "Tenes", "Ciencias y Sistemas", "1234");

        x.lee_datos();
        logueo loguex = new logueo();
        loguex.setVisible(true);
        //concon();

    }

    public static void concon() throws InterruptedException {

        while (true) {
            if (state == 0) {
                //escuchamos clientes y bloques
                try {
                    URL url = new URL("http://502tec.com/eddx/index.php?a=2&b=0");
                    URLConnection conn = url.openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                    //System.out.println("Protocol: " + url.toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    String ee = "";
                    while ((line = in.readLine()) != null) {
                        ee += line;
                    }
                    in.close();
                    clt.read_clientes(ee);

                } catch (Exception e) {
                    System.out.println(e);
                }
                //bloque ultimo
                try {
                    URL url = new URL("http://502tec.com/eddx/index.php?a=4&b=0");
                    URLConnection conn = url.openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                    //System.out.println("Protocol: " + url.toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    String ee = "";
                    while ((line = in.readLine()) != null) {
                        ee += line;
                    }
                    in.close();

                    if (gbloque.ultimo == null) {
                        //no hay bloques
                        if (!ee.equals("[]")) {
                            JSONObject obj = new JSONObject(ee);
                            genera.recibe_bloque(ee);
                            gbloque.nuevo_bloque(obj.getInt("INDEX"), obj.getString("TIMESTAMP"), obj.get("DATA").toString(), obj.getInt("NONCE"), obj.getString("PREVIOUSHASH"), obj.getString("HASH"));
                        }
                    } else {
                        if (!ee.equals("[]")) {
                            //System.out.println(ee);
                            JSONObject obj = new JSONObject(ee);
                            if (obj.getInt("INDEX") != gbloque.ultimo.id) {
                                genera.recibe_bloque(ee);
                                gbloque.nuevo_bloque(obj.getInt("INDEX"), obj.getString("TIMESTAMP"), obj.get("DATA").toString(), obj.getInt("NONCE"), obj.getString("PREVIOUSHASH"), obj.getString("HASH"));
                            }
                        }
                    }

                    //clt.read_clientes(ee);
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else if (state == 1) {
                //requerimos la ip
                try {
                    URL url = new URL("http://502tec.com/eddx/index.php?a=1&b=0");
                    URLConnection conn = url.openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                    //System.out.println("Protocol: " + url.toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    String ee = "";
                    while ((line = in.readLine()) != null) {
                        ee += line;
                    }
                    in.close();
                    mip = ee;
                    state = 44;//estado de escucha
                } catch (Exception e) {
                    System.out.println(e);
                }
                //require last

            } else if (state == 3) {
                //manda bloque
                try {
                    content = content.replace(" ", "%20");
                    URL url = new URL("http://502tec.com/eddx/index.php?a=3&b=" + content);
                    //System.out.println("http://502tec.com/eddx/index.php?a=3&b="+content);
                    URLConnection conn = url.openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                    //System.out.println("Protocol: " + url.toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    String ee = "";
                    while ((line = in.readLine()) != null) {
                        ee += line;
                    }
                    in.close();
                    System.out.println(ee);
                    //mip = ee;
                    state = 0;//estado de escucha
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    content = "";
                }
            } else if (state == 44) {
                try {
                    URL url = new URL("http://502tec.com/eddx/index.php?a=2&b=0");
                    URLConnection conn = url.openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                    //System.out.println("Protocol: " + url.toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    String ee = "";
                    while ((line = in.readLine()) != null) {
                        ee += line;
                    }
                    in.close();
                    clt.read_clientes(ee);

                } catch (Exception e) {
                    System.out.println(e);
                }
                //bloque ultimo
                try {
                    URL url = new URL("http://502tec.com/eddx/index.php?a=4&b=0");
                    URLConnection conn = url.openConnection();
                    conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                    //System.out.println("Protocol: " + url.toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    String ee = "";
                    while ((line = in.readLine()) != null) {
                        ee += line;
                    }
                    in.close();

                    if (gbloque.ultimo == null) {
                        //no hay bloques
                        if (!ee.equals("[]")) {
                            JSONObject obj = new JSONObject(ee);
                            //genera.recibe_bloque(ee);
                            gbloque.nuevo_bloque(obj.getInt("INDEX"), obj.getString("TIMESTAMP"), obj.get("DATA").toString(), obj.getInt("NONCE"), obj.getString("PREVIOUSHASH"), obj.getString("HASH"));
                        }
                    } else {
                        if (!ee.equals("[]")) {
                            //System.out.println(ee);
                            JSONObject obj = new JSONObject(ee);
                            if (obj.getInt("INDEX") != gbloque.ultimo.id) {
                                //genera.recibe_bloque(ee);
                                gbloque.nuevo_bloque(obj.getInt("INDEX"), obj.getString("TIMESTAMP"), obj.get("DATA").toString(), obj.getInt("NONCE"), obj.getString("PREVIOUSHASH"), obj.getString("HASH"));
                            }
                        }
                    }
                    state=0;
                    //clt.read_clientes(ee);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            Thread.sleep(1000);
        }
    }

}
