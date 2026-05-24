package jogo;
import java.awt.*;
import javax.swing.ImageIcon;

public class NaoColetavel {
    public int x, y;
    public boolean estaNoCaminhao = false;
    private Jogo jogo;
    private ImageIcon img;

    public NaoColetavel(Jogo jogo) {
        this.jogo = jogo;
        java.net.URL url = getClass().getResource("/foto/peixemorto.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void checarColeta() { //verificar a coleta
        if (!estaNoCaminhao) {
            if (jogo.player.getBounds().intersects(getBounds())) estaNoCaminhao = true;
        } else {
            this.x = jogo.player.x + 10;
            this.y = jogo.player.y + 10;
        }
    }

    public void desenhar(Graphics g) {
        if (img != null) g.drawImage(img.getImage(), x, y, 30, 30, null); //posicionamento e alturua e largura
        else { g.setColor(new Color(139, 69, 19)); g.fillOval(x, y, 30, 30); }
    }

    public Rectangle getBounds() { return new Rectangle(x, y, 30, 30); }
}
