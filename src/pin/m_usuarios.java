package pin;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class m_usuarios {

    //varibles necesarias que apunta a nuestras listas
    indice_usuarios pi_usuarios;
    indice_usuarios ui_usuarios;
    int tmx;

    public m_usuarios() {
        //llenado(45);//de 0...44===45 indices
        //tmx=45;
    }

    void llenado(int indices) {
        tmx = indices;
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
        nuevo_usr.carnet = carnet;
        nuevo_usr.nombre = nombre;
        nuevo_usr.apellido = apellido;
        nuevo_usr.carrera = carrera;
        nuevo_usr.psw = getMd5(pass);
        //manejamos la colision
        int md = carnet % tmx;

        indice_usuarios actual = ret_indice(md);

        if (!existee(carnet)) {
            if (actual.primero_usr == null) {
                actual.primero_usr = nuevo_usr;
                actual.ultimo_usr = nuevo_usr;
            } else {
                actual.ultimo_usr.siguiente = nuevo_usr;
                actual.ultimo_usr = nuevo_usr;
            }
        }

    }

    void agregar_sinhash(int carnet, String nombre, String apellido, String carrera, String pass) {
        usuarios nuevo_usr = new usuarios();
        nuevo_usr.carnet = carnet;
        nuevo_usr.nombre = nombre;
        nuevo_usr.apellido = apellido;
        nuevo_usr.carrera = carrera;
        nuevo_usr.psw = pass;
        //manejamos la colision
        int md = carnet % tmx;
        indice_usuarios actual = ret_indice(md);
        if (!existee(carnet)) {
            if (actual.primero_usr == null) {
                actual.primero_usr = nuevo_usr;
                actual.ultimo_usr = nuevo_usr;
            } else {
                actual.ultimo_usr.siguiente = nuevo_usr;
                actual.ultimo_usr = nuevo_usr;
            }
        }
    }

    usuarios existe_usr_loguin(int carnet, String pass) {
        //boolean n=false;
        String hpass = getMd5(pass);
        int md = carnet % tmx;
        usuarios actual = ret_indice(md).primero_usr;
        usuarios encontrado = null;
        while (actual != null) {
            if (actual.carnet == carnet && actual.psw.equals(hpass)) {
                encontrado = actual;
                break;
            } else {
                actual = actual.siguiente;
            }
        }
        return encontrado;
    }

    public boolean existee(int carnet) {
        boolean n = false;
        int md = carnet % tmx;
        usuarios actual = ret_indice(md).primero_usr;
        usuarios encontrado = null;
        while (actual != null) {
            if (actual.carnet == carnet) {
                encontrado = actual;
                n = true;
                break;
            } else {
                actual = actual.siguiente;
            }
        }
        return n;
    }

    public void edit_usr(int carnet, String nombre, String apellido, String carrera, String pass) {
        String hpass = getMd5(pass);
        int md = carnet % tmx;
        usuarios actual = ret_indice(md).primero_usr;
        while (actual != null) {
            if (actual.carnet == carnet) {
                //editamos
                actual.nombre = nombre;
                actual.apellido = apellido;
                actual.carrera = carrera;
                if (pass.length() != 0) {
                    actual.psw = getMd5(pass);
                }
                break;
            } else {
                actual = actual.siguiente;
            }
        }
    }

    public void drop_usr(int carnet) {
        int md = carnet % tmx;
        usuarios a = null;
        usuarios b = null;
        usuarios actual = ret_indice(md).primero_usr;
        indice_usuarios perf = ret_indice(md);
        while (actual != null) {
            if (actual.carnet == carnet) {
                //eliminamos
                if (actual.carnet == perf.primero_usr.carnet) {//primero
                    perf.primero_usr = actual.siguiente;
                } else if (actual.carnet == perf.ultimo_usr.carnet) {//ultimo
                    a.siguiente = null;
                    perf.ultimo_usr = a;
                } else {//en medio
                    b = actual.siguiente;
                    a.siguiente = b;
                    actual.siguiente = null;
                }
                break;
            } else {
                a = actual;
                actual = actual.siguiente;
            }
        }
    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    indice_usuarios ret_indice(int i) {
        indice_usuarios vista = pi_usuarios;
        while (true) {
            if (vista.indice == i) {
                break;
            } else {
                vista = vista.siguiente;
            }
        }

        return vista;
    }

    public void reporte_hash() throws InterruptedException {
        indice_usuarios vista = pi_usuarios;
        usuarios n;
        //primero los indices
        try {
            PrintWriter writer = new PrintWriter("hasheo.dot", "UTF-8");
            writer.write("digraph {\n");
            writer.write("node [shape=box];\n ");
            writer.write("e0[ shape = point, width = 0 ];\n" + "e1[ shape = point, width = 0 ]; \n");
            while (vista != null) {
                writer.write("s" + vista.indice + "i [label=\" " + Integer.toString(vista.indice) + " \" group = 1];\n");

                if (vista.siguiente != null) {
                    writer.write("s" + vista.indice + "i ->s" + Integer.toString(vista.siguiente.indice) + "i;\n");
                }
                //ahora las relaciones de los usuarios
                n = vista.primero_usr;
                int i = 2;
                String z = "";
                if (n != null) {
                    writer.write("s" + vista.indice + "i -> u" + Integer.toString(n.carnet) + ";\n");
                    z = "{rank=same; s" + Integer.toString(vista.indice) + "i ;";
                }
                while (n != null) {
                    writer.write("u" + Integer.toString(n.carnet) + "[label=\"" + n.nombre + " " + n.apellido + "\\n" + Integer.toString(n.carnet) + "\\n" + n.psw + "  \" group = " + Integer.toString(i) + " ];");
                    z += "u" + Integer.toString(n.carnet) + " ;";
                    if (n.siguiente != null) {
                        writer.write("u" + Integer.toString(n.carnet) + "-> u" + Integer.toString(n.siguiente.carnet)+";\n");
                    }
                    n = n.siguiente;
                    i++;
                }
                //añadinos el rank same
                if (!z.equals("")) {
                    z += " }\n";
                    writer.write(z);
                }
                vista = vista.siguiente;
            }
            writer.write("}\n");
            writer.close();

            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("dot -Tjpg hasheo.dot -o hasheo.jpg");
            Thread.sleep(1000);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
