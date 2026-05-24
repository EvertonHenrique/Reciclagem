package jogo;
import java.awt.*;
import javax.swing.ImageIcon;

public class Lata_azul {
    public int x = 250, y = 28;
    private ImageIcon img;

    public Lata_azul(Jogo jogo) {
        java.net.URL url = getClass().getResource("/foto/lata_papel.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void desenhar(Graphics g) {
        if (img != null) g.drawImage(img.getImage(), x, y, 60, 70, null); //posicionamento e alturua e largura
        else { g.setColor(Color.BLUE); g.fillRect(x, y, 60, 70); }
    }

    public Rectangle getBounds() { return new Rectangle(x, y, 60, 70); }
}
