/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacotinho;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class Tabuleiro {
    private Espaco[][] espaco = new Espaco[Jogo.x][Jogo.y];

    public Tabuleiro() throws IOException {
        try {
            for (int i = 0; i < Jogo.x; i++) {
                for (int j = 0; j < Jogo.y; j++) {
                    espaco[i][j] = new Espaco(false);
                }
            }
            setBombs();
        } catch (IOException ex) {
            Logger.getLogger(Tabuleiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        calculate();
    }
    
    private void setBombs() throws IOException {
        int count = Jogo.bombCount;
        int x;
        int y;
        while(count > 0) {
            x = (int)(Math.random() * Jogo.x);
            y = (int)(Math.random() * Jogo.y);
            while (espaco[x][y].getBomba()) {
                y++;
                if (y >= Jogo.y) {
                    x++;
                    y = 0;
                }
                if (x >= Jogo.x && y >= Jogo.y) {
                    x = 0;
                    y = 0;
                }
            }
            espaco[x][y] = new Espaco(true);
            espaco[x][y].setImagem("C:/Users/Luis/Documents/NetBeansProjects/"
                    + "CampoMinado/src/Imagens/bomb.png");
            count--;
        }
    }
    
    private void calculate() throws IOException {
        int cont = 0;
        for (int i = 0; i < Jogo.x; i++) {
            for (int j = 0; j < Jogo.y; j++) {
                if (i < Jogo.x-1 && espaco[i+1][j].getBomba()) {
                    cont++;
                }
                if (i > 0 && espaco[i-1][j].getBomba()) {
                    cont++;
                }
                if (j > 0 && espaco[i][j-1].getBomba()) {
                    cont++;
                }
                if (j < Jogo.y-1 && espaco[i][j+1].getBomba()) {
                    cont++;
                }
                if (i < Jogo.x-1 && j < Jogo.y-1 && espaco[i+1][j+1].getBomba()) {
                    cont++;
                }
                if (i > 0 && j > 0 && espaco[i-1][j-1].getBomba()) {
                    cont++;
                }
                if (i > 0 && j < Jogo.y-1 && espaco[i-1][j+1].getBomba()) {
                    cont++;
                }
                if (i < Jogo.x-1 && j > 0 && espaco[i+1][j-1].getBomba()) {
                    cont++;
                }
                if (!espaco[i][j].getBomba()) {
                    espaco[i][j].setBombaPerto(cont);
                    espaco[i][j].setImagem("C:/Users/Luis/Documents/"
                            + "NetBeansProjects/CampoMinado/src/Imagens/"+cont+".png");
                }
                cont = 0;
            }
        }
    }
    
    public Espaco[][] getSpace() {
        return this.espaco;
    }
}
