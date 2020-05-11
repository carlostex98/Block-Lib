package pin;

import java.io.IOException;
import java.io.PrintWriter;

public class m_categorias {
    categoria root;
    String ee="";
    public m_categorias(){
        root=null;
    }
    
    
    public categoria find(String nombre) {
        categoria current = root;
        while (current != null) {
            if (current.nombre == nombre) {
               break;
            }
            int compare = current.nombre.compareTo(nombre);  
            current = compare < 0 ? current.der : current.izq;
        }
        return current;
    }
    public void insert(String nombre, int carnet) {
        root = insert(root, nombre, carnet);
    }
    public void delete(String nombre) {
        root = delete(root, nombre);
    }
    public categoria getRoot() {
        return root;
    }
    public int height() {
        return root == null ? -1 : root.height;
    }
    private categoria insert(categoria node, String nombre, int carnet) {
        if (node == null) {
            return new categoria(nombre,carnet);
        } else if (node.nombre.compareTo(nombre) > 0) {
            node.izq = insert(node.izq, nombre, carnet);
        } else if (node.nombre.compareTo(nombre) < 0) {
            node.der = insert(node.der, nombre, carnet);
        } else {
            //llave duplicada
        }
        return rebalance(node);
    }
    
    private categoria delete(categoria node, String nombre) {
        if (node == null) {
            return node;
        } else if (node.nombre.compareTo(nombre) > 0) {
            node.izq = delete(node.izq, nombre);
        } else if (node.nombre.compareTo(nombre) < 0) {
            node.der = delete(node.der, nombre);
        } else {
            if (node.izq == null || node.der == null) {
                node = (node.izq == null) ? node.der : node.izq;
            } else {
                categoria mostLeftChild = mostLeftChild(node.der);
                node.nombre = mostLeftChild.nombre;
                node.der = delete(node.der, node.nombre);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }
    private categoria mostLeftChild(categoria node) {
        categoria current = node;
        /* loop down to find the leftmost leaf */
        while (current.izq != null) {
            current = current.izq;
        }
        return current;
    }
    private categoria rebalance(categoria z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.der.der) > height(z.der.izq)) {
                z = rotateLeft(z);
            } else {
                z.der = rotateRight(z.der);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.izq.izq) > height(z.izq.der)) {
                z = rotateRight(z);
            } else {
                z.izq = rotateLeft(z.izq);
                z = rotateRight(z);
            }
        }
        return z;
    }
    private categoria rotateRight(categoria y) {
        categoria x = y.izq;
        categoria z = x.der;
        x.der = y;
        y.izq = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    private categoria rotateLeft(categoria y) {
        categoria x = y.der;
        categoria z = x.izq;
        x.izq = y;
        y.der = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    private void updateHeight(categoria n) {
        n.height = 1 + Math.max(height(n.izq), height(n.der));
    }
    private int height(categoria n) {
        return n == null ? -1 : n.height;
    }
    public int getBalance(categoria n) {
        return (n == null) ? 0 : height(n.der) - height(n.izq);
    }
    
    public void reporte_avl(){
        
        try{
            PrintWriter writer = new PrintWriter("avl.dot", "UTF-8");
            writer.println("digraph sls{");
            writer.println("node [shape=record];");
            ee="";
            nodosAvl(root);
            writer.println(ee);
            ee="";
            //ahora las relaciones
            relAvl(root);
            writer.println(ee);
            ee="";
            writer.println("}");
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void nodosAvl(categoria n){
        if(n!=null){
            ee += n.nombre+"[label=\"<C0>|" + n.nombre + "|<C1> \" ]; \n";   
            nodosAvl(n.der);
            nodosAvl(n.izq);
        }
    }
    public void relAvl(categoria r) {
        if (r != null) {
            //data
            if (r.der != null) {
                ee += r.nombre+ ":C1->" + r.der.nombre + "; \n";
            }
            if (r.izq != null) {
                ee += r.nombre+ ":C0->" + r.izq.nombre + "; \n";
            }
            relAvl(r.izq);
            relAvl(r.der);
        }
    }
    
}
