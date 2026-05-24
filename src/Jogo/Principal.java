package jogo;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Jogo da Reciclagem");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(720, 720);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);

        MenuInicial menu = new MenuInicial(e -> {
            janela.getContentPane().removeAll(); 
            
            Jogo jogo = new Jogo();              
            janela.add(jogo);                    
            
            janela.revalidate();                 
            janela.repaint();                    
            jogo.requestFocusInWindow();         
        });

        janela.add(menu); 
        janela.setVisible(true); 
    }
}