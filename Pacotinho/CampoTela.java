/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacotinho;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author Luis
 */
public class CampoTela extends javax.swing.JFrame implements MouseListener{
    private Jogo jogo;
    private AtualizaTempo at;
    private JButton[][] grade = new JButton[Jogo.x][Jogo.y];
    /**
     * Creates new form CampoTela
     */
    public CampoTela() throws IOException {
        Icon icon = setImagem2("C:/Users/Luis/Documents/NetBeansProjects/"
                    + "CampoMinado/src/Imagens/sun.png",0);
        initComponents();
        jogo = new Jogo();
        initialize();
        this.setBounds(0,0,jogo.x*jogo.width+15, jogo.y*jogo.height+88);
        jPanel1.setLayout(new GridLayout(jogo.x, jogo.y));
        jPanel1.setSize(jogo.x*jogo.width, jogo.y*jogo.height);
        jPanel1.setBounds(0, 50,jogo.x*jogo.width, jogo.y*jogo.height);
        jButtonRisingSun.setIcon(icon);
        at = new AtualizaTempo();
        at.start();
    }
    
    private void initialize() throws IOException {
        Icon icon = setImagem("C:/Users/Luis/Documents/NetBeansProjects/"
                    + "CampoMinado/src/Imagens/facingDown.png");
        for (int i = 0; i < Jogo.x; i++) {
            for (int j = 0; j < Jogo.y; j++) {
                grade[i][j] = new JButton(icon);
                grade[i][j].addMouseListener(this);
                grade[i][j].setSize(jogo.width, jogo.height);
                jPanel1.add(grade[i][j]);
            }
        }
        validate();
    }
    
    public void perda() throws IOException {
        ArrayList<int[]> lista = new ArrayList<>();
        lista = jogo.perda();
        Icon icon;
        for(int[] a : lista) {
            icon = new ImageIcon(jogo.getTab().getSpace()[a[0]][a[1]].getImagem());
            grade[a[0]][a[1]].setIcon(icon);
        }
        icon = setImagem2("C:/Users/Luis/Documents/NetBeansProjects/"
                    + "CampoMinado/src/Imagens/sun.png",-90);
        jButtonRisingSun.setIcon(icon);
    }
    
    public void vitoria() throws IOException {
        jogo.vitoria();
        if (jogo.getVitoria()) {
            for (int i = 0; i < grade.length; i++) {
                for (int j = 0; j < grade[i].length; j++) {
                    if (!jogo.getTab().getSpace()[i][j].getFlipped()) {
                        Icon icon;
                        icon = setImagem("C:/Users/Luis/Documents/NetBeansProjects/"
                                + "CampoMinado/src/Imagens/flagged.png");
                        grade[i][j].setIcon(icon);
                    }
                }
            }
            this.jTextFieldFlag.setText(jogo.getBandeiras()+"");
            Icon icon = setImagem2("C:/Users/Luis/Documents/NetBeansProjects/"
                    + "CampoMinado/src/Imagens/sun.png",-60);
            jButtonRisingSun.setIcon(icon);
        }
    }
    
    public void clear(int x, int y) {
        ArrayList<int[]> lista = new ArrayList<>();
        lista = jogo.clear(x,y);
        Icon icon;
        for(int[] a : lista) {
            icon = new ImageIcon(jogo.getTab().getSpace()[a[0]][a[1]].getImagem());
            grade[a[0]][a[1]].setIcon(icon);
            jogo.getTab().getSpace()[a[0]][a[1]].flip();
        }
    }
    
    public class AtualizaTempo extends Thread {
        @Override
        public void run() {
            while(!jogo.getDerota() && !jogo.getVitoria()) {
                jTextFieldTime.setText(jogo.getTempo()+"");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CampoTela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextFieldTime = new javax.swing.JTextField();
        jTextFieldFlag = new javax.swing.JTextField();
        jButtonRisingSun = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(jogo.x*jogo.width + 1, jogo.y*jogo.height + 20);
        getContentPane().setLayout(null);

        jPanel1.setPreferredSize(new java.awt.Dimension(230, 260));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 30, 230, 260);

        jTextFieldTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTime.setText("000");
        getContentPane().add(jTextFieldTime);
        jTextFieldTime.setBounds(170, 10, 60, 30);

        jTextFieldFlag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldFlag.setText("10");
        getContentPane().add(jTextFieldFlag);
        jTextFieldFlag.setBounds(30, 10, 50, 30);

        jButtonRisingSun.setPreferredSize(new java.awt.Dimension(20, 20));
        jButtonRisingSun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRisingSunActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRisingSun);
        jButtonRisingSun.setBounds(110, 10, 30, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRisingSunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRisingSunActionPerformed
        try {
            jogo = new Jogo();
            Icon icon = setImagem("C:/Users/Luis/Documents/NetBeansProjects/"
                    + "CampoMinado/src/Imagens/facingDown.png");
            for (int i = 0; i < Jogo.x; i++) {
                for (int j = 0; j < Jogo.y; j++) {
                    grade[i][j].setIcon(icon);
                }
            }
            this.jTextFieldFlag.setText("10");
            validate();
            icon = setImagem2("C:/Users/Luis/Documents/NetBeansProjects/"
                    + "CampoMinado/src/Imagens/sun.png",0);
            jButtonRisingSun.setIcon(icon);
            at = new AtualizaTempo();
            at.start();
        } catch (IOException ex) {
            Logger.getLogger(CampoTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonRisingSunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CampoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CampoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CampoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CampoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CampoTela().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(CampoTela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRisingSun;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldFlag;
    private javax.swing.JTextField jTextFieldTime;
    // End of variables declaration//GEN-END:variables

    public Icon setImagem(String filename) throws IOException {
        BufferedImage temp = ImageIO.read(new File(filename));
        return new ImageIcon(Jogo.resizeImage(temp));
    }
    
    public Icon setImagem2(String filename, int x) throws IOException {
        BufferedImage temp = ImageIO.read(new File(filename));
        return new ImageIcon(Jogo.resizeImage2(temp,x));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Icon icon;
        for (int i = 0; i < grade.length; i++) {
            for (int j = 0; j < grade[i].length; j++) {
                if (me.getSource() == grade[i][j] && SwingUtilities.isLeftMouseButton(me)
                        && !jogo.getDerota() && !jogo.getTab().getSpace()[i][j].getFlagged()
                        && !jogo.getVitoria()) {
                    icon = new ImageIcon(jogo.getTab().getSpace()[i][j].getImagem());
                    grade[i][j].setIcon(icon);
                    jogo.getTab().getSpace()[i][j].flip();
                    if (jogo.getTab().getSpace()[i][j].getBomba()) {
                        try {
                            perda();
                        } catch (IOException ex) {
                            Logger.getLogger(CampoTela.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        if(jogo.getTab().getSpace()[i][j].getBombaPerto() == 0) {
                            clear(i,j);
                        }
                        try {
                            vitoria();
                        } catch (IOException ex) {
                            Logger.getLogger(CampoTela.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if (me.getSource() == grade[i][j] && SwingUtilities.isRightMouseButton(me)
                        && !jogo.getDerota() && !jogo.getTab().getSpace()[i][j].getFlipped()
                        && !jogo.getVitoria()) {
                    if (jogo.getTab().getSpace()[i][j].getFlagged()) {
                        try {
                            icon = setImagem("C:/Users/Luis/Documents/NetBeansProjects/"
                                + "CampoMinado/src/Imagens/facingDown.png");
                            grade[i][j].setIcon(icon);
                            jogo.tiraBandeira();
                            this.jTextFieldFlag.setText(jogo.getBandeiras()+"");
                            jogo.getTab().getSpace()[i][j].setFlagged();
                            vitoria();
                        } catch (IOException ex) {
                            Logger.getLogger(CampoTela.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            icon = setImagem("C:/Users/Luis/Documents/NetBeansProjects/"
                                + "CampoMinado/src/Imagens/flagged.png");
                            grade[i][j].setIcon(icon);
                            jogo.colocaBandeiras();
                            this.jTextFieldFlag.setText(jogo.getBandeiras()+"");
                            jogo.getTab().getSpace()[i][j].setFlagged();
                            vitoria();
                        } catch (IOException ex) {
                            Logger.getLogger(CampoTela.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
}
