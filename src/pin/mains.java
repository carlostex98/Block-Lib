package pin;

public class mains {
    public static m_usuarios usuario=new m_usuarios();
    public static usuarios nop=new usuarios();
    
    public static void main(String[] args){
        //vars
        usuario.llenado(45);
        usuario.agregar_usuario(201700317, "Carlos", "Tenes", "Ciencias y Sistemas", "1234");
        logueo loguex=new logueo();
        loguex.setVisible(true);
        
        
        
        
    }
}
