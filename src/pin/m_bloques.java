package pin;

import java.io.IOException;
import java.io.PrintWriter;

public class m_bloques {

    bloques primero;
    bloques ultimo;
    int x = 0;

    public void nuevo_bloque(int index, String tmt, String data, int non, String p_hash, String hash) {
        bloques nuevo = new bloques();
        nuevo.id = index;
        nuevo.time = tmt;
        nuevo.data = data;
        nuevo.nonce = non;
        nuevo.phash = p_hash;
        nuevo.hh = hash;
        if (primero == null) {
            primero = ultimo = nuevo;
        } else {
            ultimo.sig = nuevo;
            ultimo = nuevo;
        }

    }

    public bloques ret_ultimo() {
        return ultimo;
    }

    public void reporte() throws InterruptedException {

        try {
            PrintWriter writer = new PrintWriter("bloques.dot", "UTF-8");
            writer.println("digraph sls{");
            writer.println("node [shape=record];");
            bloques aux = primero;
            while (aux != null) {
                String data="id: "+Integer.toString(aux.id)+"\\n";
                data+="Timestamp: "+aux.time+"\\n";
                data+="Nonce: "+Integer.toString(aux.nonce)+"\\n";
                data+="Previous hash: "+aux.phash+"\\n";
                data+="Hash: "+aux.hh;
                writer.println("ip" + Integer.toString(aux.id) + " [label=\" " + data + " \"];");
                if (aux.sig != null) {
                    writer.println("ip" + Integer.toString(aux.id) + " -> ip" + Integer.toString(aux.sig.id) + "; \n");
                }
                aux=aux.sig;
            }
            writer.println("}");
            writer.close();
            //genera grafo
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("dot -Tjpg bloques.dot -o bloques.jpg");
            Thread.sleep(1000);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
