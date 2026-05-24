package jogo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import java.util.Random;
import Som.*;

public class Jogo extends JPanel {
    private MusicaFundo musica;
    private EfeitoSonoro item;
    private EfeitoSonoro reciclar;
    private EfeitoSonoro derrota;
    private EfeitoSonoro vitoria;
    private EfeitoSonoro passos;
    public Jogador player;
    public InimigoSuperior carrosCima;
    public InimigoInferior carrosBaixo;
    public Bike bike;
    public BikeOposto bikeOposto;
    
    public ItemColetavel latinha;
    public NaoColetavel peixe;
    public ItemPapel papel;
    public ItemVidro vidro;
    
    public Lata_metal lixeiraAmarela;
    public Lata_azul lixeiraAzul;
    public Lata_organico lixeiraMarrom;
    public Lata_verde lixeiraVerde;
    
    public static boolean chocado = false;
    public static int pontos = 0; 
    private boolean janelaAberta = false;
    private ImageIcon fundo;
    private Random gerador = new Random();
    private Timer gameLoop;

    public Jogo() {
        reciclar = new EfeitoSonoro("/Som/coletaReciclada.wav");
        musica = new MusicaFundo("/Som/fundo.wav");
        item = new EfeitoSonoro("/Som/itens.wav");
        derrota = new EfeitoSonoro("/Som/gameOver.wav");
        vitoria = new EfeitoSonoro("/Som/winner.wav");
        passos = new EfeitoSonoro("/Som/passos.wav");
        
        inicializarObjetos();
        fundo = carregarPacote("/foto/imagem.png");
        musica.tocar(); 
        setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int v = 25;
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.x -= v;
                    passos.tocar();
                    if (player.x < 0) player.x = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.x += v;
                    passos.tocar();
                    if (player.x > 580) player.x = 580;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    player.y -= v;
                    passos.tocar();
                    if (player.y < 0) player.y = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player.y += v;
                    passos.tocar();
                    if (player.y > 630) player.y = 630;
                }
            }
        });

        gameLoop = new Timer(20, e -> {
            if (!chocado) {
                carrosCima.mover();
                carrosBaixo.mover();
                bike.mover();
                bikeOposto.mover();
                
                latinha.checarColeta();
                peixe.checarColeta();
                papel.checarColeta();
                vidro.checarColeta();
                
                Rectangle pRect = player.getBounds();

                if (pRect.intersects(carrosCima.getBounds()) || pRect.intersects(carrosCima.getBounds2()) || 
                    pRect.intersects(carrosBaixo.getBounds()) || pRect.intersects(carrosBaixo.getBounds2()) ||
                    pRect.intersects(bike.getBounds()) || pRect.intersects(bikeOposto.getBounds())) {
                    finalizarJogo("Você bateu o Caminhão!", false);
                }

                verificarEntrega(pRect);

                if (pontos >= 3) {
                    finalizarJogo("Parabéns! Você ajudou o planeta!", true);
                }
                
                repaint();
            }
        });
        gameLoop.start();
    }

    private void verificarEntrega(Rectangle pRect) {
        if (latinha.estaNoCaminhao) {
            if (pRect.intersects(lixeiraAmarela.getBounds())) { reciclar.tocar(); pontos++; gerarNovoItem(); }
            else if (pRect.intersects(lixeiraAzul.getBounds()) || pRect.intersects(lixeiraMarrom.getBounds()) || pRect.intersects(lixeiraVerde.getBounds())) 
                finalizarJogo("Erro! Latinha é na Lata Amarela METAL.", false);
        }
        if (peixe.estaNoCaminhao) {
            if (pRect.intersects(lixeiraMarrom.getBounds())) { reciclar.tocar(); pontos++; gerarNovoItem(); }
            else if (pRect.intersects(lixeiraAzul.getBounds()) || pRect.intersects(lixeiraAmarela.getBounds()) || pRect.intersects(lixeiraVerde.getBounds())) 
                finalizarJogo("Erro! Peixe é na Lata Marrom.", false);
        }
        if (papel.estaNoCaminhao) {
            if (pRect.intersects(lixeiraAzul.getBounds())) { reciclar.tocar(); pontos++; gerarNovoItem(); }
            else if (pRect.intersects(lixeiraMarrom.getBounds()) || pRect.intersects(lixeiraAmarela.getBounds()) || pRect.intersects(lixeiraVerde.getBounds())) 
                finalizarJogo("Erro! Papel é na Lata Azul.", false);
        }
        if (vidro.estaNoCaminhao) {
            if (pRect.intersects(lixeiraVerde.getBounds())) { reciclar.tocar(); pontos++; gerarNovoItem(); }
            else if (pRect.intersects(lixeiraMarrom.getBounds()) || pRect.intersects(lixeiraAmarela.getBounds()) || pRect.intersects(lixeiraAzul.getBounds())) 
                finalizarJogo("Erro! Vidro é na Lata Verde.", false);
        }
    }

    private void gerarNovoItem() {
        latinha.estaNoCaminhao = false; latinha.x = -100;
        peixe.estaNoCaminhao = false;   peixe.x = -100;
        papel.estaNoCaminhao = false;   papel.x = -100;
        vidro.estaNoCaminhao = false;   vidro.x = -100;

        if (pontos < 3) { 
            int sorteio = gerador.nextInt(4);
            int novoX = gerador.nextInt(500) + 100;
            int novoY = gerador.nextInt(300) + 250;
            if (sorteio == 0) { latinha.x = novoX; latinha.y = novoY; }
            else if (sorteio == 1) { peixe.x = novoX; peixe.y = novoY; }
            else if (sorteio == 2) { papel.x = novoX; papel.y = novoY; }
            else if (sorteio == 3) { vidro.x = novoX; vidro.y = novoY; }
            
            item.tocar();
        }
    }

    private void finalizarJogo(String msg, boolean venceu) {
        if (gameLoop != null) {
            gameLoop.stop();
        }

        if (venceu) {
            musica.parar();
            vitoria.tocar();
        } else {
            musica.parar(); 
            derrota.tocar();
        }
        chocado = true;
        if (!janelaAberta) {
            janelaAberta = true;
            repaint();
            SwingUtilities.invokeLater(() -> {
                Object[] opcoes = {"Jogar Novamente", "Sair"};
                int escolha;
                
                resetarLayoutJanela(); 
                
                if (venceu) {
                    URL imgURL = getClass().getResource("/foto/planeta.png");
                    String htmlComemoracao = "<html><table width='260' style='padding: 5px; margin: 0;'><tr><td align='center'>"
                    + (imgURL != null ? "<img src='" + imgURL + "' width='80' height='80'><br><br>" : "")
                    + "<h1 style='color: #F8F8F8; margin: 0; font-family: sans-serif;'>⭐ PARABÉNS! ⭐</h1>"
                    + "<p style='color: #F8F8F8; font-size: 14px; margin: 5px;'>Você</p>"
                    + "<p style='color: #F8F8F8; font-size: 13px;'>Coletou todos os itens.</p>"
                    + "<br><b style='color: #F8F8F8; font-size: 16px;'>Missão Cumprida!</b>"
                    + "</td></tr></table></html>";

                    UIManager.put("OptionPane.background", Color.decode("#00CC33"));
                    UIManager.put("Panel.background", Color.decode("#00CC33"));

                    escolha = JOptionPane.showOptionDialog(this, htmlComemoracao, "🏆 Parabéns!", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes);
                } else {
                    URL imgURL = getClass().getResource("/foto/joia.png");
                    String htmlGameOver = "<html><table width='260' style='padding: 5px; margin: 0;'><tr><td align='center'>"
                    + (imgURL != null ? "<img src='" + imgURL + "' width='80' height='80'><br><br>" : "")
                    + "<h1 style='color: #8B0000; margin: 0; font-family: sans-serif;'>❌ FIM DE JOGO ❌</h1>"
                    + "<p style='font-size: 14px; margin: 10px 0; color: #333333;'>" + msg + "</p>"
                    + "<p style='font-size: 10px; margin: 5px; color: #333333;'>Itens Reciclados: <b style='color: #2E8B57;'>" + pontos + " itens</b></p>"
                    + "<br><b style='font-size: 13px; color: #333333'>Tente novamente!</b>"
                    + "</td></tr></table></html>";

                    UIManager.put("OptionPane.background", Color.decode("#DCDCDC"));
                    UIManager.put("Panel.background", Color.decode("#DCDCDC"));

                    escolha = JOptionPane.showOptionDialog(this, htmlGameOver, "Game Over", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes);
                }

                if (escolha == JOptionPane.YES_OPTION) { 
                    pontos = 0; 
                    inicializarObjetos(); 
                    musica.tocar();
                    
                    if (gameLoop != null) {
                        gameLoop.start();
                    }
                    
                    requestFocusInWindow();
                    repaint(); 
                } else { 
                    System.exit(0); 
                }
            });
        }
    }

    private void inicializarObjetos() {
        player = new Jogador();
        carrosCima = new InimigoSuperior();
        carrosBaixo = new InimigoInferior();
        bike = new Bike(); 
        bikeOposto = new BikeOposto();
        latinha = new ItemColetavel(this); 
        peixe = new NaoColetavel(this);
        papel = new ItemPapel(this); 
        vidro = new ItemVidro(this);
        lixeiraAmarela = new Lata_metal(this);
                lixeiraAzul = new Lata_azul(this);
        lixeiraMarrom = new Lata_organico(this);
        lixeiraVerde = new Lata_verde(this);
        
        gerarNovoItem();
        chocado = false;
        janelaAberta = false;
    }

    private ImageIcon carregarPacote(String caminho) {
        URL url = getClass().getResource(caminho);
        return (url != null) ? new ImageIcon(url) : null;
    }

    private void resetarLayoutJanela() {
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (fundo != null) {
            g.drawImage(fundo.getImage(), 0, 0, 720, 720, null);
        }
        
        // Desenho das lixeiras
        lixeiraAmarela.desenhar(g);
        lixeiraAzul.desenhar(g);
        lixeiraMarrom.desenhar(g);
        lixeiraVerde.desenhar(g);
        
        // Desenho dos itens coletáveis
        latinha.desenhar(g);
        peixe.desenhar(g);
        papel.desenhar(g);
        vidro.desenhar(g);
        
        // Desenho do jogador e obstáculos
        player.desenhar(g);
        carrosCima.desenhar(g);
        carrosBaixo.desenhar(g);
        bike.desenhar(g);
        bikeOposto.desenhar(g);
        
        // Placar de pontos
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Itens Coletados: " + pontos + " / 3", 20, 20);
        
        // Mensagem educativa
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.BOLD, 15));
        g.drawString("Ajude preservar nosso meio ambiente", 380, 15);
        
        // Avisos de direcionamento dos itens
        if (!chocado) {
            g.setFont(new Font("Calibri", Font.BOLD, 18));
            g.setColor(Color.RED);
            
            if (latinha.estaNoCaminhao) {
                g.drawString("LEVE A LATINHA PARA A LIXEIRA AMARELA(METAL)!", 165, 115);
            } else if (peixe.estaNoCaminhao) {
                g.drawString("LEVE O PEIXE PARA A LIXEIRA MARROM(ORGÂNICO)!", 165, 115);
            } else if (papel.estaNoCaminhao) {
                g.drawString("LEVE O PAPEL PARA A LIXEIRA AZUL(PAPEL)!", 165, 115);
            } else if (vidro.estaNoCaminhao) {
                g.drawString("LEVE O VIDRO PARA A LIXEIRA VERDE(VIDRO)!", 165, 115);
            }
        }
        
        // Tela de fim de jogo (Vitória ou Derrota)
        if (chocado) {
            Color overlay = (pontos >= 3) ? new Color(46, 139, 87, 150) : new Color(0, 0, 0, 150);
            g.setColor(overlay);
            g.fillRect(0, 0, 720, 720);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Impact", Font.PLAIN, 60));
            String texto = (pontos >= 3) ? "VOCÊ VENCEU!" : "GAME OVER";
            g.drawString(texto, 220, 100);
        }
    }
}

