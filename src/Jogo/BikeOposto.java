package jogo;

import java.awt.*;
import javax.swing.ImageIcon;

public class BikeOposto {
    public int x1 = -100; // Começa na esquerda
    public int y = 370;   // Faixa inferior (para não bater na outra bike)
    private ImageIcon img;

    public BikeOposto() {
        java.net.URL url = getClass().getResource("/foto/homem_bike3.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void mover() {
        x1 += 2; // //velocidade da bike
        if (x1 > 720) { 
            x1 = -100; // Reseta na esquerda
        }
    }

    public void desenhar(Graphics g) {
        if (img != null) {
            g.drawImage(img.getImage(), x1, y, 80, 40, null);
        } else {
            g.setColor(Color.BLUE); 
            g.fillRect(x1, y, 80, 40);
        }
    }

    public Rectangle getBounds() { 
        return new Rectangle(x1, y, 80, 40); 
    }
}
