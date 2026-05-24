// Base dos Sons
package Som;
import java.net.URL;
import javax.sound.sampled.*;
public abstract class SomBase {
    protected Clip clip;
    private String caminho;

    public SomBase(String caminho) {
        this.caminho = caminho;
    }
    
    
    
    protected void carregar(){
        try {
            URL url = getClass().getResource(this.getCaminho());
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            
            clip = AudioSystem.getClip();
            clip.open(audio);
           
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public abstract void tocar();
    
    public void parar(){
        if (clip != null){
            clip.stop();
        }
    }
    
    

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    
    
}
