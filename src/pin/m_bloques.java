
package pin;
public class m_bloques {
    bloques primero;
    bloques ultimo;
    int x=0;
    public void nuevo_bloque(int index, String tmt, String data, int non, String p_hash, String hash){
        bloques nuevo = new bloques();
        nuevo.id=index;
        nuevo.time=tmt;
        nuevo.data=data;
        nuevo.nonce=non;
        nuevo.phash=p_hash;
        nuevo.hh=hash;
        if(primero==null){
            primero=ultimo=nuevo;
        }else{
            ultimo.sig=nuevo;
            ultimo=nuevo;
        }
        
    }
    public bloques ret_ultimo(){
        return ultimo;
    }
    
    
}
