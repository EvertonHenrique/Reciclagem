package jogo;
import java.awt.*;
import javax.swing.ImageIcon;

public class Lata_verde {
    private Jogo jogo;
    private ImageIcon img;
    public int x = 510, y = 28; //posiçao

    public Lata_verde(Jogo jogo) {
        this.jogo = jogo;
        java.net.URL url = getClass().getResource("/foto/lata_vidro.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void desenhar(Graphics g) {
        if (img != null) g.drawImage(img.getImage(), x, y, 60, 70, null); //posicionamento e alturua e largura
        else { g.setColor(Color.GREEN); g.fillRect(x, y, 60, 70); }
    }

    public Rectangle getBounds() { return new Rectangle(x, y, 60, 70); }
}
