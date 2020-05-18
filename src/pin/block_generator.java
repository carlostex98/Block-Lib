package pin;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import org.json.*;

public class block_generator {

    int noncex = 0;

    public void cat_nuevo(String cate) {
        //crea y luego inserta
        JSONObject obj = new JSONObject();
        int index = 0;
        String tmst = "";
        tmst = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss").format(new java.util.Date());
        String data = "";
        int nonce = 0;
        String phash = "0000";
        String hactual = "";
        bloques aux = mains.gbloque.ret_ultimo();
        if (aux != null) {
            phash = aux.hh;
            index = aux.id + 1;
        }
        
        JSONObject data2 = new JSONObject();
        data2.put("NOMBRE", cate);
        data2.put("CARNET", mains.nop.carnet);
        JSONObject data1 = new JSONObject();
        data1.put("CREAR_CATEGORIA", data2);
        
        nonce=nonce_calc(Integer.toString(index)+tmst+phash+data1.toString());
        //System.out.println(nonce);
        hactual=sha256(Integer.toString(index)+tmst+phash+data1.toString()+Integer.toString(nonce));
        obj.put("INDEX", index);
        obj.put("TIMESTAMP", tmst);
        obj.put("NONCE", nonce);
        obj.put("DATA", data1);
        obj.put("PREVIOUSHASH", phash);
        obj.put("HASH", hactual);
        data=data1.toString();
        
        mains.gbloque.nuevo_bloque(index, tmst, data, nonce, phash, hactual);
        //send to service
        mains.state=3;
        mains.content=obj.toString();
    }

    public void elim_cat(String cat) {
        //crea y luego inserta
        JSONObject obj = new JSONObject();
        int index = 0;
        String tmst = "";
        tmst = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss").format(new java.util.Date());
        String data = "";
        int nonce = 0;
        String phash = "0000";
        String hactual = "";
        bloques aux = mains.gbloque.ret_ultimo();
        if (aux != null) {
            phash = aux.hh;
            index = aux.id + 1;
        }
        
        JSONObject data2 = new JSONObject();
        data2.put("NOMBRE", cat);
        JSONObject data1 = new JSONObject();
        data1.put("ELIMINAR_CATEGORIA", data2);
        
        nonce=nonce_calc(Integer.toString(index)+tmst+phash+data1.toString());
        //System.out.println(nonce);
        hactual=sha256(Integer.toString(index)+tmst+phash+data1.toString()+Integer.toString(nonce));
        obj.put("INDEX", index);
        obj.put("TIMESTAMP", tmst);
        obj.put("NONCE", nonce);
        obj.put("DATA", data1);
        obj.put("PREVIOUSHASH", phash);
        obj.put("HASH", hactual);
        data=data1.toString();
        
        mains.gbloque.nuevo_bloque(index, tmst, data, nonce, phash, hactual);
        //send to service
        mains.state=3;
        mains.content=obj.toString();
    }

    public void envia_usr(int carnet, String nombre, String apellido, String carrera, String pass) {
        //crea y luego inserta
        JSONObject obj = new JSONObject();
        int index = 0;
        String tmst = "";
        tmst = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss").format(new java.util.Date());
        String data = "";
        int nonce = 0;
        String phash = "0000";
        String hactual = "";
        bloques aux = mains.gbloque.ret_ultimo();
        if (aux != null) {
            phash = aux.hh;
            index = aux.id + 1;
        }
        
        JSONObject data2 = new JSONObject();
        data2.put("CARNET", carnet);
        data2.put("NOMBRE", nombre);
        data2.put("APELLIDO", apellido);
        data2.put("CARRERA", carrera);
        data2.put("PASSWORD", pass);
        
        JSONObject data1 = new JSONObject();
        data1.put("CREAR_USUARIO", data2);
        
        nonce=nonce_calc(Integer.toString(index)+tmst+phash+data1.toString());
        //System.out.println(nonce);
        hactual=sha256(Integer.toString(index)+tmst+phash+data1.toString()+Integer.toString(nonce));
        obj.put("INDEX", index);
        obj.put("TIMESTAMP", tmst);
        obj.put("NONCE", nonce);
        obj.put("DATA", data1);
        obj.put("PREVIOUSHASH", phash);
        obj.put("HASH", hactual);
        data=data1.toString();
        
        mains.gbloque.nuevo_bloque(index, tmst, data, nonce, phash, hactual);
        //send to service
        mains.state=3;
        mains.content=obj.toString();
    }

    public void elim_usr(int carnet) {
        //crea y luego inserta
        JSONObject obj = new JSONObject();
        int index = 0;
        String tmst = "";
        tmst = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss").format(new java.util.Date());
        String data = "";
        int nonce = 0;
        String phash = "0000";
        String hactual = "";
        bloques aux = mains.gbloque.ret_ultimo();
        if (aux != null) {
            phash = aux.hh;
            index = aux.id + 1;
        }
        
        JSONObject data2 = new JSONObject();
        data2.put("CARNET", carnet);
        
        
        JSONObject data1 = new JSONObject();
        data1.put("ELIMINAR _USUARIO", data2);
        
        nonce=nonce_calc(Integer.toString(index)+tmst+phash+data1.toString());
        //System.out.println(nonce);
        hactual=sha256(Integer.toString(index)+tmst+phash+data1.toString()+Integer.toString(nonce));
        obj.put("INDEX", index);
        obj.put("TIMESTAMP", tmst);
        obj.put("NONCE", nonce);
        obj.put("DATA", data1);
        obj.put("PREVIOUSHASH", phash);
        obj.put("HASH", hactual);
        data=data1.toString();
        
        mains.gbloque.nuevo_bloque(index, tmst, data, nonce, phash, hactual);
        //send to service
        mains.state=3;
        mains.content=obj.toString();
    }

    public void mod_usr(int carnet, String nombre, String apellido, String carrera, String pass) {
        JSONObject obj = new JSONObject();
        int index = 0;
        String tmst = "";
        tmst = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss").format(new java.util.Date());
        String data = "";
        int nonce = 0;
        String phash = "0000";
        String hactual = "";
        bloques aux = mains.gbloque.ret_ultimo();
        if (aux != null) {
            phash = aux.hh;
            index = aux.id + 1;
        }
        
        JSONObject data2 = new JSONObject();
        data2.put("CARNET", carnet);
        data2.put("NOMBRE", nombre);
        data2.put("APELLIDO", apellido);
        data2.put("CARRERA", carrera);
        data2.put("PASSWORD", pass);
        
        JSONObject data1 = new JSONObject();
        data1.put("EDITAR_USUARIO", data2);
        
        nonce=nonce_calc(Integer.toString(index)+tmst+phash+data1.toString());
        //System.out.println(nonce);
        hactual=sha256(Integer.toString(index)+tmst+phash+data1.toString()+Integer.toString(nonce));
        obj.put("INDEX", index);
        obj.put("TIMESTAMP", tmst);
        obj.put("NONCE", nonce);
        obj.put("DATA", data1);
        obj.put("PREVIOUSHASH", phash);
        obj.put("HASH", hactual);
        data=data1.toString();
        
        mains.gbloque.nuevo_bloque(index, tmst, data, nonce, phash, hactual);
        //send to service
        mains.state=3;
        mains.content=obj.toString();
    }

    public void libro_elimina(int isbn) {
        JSONObject obj = new JSONObject();
        int index = 0;
        String tmst = "";
        tmst = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss").format(new java.util.Date());
        String data = "";
        int nonce = 0;
        String phash = "0000";
        String hactual = "";
        bloques aux = mains.gbloque.ret_ultimo();
        if (aux != null) {
            phash = aux.hh;
            index = aux.id + 1;
        }
        
        JSONObject data2 = new JSONObject();
        data2.put("ISBN", isbn);
        
        
        JSONObject data1 = new JSONObject();
        data1.put("ELIMINAR_LIBRO", data2);
        
        nonce=nonce_calc(Integer.toString(index)+tmst+phash+data1.toString());
        //System.out.println(nonce);
        hactual=sha256(Integer.toString(index)+tmst+phash+data1.toString()+Integer.toString(nonce));
        obj.put("INDEX", index);
        obj.put("TIMESTAMP", tmst);
        obj.put("NONCE", nonce);
        obj.put("DATA", data1);
        obj.put("PREVIOUSHASH", phash);
        obj.put("HASH", hactual);
        data=data1.toString();
        
        mains.gbloque.nuevo_bloque(index, tmst, data, nonce, phash, hactual);
        //send to service
        mains.content=obj.toString();
        mains.state=3;
        //System.out.println(obj.toString());
        
    }

    public void libro_crea(int isbn, String titulo, String autor, String editorial, int anio, String edicion, String cat, String idiom, int carnet) {
        JSONObject obj = new JSONObject();
        int index = 0;
        String tmst = "";
        tmst = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss").format(new java.util.Date());
        String data = "";
        int nonce = 0;
        String phash = "0000";
        String hactual = "";
        bloques aux = mains.gbloque.ret_ultimo();
        if (aux != null) {
            phash = aux.hh;
            index = aux.id + 1;
        }
        
        JSONObject data2 = new JSONObject();
        data2.put("ISBN", isbn);
        data2.put("ANIO", anio);
        data2.put("IDIOMA", idiom);
        data2.put("TITULO", titulo);
        data2.put("EDITORIAL", editorial);
        data2.put("AUTOR", autor);
        data2.put("EDICION", edicion);
        data2.put("CARNET", carnet);
        data2.put("CATEGORIA", cat);
        
        JSONObject data1 = new JSONObject();
        data1.put("CREAR_LIBRO", data2);
        
        nonce=nonce_calc(Integer.toString(index)+tmst+phash+data1.toString());
        //System.out.println(nonce);
        hactual=sha256(Integer.toString(index)+tmst+phash+data1.toString()+Integer.toString(nonce));
        obj.put("INDEX", index);
        obj.put("TIMESTAMP", tmst);
        obj.put("NONCE", nonce);
        obj.put("DATA", data1);
        obj.put("PREVIOUSHASH", phash);
        obj.put("HASH", hactual);
        data=data1.toString();
        
        mains.gbloque.nuevo_bloque(index, tmst, data, nonce, phash, hactual);
        //send to service
        mains.state=3;
        mains.content=obj.toString();
    }

    public void recibe_bloque(String cuerpo) {
        //determinaremos el bloque, se comprueba y se envia la data al metodo necesario
        //van existir varios tipos de insert
        //categoria, nuevo elimina
        //usuario, nuevo, elimina, edita
        //libro, nuevo, elimina
        //por ultimo los ingresa la lista de blockchain
        JSONObject obj = new JSONObject(cuerpo);
        
        JSONObject obj2 = new JSONObject(obj.get("DATA").toString());
        if(obj2.has("CREAR_USUARIO")){
            JSONObject obj4 = new JSONObject(obj2.get("CREAR_USUARIO").toString());
            mains.usuario.agregar_usuario(obj4.getInt("CARNET"), obj4.getString("NOMBRE"), obj4.getString("APELLIDO"), obj4.getString("CARRERA"), obj4.getString("PASSWORD"));
        }
        if(obj2.has("ELIMINAR_USUARIO")){
            JSONObject obj4 = new JSONObject(obj2.get("ELIMINAR_USUARIO").toString());
            mains.usuario.drop_usr(obj4.getInt("CARNET"));
        }
        if(obj2.has("EDITAR_USUARIO")){
            JSONObject obj4 = new JSONObject(obj2.get("EDITAR_USUARIO").toString());
            mains.usuario.edit_usr(obj4.getInt("CARNET"), obj4.getString("NOMBRE"), obj4.getString("APELLIDO"), obj4.getString("CARRERA"), obj4.getString("PASSWORD"));
        }
        if(obj2.has("ELIMINAR_CATEGORIA")){
            JSONObject obj4 = new JSONObject(obj2.get("ELIMINAR_CATEGORIA").toString());
            mains.ctegoria.delete(obj4.getString("NOMBRE"));
        }
        if(obj2.has("CREAR_CATEGORIA")){
            JSONObject obj4 = new JSONObject(obj2.get("CREAR_CATEGORIA").toString());
            mains.ctegoria.insert(obj4.getString("NOMBRE"), obj4.getInt("CARNET"));
        }
        if(obj2.has("CREAR_LIBRO")){
            JSONObject obj4 = new JSONObject(obj2.get("CREAR_LIBRO").toString());
            mains.libro_aux.insert_l(obj4.getInt("ISBN"), obj4.getString("TITULO"), obj4.getString("AUTOR"), obj4.getString("EDITORIAL"), obj4.getInt("ANIO"), obj4.getString("EDICION"), obj4.getString("CATEGORIA"), obj4.getString("IDIOMA"), obj4.getInt("CARNET"));
        }
        if(obj2.has("ELIMINAR_LIBRO")){
            JSONObject obj4 = new JSONObject(obj2.get("ELIMINAR_LIBRO").toString());
            mains.libro_aux.del_libro(obj4.getInt("ISBN"));
        }
        //falta lo masivo
        //insertmos el bloque
        mains.gbloque.nuevo_bloque(obj.getInt("INDEX"), obj.getString("TIMESTAMP"), obj.get("DATA").toString(), obj.getInt("NONCE"), obj.getString("PREVIOUSHASH"), obj.getString("HASH"));
        
        
    }

    public int nonce_calc(String us) {
        int n = 0;
        String g = sha256(us+"0");
        while (true) {

            if (g.substring(0, 4).equals("0000")) {
                //System.out.println(n);
                break;
            } else {
                n++;
                g = sha256(us + Integer.toString(n));
            }
        }
        return n;
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
