package pin;

public class BNode {

    static int t;  //orden
    int cuenta; // claves
    int isbn[];  // array de valores, sin guiones, se añden despues
    BNode hijo[]; //hijos referencia
    boolean hoja; //hoja o no
    BNode parent;  //le pariente, si!, que elegancia la de francia

    public BNode() {
    }

    public BNode(int t, BNode parent) {
        this.t = t;
        this.parent = parent;
        isbn = new int[2 * t - 1];
        hijo = new BNode[2 * t];
        hoja = true;
        cuenta = 0;
    }

    public int getValue(int i) {
        return isbn[i];
    }

    public BNode getChild(int i) {
        return hijo[i];
    }

}
