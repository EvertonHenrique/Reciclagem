package jogo;
import java.awt.*;
import javax.swing.ImageIcon;

public class InimigoInferior {
    public int x1 = -100, x2 = 800;
    private ImageIcon img1;
    private ImageIcon img2;

    public InimigoInferior() {
        java.net.URL url1 = getClass().getResource("/foto/carro2.png"); //imagem
        if (url1 != null) img1 = new ImageIcon(url1);
        
        java.net.URL url2 = getClass().getResource("/foto/carro1.png"); //imagem
        if (url2 != null) img2 = new ImageIcon(url2);
        
        
    }
    
    //velocidade do carro e coodernadas
    public void mover() {
         
        x1 += 4;
        if (x1 > 800) x1 = -130;
        
        x2 -= 4;
        if (x2 < -130) x2 = 800;
    }

    public void desenhar(Graphics g) {
        if (img1 != null) {
            g.drawImage(img1.getImage(), x1, 450, 100, 30, null); //posicionamento e alturua e largura
            
        } else {
            g.setColor(Color.BLUE);  //caso tiver um  erro na imagem
            g.fillRect(x1, 450, 100, 30);
            
        }
        
        if (img2 != null) {
            
            g.drawImage(img2.getImage(), x2, 570, 100, 30, null); //posicionamento e alturua e largura
        } else {
            g.setColor(Color.BLUE); //caso tiver um  erro na imagem
            
            g.fillRect(x2, 570, 100, 30);
        }
    }

    public Rectangle getBounds() { return new Rectangle(x1, 450, 130, 30); }
    public Rectangle getBounds2() { return new Rectangle(x2, 570, 130, 30); }
}
