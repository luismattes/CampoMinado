/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacotinho;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Jogo {
    private Tabuleiro layout;
    public static final int width = 30;
    public static final int height = 30;
    public static final int x = 9;
    public static final int y = 9;
    public static final int bombCount = 10;
    private Tempo tempo;
    private boolean vitoria = false;
    private boolean derrota = false;
    private int bandeiras;

    public Jogo() throws IOException{
        layout = new Tabuleiro();
        bandeiras = bombCount;
        tempo = new Tempo();
        tempo.start();
    }
    
    public static BufferedImage resizeImage(BufferedImage originalImage) throws IOException{
        Image resultingImage = originalImage.getScaledInstance
        (width, height, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(width, height, 
                BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
    
    public static BufferedImage resizeImage2(BufferedImage originalImage, int x) throws IOException{
        Image resultingImage = originalImage.getScaledInstance
        (width*4, height, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(width, height, 
                BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, x, 0, null);
        return outputImage;
    }
    
    public ArrayList<int[]> perda() {
        ArrayList<int[]> lista = new ArrayList<>();
        for (int i = 0; i < Jogo.x; i++) {
                for (int j = 0; j < Jogo.y; j++) {
                    if (getTab().getSpace()[i][j].getBomba()) {
                        int[] pac = new int[2];
                        pac[0] = i;
                        pac[1] = j;
                        lista.add(pac);
                    }
                }
        }
        this.derrota = true;
        return lista;
    }
    
    public void vitoria() {
        int count = 0;
        for (int i = 0; i < Jogo.x; i++) {
            for (int j = 0; j < Jogo.y; j++) {
                if (getTab().getSpace()[i][j].getFlipped()) {
                    count++;
                }
            }
        }
        if (count >= (x*y)-bombCount) {
            this.vitoria = true;
        }
    }
    
    public Tabuleiro getTab() {
        return this.layout;
    }
    
    public boolean getDerota() {
        return this.derrota;
    }
    
    public boolean getVitoria() {
        return this.vitoria;
    }
    
    public void tiraBandeira() {
        bandeiras++;
    }
    
    public void colocaBandeiras() {
        bandeiras--;
    }
    
    public int getBandeiras() {
        return this.bandeiras;
    }
    
    public int getTempo() {
        return tempo.getTempo();
    }
    
    public ArrayList<int[]> clear(int x, int y) {
        ArrayList<int[]> lista = new ArrayList<>();
        if (x < Jogo.x-1) {
            lista.add(new int[]{x+1,y});
            if (getTab().getSpace()[x+1][y].getBombaPerto() == 0 && 
                    !getTab().getSpace()[x+1][y].getMarked()) {
                getTab().getSpace()[x+1][y].setMarked();
                lista.addAll(clear(x+1,y));
            }
        }
        if (x > 0) {
            lista.add(new int[]{x-1,y});
            if (getTab().getSpace()[x-1][y].getBombaPerto() == 0 && 
                    !getTab().getSpace()[x-1][y].getMarked()) {
                getTab().getSpace()[x-1][y].setMarked();
                lista.addAll(clear(x-1,y));
            }
        }
        if (y > 0) {
            lista.add(new int[]{x,y-1});
            if (getTab().getSpace()[x][y-1].getBombaPerto() == 0 && 
                    !getTab().getSpace()[x][y-1].getMarked()) {
                getTab().getSpace()[x][y-1].setMarked();
                lista.addAll(clear(x,y-1));
            }
        }
        if (y < Jogo.y-1) {
            lista.add(new int[]{x,y+1});
            if (getTab().getSpace()[x][y+1].getBombaPerto() == 0 && 
                    !getTab().getSpace()[x][y+1].getMarked()) {
                getTab().getSpace()[x][y+1].setMarked();
                lista.addAll(clear(x,y+1));
            }
        }
        if (x < Jogo.x-1 && y < Jogo.y-1) {
            lista.add(new int[]{x+1,y+1});
            if (getTab().getSpace()[x+1][y+1].getBombaPerto() == 0 && 
                    !getTab().getSpace()[x+1][y+1].getMarked()) {
                getTab().getSpace()[x+1][y+1].setMarked();
                lista.addAll(clear(x+1,y+1));
            }
        }
        if (x > 0 && y > 0) {
            lista.add(new int[]{x-1,y-1});
            if (getTab().getSpace()[x-1][y-1].getBombaPerto() == 0 && 
                    !getTab().getSpace()[x-1][y-1].getMarked()) {
                getTab().getSpace()[x-1][y-1].setMarked();
                lista.addAll(clear(x-1,y-1));
            }
        }
        if (x > 0 && y < Jogo.y-1) {
            lista.add(new int[]{x-1,y+1});
            if (getTab().getSpace()[x-1][y+1].getBombaPerto() == 0 && 
                    !getTab().getSpace()[x-1][y+1].getMarked()) {
                getTab().getSpace()[x-1][y+1].setMarked();
                lista.addAll(clear(x-1,y+1));
            }
        }
        if (x < Jogo.x-1 && y > 0) {
            lista.add(new int[]{x+1,y-1});
            if (getTab().getSpace()[x+1][y-1].getBombaPerto() == 0 && 
                    !getTab().getSpace()[x+1][y-1].getMarked()) {
                getTab().getSpace()[x+1][y-1].setMarked();
                lista.addAll(clear(x+1,y-1));
            }
        }
        return lista;
    }
    
}

