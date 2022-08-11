/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacotinho;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Luis
 */
public class Espaco {

    private int bombaPerto;
    private boolean bomba;
    private BufferedImage imagem;
    private boolean flagged = false;
    private boolean flipped = false;
    private boolean marked = false;
    
    Espaco(boolean bomba) throws IOException {
        this.bomba = bomba;
    }
    
    public void setBombaPerto(int b) {
        bombaPerto = b;
    }
    
    public int getBombaPerto() {
        return bombaPerto;
    }
    
    public void setImagem(String filename) throws IOException {
        BufferedImage temp = ImageIO.read(new File(filename));
        imagem = Jogo.resizeImage(temp);
    }
    
    public BufferedImage getImagem() {
        return imagem;
    }
    
    public boolean getBomba(){
        return this.bomba;
    }
    
    public void setFlagged() {
        this.flagged = !flagged;
    }
    
    public boolean getFlagged() {
        return this.flagged;
    }
    
    public void flip() {
        this.flipped = true;
    }
    
    public boolean getFlipped() {
        return this.flipped;
    }
    
    public void setMarked() {
        this.marked = true;
    }
    
    public boolean getMarked() {
        return this.marked;
    }
     
}
