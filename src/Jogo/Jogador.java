package jogo;
import java.awt.*;
import javax.swing.ImageIcon;

public class Jogador {
    public int x = 300, y = 630; //posicao inicila
    private ImageIcon img;

    public Jogador() {
        java.net.URL url = getClass().getResource("/foto/caminhao1.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void desenhar(Graphics g) {
        if (img != null) g.drawImage(img.getImage(), x, y, 130, 50, null);
        else { g.setColor(Color.ORANGE); g.fillRect(x, y, 130, 50); } //caso tiver um  erro na imagem 
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 130, 50); // Leve redução para colisão mais justa
    }
}
