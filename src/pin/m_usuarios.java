package pin;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class m_usuarios {

    //varibles necesarias que apunta a nuestras listas
    indice_usuarios pi_usuarios;
    indice_usuarios ui_usuarios;
    int tmx;

    public m_usuarios() {
        llenado(45);//de 0...44===45 indices
        tmx=45;
    }

    void llenado(int indices) {
        for (int i = 0; i < indices; i++) {
            add_indice(i);
        }
    }

    void add_indice(int i) {
        indice_usuarios nuevo = new indice_usuarios();
        nuevo.indice = i;
        if (pi_usuarios == null) {
            pi_usuarios = nuevo;
            ui_usuarios = nuevo;
        } else {
            ui_usuarios.siguiente = nuevo;
            ui_usuarios = nuevo;
        }
    }

    void agregar_usuario(int carnet, String nombre, String apellido, String carrera, String pass) {
        usuarios nuevo_usr = new usuarios();
        nuevo_usr.carnet=carnet;
        nuevo_usr.nombre=nombre;
        nuevo_usr.apellido=apellido;
        nuevo_usr.carrera=carrera;
        nuevo_usr.psw=getMd5(pass);
        //manejamos la colision
        int md=carnet%tmx;
        indice_usuarios actual=ret_indice(md);
        if(actual.primero_usr==null){
            actual.primero_usr=nuevo_usr;
            actual.ultimo_usr=nuevo_usr;
        }else{
            actual.ultimo_usr.siguiente=nuevo_usr;
            actual.ultimo_usr=nuevo_usr;
        }
    }
    
    boolean existe_usr_loguin(int carnet, String pass){
        boolean n=false;
        String hpass=getMd5(pass);
        int md=carnet%tmx;
        usuarios actual=ret_indice(md).primero_usr;
        while(actual!=null){
            if(actual.carnet==carnet && actual.psw.equals(hpass)){
                n=true;
                break;
            }else{
                actual=actual.siguiente;
            }
        }
        return n;
    }
    
    public void edit_usr(int carnet, String nombre, String apellido, String carrera, String pass){
        String hpass=getMd5(pass);
        int md=carnet%tmx;
        usuarios actual=ret_indice(md).primero_usr;
        while(actual!=null){
            if(actual.carnet==carnet){
                //editamos
                actual.nombre=nombre;
                actual.apellido=apellido;
                actual.carrera=carrera;
                actual.psw=getMd5(pass);
                break;
            }else{
                actual=actual.siguiente;
            }
        }
    }
    
    public void drop_usr(int carnet){
        int md=carnet%tmx;
        usuarios a=null;
        usuarios b=null;
        usuarios actual=ret_indice(md).primero_usr;
        indice_usuarios perf=ret_indice(md);
        while(actual!=null){
            if(actual.carnet==carnet){
                //eliminamos
                
                break;
            }else{
                actual=actual.siguiente;
            }
        }
    }
    
    public static String getMd5(String input) 
    { 
        try {            
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
    indice_usuarios ret_indice(int i){
       indice_usuarios vista=pi_usuarios;
       while(true){
           if(vista.indice==i){
               break;
           }else{
               vista=vista.siguiente;
           }
       }
       
       return vista;
    }
    
}
