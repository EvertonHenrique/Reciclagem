package jogo; 

import java.awt.*;
import javax.swing.ImageIcon;

public class ItemPapel {
    public int x, y;
    public boolean estaNoCaminhao = false;
    private Jogo jogo;
    private ImageIcon img;

    public ItemPapel(Jogo jogo) {
        this.jogo = jogo;
        java.net.URL url = getClass().getResource("/foto/papel.png");
        if (url != null) img = new ImageIcon(url);
    }

    public void checarColeta() {
        // Só tenta coletar se o papel ainda estiver no chão
        if (!estaNoCaminhao) {
            // TRAVA: Só coleta se o jogador NÃO estiver carregando nenhum outro item
            boolean caminhaoVazio = !jogo.latinha.estaNoCaminhao && !jogo.peixe.estaNoCaminhao;
            
            if (caminhaoVazio && jogo.player.getBounds().intersects(getBounds())) {
                estaNoCaminhao = true;
            }
        } else {
            // Se já está no caminhão, segue a posição do jogador
            this.x = jogo.player.x + 10;
            this.y = jogo.player.y + 10;
        }
    }

    public void desenhar(Graphics g) {
        if (img != null) {
            g.drawImage(img.getImage(), x, y, 30, 30, null); //posicionamento e alturua e largura
        } else {
            // Cor azul clara para representar papel caso a imagem falhe ou erro
            g.setColor(new Color(173, 216, 230)); 
            g.fillRect(x, y, 30, 30);
        }
    }

    public Rectangle getBounds() { 
        return new Rectangle(x, y, 30, 30); 
    }
}
