package pin;

public class Btree {

    static int orden;
    BNode root;

    public Btree() {
        this.orden = 5;
        root = new BNode(5, null);

    }
    public void set_root(BNode r){
        root=r;
    }

    public BNode busca(BNode root, int key) {
        int i = 0;
        while (i < root.cuenta && key > root.isbn[i]) {
            i++;
        }
        if (i <= root.cuenta && key == root.isbn[i]) {
            return root;
        }
        if (root.hoja) {
            return null;
        } else {
            return busca(root.getChild(i), key);
        }
    }

    public void splitea(BNode x, int i, BNode y) {
        BNode z = new BNode(orden, null);//gotta have extra node if we are
        //to split.

        z.hoja = y.hoja;//set boolean to same as y

        z.cuenta = orden - 1;//this is updated size

        for (int j = 0; j < orden - 1; j++) {
            z.isbn[j] = y.isbn[j + orden]; //copy end of y into front of z

        }
        if (!y.hoja)//if not leaf we have to reassign child nodes.
        {
            for (int k = 0; k < orden; k++) {
                z.hijo[k] = y.hijo[k + orden];
            }
        }
        y.cuenta = orden - 1;

        for (int j = x.cuenta; j > i; j--) {
            x.hijo[j + 1] = x.hijo[j];
        }
        x.hijo[i + 1] = z;

        for (int jp = x.cuenta; jp > i; jp--) {
            x.isbn[jp + 1] = x.isbn[jp];
        }
        x.isbn[i] = y.isbn[orden - 1];
        y.isbn[orden - 1] = 0;

        for (int j = 0; j < orden - 1; j++) {
            y.isbn[j + orden] = 0;
        }
        x.cuenta++;
    }

    public void nonfullInsert(BNode pp, int is) {
        int i = pp.cuenta;
        if (pp.hoja) {
            while (i >= 1 && is < pp.isbn[i - 1]) {
                pp.isbn[i] = pp.isbn[i - 1];
                i--;
            }
            pp.isbn[i] = is;
            pp.cuenta++;
        } else {
            int j = 0;
            while (j < pp.cuenta && is > pp.isbn[j]) {
                j++;
            }
            if (pp.hijo[j].cuenta == orden * 2 - 1) {
                splitea(pp, j, pp.hijo[j]);
                if (is > pp.isbn[j]) {
                    j++;
                }
            }
            nonfullInsert(pp.hijo[j], is);
        }
    }

    public void in_lib(Btree t, int is) {
        BNode r = t.root;
        if (r.cuenta == 2 * orden - 1) {
            BNode s = new BNode(orden, null);
            t.root = s;
            s.hoja = false;
            s.cuenta = 0;
            s.hijo[0] = r;
            splitea(s, 0, r);
            nonfullInsert(s, is);
        } else {
            nonfullInsert(r, is);
        }
    }

    public void print(BNode n) {
        for (int i = 0; i < n.cuenta; i++) {
            System.out.print(n.getValue(i) + " ");
        }

        if (!n.hoja) {
            for (int j = 0; j <= n.cuenta; j++) {
                if (n.getChild(j) != null) {
                    System.out.println();
                    print(n.getChild(j));
                }
            }
        }
    }

    public void SearchPrintNode(Btree T, int x) {
        BNode temp = new BNode(orden, null);

        temp = busca(T.root, x);

        if (temp == null) {

            System.out.println("The Key does not exist in this tree");
        } else {

            print(temp);
        }

    }

    public void deleteKey(Btree t, int is) {

        BNode temp = new BNode(orden, null);
        temp = busca(t.root, is);
        if (temp.hoja && temp.cuenta > orden - 1) {
            int i = 0;

            while (is > temp.getValue(i)) {
                i++;
            }
            for (int j = i; j < 2 * orden - 2; j++) {
                temp.isbn[j] = temp.getValue(j + 1);
            }
            temp.cuenta--;
        } else {
            System.out.println("This node is either not a leaf or has less than order - 1 keys.");
        }
    }
    
    public void delt_isb(Btree t, int is){
        
    }
    
    public void insertar(int isbn){
        //in_lib(root,isbn);
    }
    
}
