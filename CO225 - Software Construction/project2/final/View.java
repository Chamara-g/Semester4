//all GUI design is done in hear

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

//user see current bidder data from this GUI
public final class View extends JFrame{

    JPanel panel = new JPanel();
    JLabel [] label;
    javax.swing.border.Border border = LineBorder.createGrayLineBorder();
    
   public View(){

       super("Bid Controler");

       addComponents();
       buttonLayOut();
               
       setContentPane(panel);
       setSize(800,600);
       setResizable(false);
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);     
   } 
   
   public void addComponents(){
       
       label = new JLabel[42];
 
       for (int i = 1 ; i < label.length ; i++){
            label[i] = new JLabel("");
            label[i].setFont(new Font("Serif", Font.BOLD, 16));
            label[i].setForeground(Color.GREEN);
       }       
       
            label[5] = new JLabel("-Bidder List-");
            label[5].setForeground(Color.yellow);    
           
            label[6] = new JLabel("Symbol");
            label[7] = new JLabel("Bidder Name");
            label[8] = new JLabel("Bid");
            label[9] = new JLabel("Date and Time");
            
   }   

   public void buttonLayOut(){
       
      Color color = new Color(0,51,0); 
      panel.setBackground(color);
      
      GridLayout layer = new GridLayout(0,4);
      panel.setLayout(layer);        
      
      for ( int i = 2 ; i < 42 ; i++){
          panel.add(label[i]);
          label[i].setBorder(border);           
      }
      
      for ( int i = 5 ; i < 10 ; i++){
        label[i].setFont(new Font("Serif", Font.BOLD, 25));
        label[i].setHorizontalAlignment(JLabel.CENTER);
        label[i].setVerticalAlignment(JLabel.CENTER);           
      }  
      
      //set label font size and align
      for ( int i = 10 ; i < 42; i++){
        label[i].setFont(new Font("Serif", Font.BOLD, 15));
        label[i].setHorizontalAlignment(JLabel.CENTER);
        label[i].setVerticalAlignment(JLabel.CENTER);           
      }       
      //set label text color
      for ( int x = 1 ; x < 10 ; x++ ){
            label[x*4 + 2].setForeground(Color.CYAN);
            label[x*4 + 3].setForeground(Color.ORANGE);
            label[x*4 + 4].setForeground(Color.PINK);
            label[x*4 + 5].setForeground(Color.RED);
      }
    
   }   
   
}
