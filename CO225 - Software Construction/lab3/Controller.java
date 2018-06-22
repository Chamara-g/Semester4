/*
E/14/108
Lab3 - TicTacToe
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public final class Controller implements ActionListener{

    View view;
    Model model;
    int count = 1;
    
    public Controller(View view , Model model){        
        this.view = view;
        this.model = model;
        addAction();
    }
    
    public void addAction(){

        for (int i = 1 ; i < view.button.length ; i++)
        view.button[i].addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        JButton name = (JButton) ae.getSource();
        int findPlayer = model.findPlayer(count);
 
        if ( !(name.getText().equals("1") || name.getText().equals("2")) ){
           name.setText(Integer.toString(findPlayer));
 
             try {
                view.addPlayerImg(findPlayer, name);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            count++;       
        }    
        model.displayWinner(count);
  //     System.out.println(model.findWinner(count));
    }
    
}