/*
E/14/108
Lab3 - TicTacToe
*/

import javax.swing.JOptionPane;


public class Model {

    View view;
    
    public Model(View view){
        this.view = view;
    }
    
    //find which player click button
    public int findPlayer(int count){
        
        if ( count%2 == 1){
            return 1;
        }
        else {
            return 2;
        }
        
    }
    
    public void displayWinner(int count){
        

        if ( findWinner(count).equals("1") ){
            //System.out.println(findWinner(count));
            JOptionPane.showMessageDialog(view,"Won Player One");
            newWindow(); 
        }
        if ( findWinner(count).equals("2") ){
           // System.out.println(findWinner(count));
            System.out.println(findWinner(count));
            JOptionPane.showMessageDialog(view,"Won Player Two"); 
            newWindow();             
        }
        if ( findWinner(count).equals("0") ){
            System.out.println("Draw");
           // System.out.println(findWinner(count));
            JOptionPane.showMessageDialog(view,"Match Draw");            
            newWindow(); 
        }
        
    }

    //find if that players play game again or not
    public void newWindow(){
        int selectedOption = JOptionPane.showConfirmDialog(view,"Do you want to start a new game ?","Choose",JOptionPane.YES_NO_OPTION);                
        if (selectedOption == JOptionPane.YES_OPTION) {
            view.dispose();
            TicTacToe.main(null);
        }else{
            view.dispose();
        }
    }    
    

    //find winner 
    public String findWinner( int count){
        
        if ( buttonIndex(1,2) && buttonIndex(2,3) ){ 
            return view.button[1].getText();
        }       
        else if ( buttonIndex(4,5) && buttonIndex(5,6) ){
            return view.button[4].getText();
        } 
        else if ( buttonIndex(7,8) && buttonIndex(8,9) ){
            return view.button[7].getText();
        }
        else if ( buttonIndex(1,4) && buttonIndex(4,7) ){
            return view.button[1].getText();
        }
        else if ( buttonIndex(2,5) && buttonIndex(5,8) ){
            return view.button[2].getText();
        }
        else if ( buttonIndex(3,6) && buttonIndex(6,9) ){
            return view.button[3].getText();
        }
        else if ( buttonIndex(1,5) && buttonIndex(5,9) ){
            return view.button[1].getText();
        }
        else if ( buttonIndex(3,5) && buttonIndex(5,7) ){
            return view.button[3].getText();
        }
        else if ( count == 10){
            return "0";
        }
        else{
            return "no";
        }

        
    }
    
    public boolean buttonIndex(int buttonNum1 , int buttonNum2){
        return view.button[buttonNum1].getText().equals(view.button[buttonNum2].getText());
    }
    
}
