package pin;

public class mains {

    public static m_usuarios usuario = new m_usuarios();
    public static usuarios nop = new usuarios();
    public static m_categorias ctegoria = new m_categorias();
    public static Btree libros = new Btree();
    public static lisb libro_aux = new lisb();
    public static int state = 0;
    public static String action = "";
    public static String content = "";
    public static String img = "";
    public static categoria cat_curr = null;

    public static void main(String[] args) throws InterruptedException {
        //vars
        usuario.llenado(45);
        usuario.agregar_usuario(201700317, "Carlos", "Tenes", "Ciencias y Sistemas", "1234");
        ctegoria.insert("tacubaya", 201700317);
        ctegoria.insert("tlacuache", 201700317);
        ctegoria.insert("pompom", 201700317);
        ctegoria.insert("pompeya", 201700317);
        logueo loguex = new logueo();
        loguex.setVisible(true);
        
        //ctegoria.setRoot("pompom");
        libros.Insertx(10);
        libros.Insertx(15);
        //libros.print(libros.root);
        //libros.root=new Node();
        
        //ctegoria.setRoot("pompeya");
        libros.Insertx(20);
        libros.Insertx(1);
        libros.Insertx(3);
        libros.Insertx(39);
        //cat_curr.raiz=libros.root;
        libros.print();
        //libros.print(libros.root);
        libros.graficar_b();
        
        //System.out.println("-----------------");
        //ctegoria.setRoot("pompom");
        //libros.print(libros.root);
        
        /*Btree tree = new Btree();
        tree.in_lib(tree, 10);
        tree.in_lib(tree, 1);
        tree.in_lib(tree, 109);
        tree.root=new BNode(null);
        tree.in_lib(tree, 20);
        tree.in_lib(tree, 15);
        tree.in_lib(tree, 18);
        tree.in_lib(tree, 9);
        tree.print(tree.root);
        
        while(true){
            System.out.println("ja");
            Thread.sleep(1000);
        }*/
        

    }
}
