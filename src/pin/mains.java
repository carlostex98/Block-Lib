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
        
        /*ctegoria.setRoot("pompom");
        libros.in_lib(libros, 12378);
        libros.in_lib(libros, 1208);
        libros.print(libros.root);
        cat_curr.raiz=libros.root;
        
        ctegoria.setRoot("pompeya");
        libros.in_lib(libros, 13);
        libros.in_lib(libros, 123);
        libros.print(libros.root);
        
        System.out.println("-----------------");
        ctegoria.setRoot("pompom");
        libros.print(libros.root);
        
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
