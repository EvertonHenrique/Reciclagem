package jogo;
import java.awt.*;
import javax.swing.ImageIcon;

public class ItemVidro {
    public int x, y;
    public boolean estaNoCaminhao = false;
    private Jogo jogo;
    private ImageIcon img;

    public ItemVidro(Jogo jogo) {
        this.jogo = jogo;
        java.net.URL url = getClass().getResource("/foto/garrafa_vidro.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void checarColeta() {
        if (!estaNoCaminhao) {
            // Trava para carregar um por vez
            boolean caminhaoVazio = !jogo.latinha.estaNoCaminhao && !jogo.peixe.estaNoCaminhao && !jogo.papel.estaNoCaminhao;
            if (caminhaoVazio && jogo.player.getBounds().intersects(getBounds())) estaNoCaminhao = true;
        } else {
            this.x = jogo.player.x + 10;
            this.y = jogo.player.y + 10;
        }
    }

    public void desenhar(Graphics g) {
        if (img != null) g.drawImage(img.getImage(), x, y, 30, 30, null); //posicionamento e alturua e largura
        else { g.setColor(Color.GREEN); g.fillOval(x, y, 20, 30); }
    }

    public Rectangle getBounds() { return new Rectangle(x, y, 30, 30); }
}

