package pin;

import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONArray;


public class m_clientes {

    cliente primero;
    cliente ultimo;

    public m_clientes() {

    }

    public void agregar(String ip) {
        cliente nuevo = new cliente();
        nuevo.ip = ip;
        if (primero == null) {
            primero = ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        }
    }

    public void elim_cliente(String ip) {
        cliente aux = primero;

        while (aux != null) {
            if(aux.ip.equals(ip)){
                //encontrado
                if(aux==primero){
                    if(aux==ultimo){
                        primero=ultimo=null;
                    }else{
                        primero=primero.siguiente;
                        primero.anterior=null;
                    }
                }else if(aux==ultimo){
                    if(aux==primero){
                        primero=ultimo=null;
                    }else{
                        ultimo=ultimo.anterior;
                        ultimo.siguiente=null;
                    }
                }else{
                    //en medio
                    aux.anterior.siguiente=aux.siguiente;
                    aux.siguiente.anterior=aux.anterior;
                    aux.anterior=aux.siguiente=null;
                }
                break;
            }else{
                aux=aux.siguiente;
            }
        }
    }
    
    public void reporte_clientes() throws InterruptedException{
        try{
            PrintWriter writer = new PrintWriter("online.dot", "UTF-8");
            writer.println("digraph sls{");
            writer.println("node [shape=record];");
            cliente aux = primero;
            while(aux != null){
                writer.println("ip"+rmp(aux.ip)+" [label=\" "+aux.ip+" \"];");
                if(aux.siguiente!=null){
                    writer.println("ip"+rmp(aux.ip)+" -> ip"+rmp(aux.siguiente.ip)+"; \n");
                }
            }
            writer.println("}");
            writer.close();
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("dot -Tjpg online.dot -o online.jpg");
            Thread.sleep(1000);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String rmp(String ed){
        String ff="";
        ff=ed.replace(".", "");
        return ff;
    }
    
    public void read_clientes(String gt){
        primero=ultimo=null;
        JSONArray arr = new JSONArray(gt);
        for (int i = 0; i < arr.length(); i++) {
            String clt = arr.getJSONObject(i).getString("ip");//int
            agregar(clt);
        }
    }

}
