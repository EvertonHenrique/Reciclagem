package jogo;
import java.awt.*;
import javax.swing.ImageIcon;

public class InimigoSuperior {
    public int x1 = -130, x2 = 850;
    private ImageIcon img1;
    private ImageIcon img2;

    public InimigoSuperior() {
        java.net.URL url = getClass().getResource("/foto/carro2.png");
        if (url != null) img1 = new ImageIcon(url);
        
        java.net.URL url2 = getClass().getResource("/foto/carro1.png");
        if (url2 != null) img2 = new ImageIcon(url2);
    }

    public void mover() {
        x1 += 4; 
        if (x1 > 850) x1 = -130;
        
        x2 -= 4;
        if (x2 < -130) x2 = 850;
    }

    public void desenhar(Graphics g) {
        if (img1 != null) {
            g.drawImage(img1.getImage(), x1, 150, 100, 30, null); //posicionamento e alturua e largura
            
        } else {
            g.setColor(Color.RED); g.fillRect(x1, 150, 100, 30);
        }
        
        if (img2 != null) {
            g.drawImage(img2.getImage(), x2, 280, 100, 30, null); //posicionamento e alturua e largura
            
        } else {
            g.setColor(Color.RED); g.fillRect(x2, 280, 100, 30);
        }
    }

    public Rectangle getBounds() { return new Rectangle(x1, 150, 100, 30); }
    public Rectangle getBounds2() { return new Rectangle(x2, 280, 100, 30); }
}
