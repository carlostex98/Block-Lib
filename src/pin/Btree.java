package pin;

public class Btree {

    static int orden;
    BNode root;

    public Btree(int orden) {
        this.orden = orden;
        root = new BNode(orden, null);

    }

    public BNode busca(BNode root, int key) {
        int i = 0;
        while (i < root.cuenta && key > root.isbn[i]){
            i++;
        }
        if (i <= root.cuenta&& key == root.isbn[i]){
            return root;
        }
        if (root.hoja){
            return null;
        }else{
            return busca(root.getChild(i), key);
        }
    }
}
