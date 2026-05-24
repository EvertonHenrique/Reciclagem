package jogo;
import java.awt.*;
import javax.swing.ImageIcon;

public class Lata_metal {
    public int x = 120, y = 28; 
    private ImageIcon img;

    public Lata_metal(Jogo jogo) {
        java.net.URL url = getClass().getResource("/foto/lata_metal.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void desenhar(Graphics g) {
        if (img != null) g.drawImage(img.getImage(), x, y, 60, 70, null); //posicionamento e alturua e largura
        else { g.setColor(Color.YELLOW); g.fillRect(x, y, 60, 70); }
    }

    public Rectangle getBounds() { return new Rectangle(x, y, 60, 70); }
}
