
package Som;
import javax.sound.sampled.*;
import java.net.URL;
public class MusicaFundo extends SomBase {

    public MusicaFundo(String caminho) {
        super(caminho);
        
        carregar();
    }



    @Override
    public void tocar() {
       if (clip != null){
           clip.setFramePosition(0);
           clip.loop(Clip.LOOP_CONTINUOUSLY);
           
       }
        
    }
    
    
}
