
import javax.swing.JFrame;


public class Fractal {

    static JFrame frame = new JFrame();
    static  Panel p ;

    public static void main(String [] args) { 
         
        switch (args[0]) {
            case "Mandelbrot":
                frame.setTitle("Mandelbrot");
                switch (args.length) {
                    case 6:
                        p = new Panel(800, 800 , Double.valueOf(args[2]) , Double.valueOf(args[1]) , Double.valueOf(args[4]) , Double.valueOf(args[3]) , Integer.valueOf(args[5]) , 10 , 1 , 0 , 0);
                        break;
                    case 5:
                        p = new Panel(800, 800 , Double.valueOf(args[2]) , Double.valueOf(args[1]) , Double.valueOf(args[4]) , Double.valueOf(args[3]) , 1000 , 10 , 1 , 0 , 0);
                        break;
                    case 1:
                        p = new Panel(800, 800 , 1 , -1 , 1 , -1 , 1000 , 10 , 1 , 0 , 0);
                        break;
                    default:
                        System.out.println("Error in input arguments");
                        return;
                }   break;
                
            case "Julia":
                frame.setTitle("Julia");
                switch (args.length) {
                    case 4:
                        p = new Panel(800, 800 , 1 , -1 , 1 , -1 , Integer.valueOf(args[3]) , 1 , 1 , Double.valueOf(args[1]) , Double.valueOf(args[2]));
                        break;
                    case 3:
                        p = new Panel(800, 800 , 1 , -1 , 1 , -1 , 1000 , 1 , 1 , Double.valueOf(args[1]) , Double.valueOf(args[2]));
                        break;
                    case 1:
                        p = new Panel(800, 800 , 1 , -1 , 1 , -1 , 1000 , 1 , 1 , -0.4 , 0.6);
                        break;
                    default:
                        System.out.println("Error in input arguments");
                        return;
                }   break;
            default:
                System.out.println("Error in input arguments");
                return;           
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	frame.setContentPane(p); 
         
	frame.pack(); 
	frame.setLocationRelativeTo(null); 
	frame.setVisible(true);
                
        }
             
}



