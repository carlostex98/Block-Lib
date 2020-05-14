package pin;

import java.io.IOException;
import java.io.PrintWriter;

public class Btree {

    String u;
    static int orden = 5;
    public Node root;
    int T = 2;

    Btree() {
        // new root
        root = new Node();
        // assign the root node to be a isLeaf
        root.isLeaf = true;
        root.numberOfNodes = 0;
        // initial the key value in the root to -1 (null)
        root.key[0] = -1;
    }

    public void print() {
        printBtree(root, "");
    }

    public void printBtree(Node node, String indent) {
        if (node == null) {
            System.out.println(indent + "The B-Tree is Empty");
        } else {
            System.out.println(indent + " ");

            // declare a string variable
            String childIndent = indent + "\t";

            // for loop to print the B-Tree, recursively print the B-Tree Strucure
            for (int i = node.numberOfNodes - 1; i >= 0; i--) {
                if (!node.isLeaf) // Recursive Call
                {
                    printBtree(node.children[i], childIndent);
                }
                // print the keys
                if (node.key[i] > 0) {
                    System.out.println(childIndent + node.key[i]);
                }
            }
            if (!node.isLeaf) // Recirsive Call
            {
                printBtree(node.children[node.numberOfNodes], childIndent);
            }
        }
    }

    public void Insertx(int key) {
        arregla_raiz();
        Node r = root;
        if (r.numberOfNodes == 2 * T - 1) {
            Node s = new Node();
            root = s;
            s.isLeaf = false;
            s.numberOfNodes = 0;
            s.children[0] = r;
            Split(s, 0, r);
            _Insert(s, key);
        } else {
            _Insert(r, key);
        }
    }

    public void arregla_raiz(){
        if(root==null){
            root=new Node();
        }
    }
    
    
    final private void _Insert(Node x, int k) {

        if (x.isLeaf) {
            int i = x.numberOfNodes - 1;

            for (i = x.numberOfNodes - 1; i >= 0 && x.key[i] > k; i--) {

                x.key[i + 1] = x.key[i];

            }
            x.key[i + 1] = k;
            x.numberOfNodes = x.numberOfNodes + 1;
        } else {
            int i = 0;
            for (i = x.numberOfNodes - 1; i >= 0 && k < x.key[i]; i--) {
            }
            ;
            i++;
            Node tmp = x.children[i];
            if (tmp.numberOfNodes == 2 * T - 1) {
                Split(x, i, tmp);
                if (k > x.key[i]) {
                    i++;
                }
            }
            _Insert(x.children[i], k);
        }

    }

    private void Split(Node x, int pos, Node y) {
        Node z = new Node();
        z.isLeaf = y.isLeaf;
        z.numberOfNodes = T - 1;
        for (int j = 0; j < T - 1; j++) {
            z.key[j] = y.key[j + T];
        }
        if (!y.isLeaf) {
            for (int j = 0; j < T; j++) {
                z.children[j] = y.children[j + T];
            }
        }
        y.numberOfNodes = T - 1;
        for (int j = x.numberOfNodes; j >= pos + 1; j--) {
            x.children[j + 1] = x.children[j];
        }
        x.children[pos + 1] = z;

        for (int j = x.numberOfNodes - 1; j >= pos; j--) {
            x.key[j + 1] = x.key[j];
        }
        x.key[pos] = y.key[T - 1];
        x.numberOfNodes = x.numberOfNodes + 1;
    }


    
    public void nombrar(Node n){
        n.name="node";
        for (int i = 0; i < n.numberOfNodes; i++) {
            //System.out.print(n.getValue(i) + " ");
            n.name+=Integer.toString(n.getValue(i));
        }

        if (!n.isLeaf) {
            for (int j = 0; j <= n.numberOfNodes; j++) {
                if (n.getChild(j) != null) {
                    //System.out.println();
                    nombrar(n.getChild(j));
                }
            }
        }
    }
    public void crea_nodos(Node n){
        u+=n.name+"[label=\"";
        for (int i = 0; i < n.numberOfNodes; i++) {
            if(i==n.numberOfNodes-1){
                u+="<f"+Integer.toString(i)+">|"+Integer.toString(n.getValue(i))+"\\n"+mains.libro_aux.ret_nombre(n.getValue(i))+"|<f"+Integer.toString(i+1)+">";
            }else{
                u+="<f"+Integer.toString(i)+">|"+Integer.toString(n.getValue(i))+"\\n"+mains.libro_aux.ret_nombre(n.getValue(i))+"|";
            }
            
        }
        u+="\"];\n";
        if (!n.isLeaf) {
            for (int j = 0; j <= n.numberOfNodes; j++) {
                if (n.getChild(j) != null) {
                    //System.out.println();
                    crea_nodos(n.getChild(j));
                }
            }
        }
    }
    public void relaciones(Node n){
        //aca esta lo chido de las relaciones ajajaj
        //u+=n.name;
        for (int i = 0; i <= n.numberOfNodes; i++) {
            //idk
            if(n.getChild(i)!=null){
                u+=n.name+":f"+Integer.toString(i)+"->"+n.getChild(i).name+";\n";
            }
        }
        if (!n.isLeaf) {
            for (int j = 0; j <= n.numberOfNodes; j++) {
                if (n.getChild(j) != null) {
                    //System.out.println();
                    relaciones(n.getChild(j));
                }
            }
        }
    }
    
    
    public void graficar_b() throws InterruptedException{
        u="";
        nombrar(root);
        
        //System.out.println(u);
        try{
            PrintWriter writer = new PrintWriter("arbol_b.dot", "UTF-8");
            writer.println("digraph sls{");
            writer.println("node [shape = record,height=.1];");
            u="";
            crea_nodos(root);
            writer.println(u);
            u="";
            relaciones(root);
            writer.println(u);
            u="";
            writer.println("}");
            writer.close();
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("dot -Tjpg arbol_b.dot -o arbol_b.jpg");
            Thread.sleep(1000);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
