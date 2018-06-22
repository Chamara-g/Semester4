/*
E/14/108
Lab3 - TicTacToe
*/

public class TicTacToe {
    
    public static void main(String [] args){
    
        View v = new View();
        Model m = new Model(v);
        Controller controller = new Controller(v,m);
        
    }
    
}
