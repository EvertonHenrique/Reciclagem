package jogo;

import java.awt.*;
import javax.swing.ImageIcon;

public class Bike {
    public int x1 = 800; // Começa na direita
    public int y = 340;  // Faixa superior
    private ImageIcon img;

    public Bike() {
        java.net.URL url = getClass().getResource("/foto/mulher_bike2.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void mover() {
        x1 -= 2; // //velocidade da bike
        if (x1 < -100) { 
            x1 = 800; // Reseta na direita
        }
    }

    public void desenhar(Graphics g) {
        if (img != null) {
            g.drawImage(img.getImage(), x1, y, 60, 30, null);
        } else {
            g.setColor(Color.RED); 
            g.fillRect(x1, y, 60, 30);
        }
    }

    public Rectangle getBounds() { 
        return new Rectangle(x1, y, 60, 30); 
    }
}

