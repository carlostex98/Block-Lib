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
    
    public void splitea(BNode x, int i, BNode y){
		BNode z = new BNode(orden,null);//gotta have extra node if we are
	                					//to split.

		z.hoja = y.hoja;//set boolean to same as y

		z.cuenta = orden - 1;//this is updated size

		for(int j = 0; j < orden - 1; j++)
		{
			z.isbn[j] = y.isbn[j+orden]; //copy end of y into front of z

		}
		if(!y.hoja)//if not leaf we have to reassign child nodes.
		{
                    for(int k = 0; k < orden; k++){
                            z.hijo[k] = y.hijo[k+orden];
                    }
		}
		y.cuenta = orden - 1; 

		for(int j = x.cuenta ; j> i ; j--){
                    x.hijo[j+1] = x.hijo[j];
		}
		x.hijo[i+1] = z;

		for(int jp = x.cuenta; jp> i; jp--){
			x.isbn[jp + 1] = x.isbn[jp];
		}
		x.isbn[i] = y.isbn[orden-1];
		y.isbn[orden-1] = 0;

		for(int j = 0; j < orden - 1; j++){
			y.isbn[j + orden] = 0;
		}
		x.cuenta ++;
	}

    
}
