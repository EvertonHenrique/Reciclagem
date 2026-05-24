package Som;
public class EfeitoSonoro extends SomBase {

    public EfeitoSonoro(String caminho) {
        super(caminho);
        
        carregar();
    }

    @Override
    public void tocar() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
    
    
}
