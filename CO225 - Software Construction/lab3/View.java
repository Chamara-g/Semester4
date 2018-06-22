/*
E/14/108
Lab3 - TicTacToe
*/

import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class View extends JFrame{
 
    JButton [] button;
    JPanel panel = new JPanel();
    JPanel panelend = new JPanel();
          
   public View(){

       super("Tic Tac Toe");

       addComponents();
       buttonLayOut();

       setContentPane(panel);
       setSize(600,600);
       setResizable(false);
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);     
   } 
   
   public void addComponents(){
       
       button = new JButton[10]; // add 9 buttons
       
       for (int i = 1 ; i < button.length ; i++){
            button[i] = new JButton("");          
       }
      
   }
   
   public void buttonLayOut(){ // set button layout 3*3 format
       
      GridLayout layer = new GridLayout(0,3);
      panel.setLayout(layer);        
      
      for ( int i = 1 ; i < 10 ; i++){
          panel.add(button[i]);
      }
    
   }


   // add tow img to find player one and player two
   public void addPlayerImg(int number , JButton b) throws IOException{
       
       if ( number == 1){
            Image img = ImageIO.read(getClass().getResource("a.png"));
            img = img.getScaledInstance( 190 , 190 ,  java.awt.Image.SCALE_SMOOTH ) ;
            b.setIcon(new ImageIcon(img));         
       }
       
       else {
           Image img = ImageIO.read(getClass().getResource("b.png"));
           img = img.getScaledInstance( 190 , 190 ,  java.awt.Image.SCALE_SMOOTH ) ;
           b.setIcon(new ImageIcon(img));
       }
   }
   
}
