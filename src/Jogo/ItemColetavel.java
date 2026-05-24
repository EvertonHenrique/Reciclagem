package jogo;
import java.awt.*;
import javax.swing.ImageIcon;
import Som.EfeitoSonoro;

public class ItemColetavel {
    public int x, y;
    public boolean estaNoCaminhao = false;
    private Jogo jogo;
    private ImageIcon img;
   

    public ItemColetavel(Jogo jogo) {
        this.jogo = jogo;
        java.net.URL url = getClass().getResource("/foto/latinha1.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void checarColeta() {
        if (!estaNoCaminhao) {
            if (jogo.player.getBounds().intersects(getBounds())) {
                estaNoCaminhao = true;
                
            }
        } else {
            // Acompanha o caminhão com um pequeno offset
            this.x = jogo.player.x + 15;
            this.y = jogo.player.y + 15;
        }
    }

    public void desenhar(Graphics g) {
        if (img != null) g.drawImage(img.getImage(), x, y, 30, 30, null); //posicionamento e alturua e largura
        else { g.setColor(Color.RED); g.fillOval(x, y, 30, 30); }
    }

    public Rectangle getBounds() { return new Rectangle(x, y, 30, 30); } //posicionamento e alturua e largura
}
