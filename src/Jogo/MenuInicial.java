package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class MenuInicial extends JPanel {
    private Image imagemFundo;
    private JButton botaoIniciar;

    public MenuInicial(ActionListener acaoBotao) {
        // CORREÇÃO: Removida a extensão duplicada (.png.png) para carregar a imagem corretamente
        URL imgURL = getClass().getResource("/foto/menuInicial.png"); 
        if (imgURL != null) {
            imagemFundo = new ImageIcon(imgURL).getImage();
        }

        // Define o layout como null para permitir o posicionamento absoluto do botão
        setLayout(null);

        // Configuração do botão invisível (sobreposto à imagem do menu)
        botaoIniciar = new JButton();
        botaoIniciar.setBounds(420, 155, 220, 60);
        botaoIniciar.setContentAreaFilled(false); 
        botaoIniciar.setBorderPainted(false);     
        botaoIniciar.setFocusPainted(false);      
        botaoIniciar.setOpaque(false);            
        
        // Atribui a ação recebida por parâmetro ao clique do botão
        botaoIniciar.addActionListener(acaoBotao);

        // Adiciona o botão ao painel do menu
        add(botaoIniciar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Desenha a imagem preenchendo todo o painel do menu (720x720)
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Caso a imagem falhe ou não seja encontrada, exibe um fundo verde padrão
            g.setColor(new Color(50, 150, 50));
            g.fillRect(0, 0, getWidth(), getHeight());
            
            // Texto de aviso temporário em caso de erro na imagem
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Erro ao carregar /foto/menuInicial.png", 150, 100);
        }
    }
}
