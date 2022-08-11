/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pacotinho;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class Tempo extends Thread{
    private int t;

     public Tempo() {
        this.t = 0;
    }
    
    @Override
    public void run() {
        while(true) {
            t++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tempo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int getTempo() {
        return this.t; 
    }
}
