
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static java.lang.Math.abs;

public class printpoint extends JPanel{

private final BufferedImage canvas;

public printpoint(int width,int height , Color c){
    int color = c.getRGB();
    canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    double xmin = -2;
    double xmax = 1;
    double ymin = -1;
    double ymax = 1;

    double xrang = abs(xmax-xmin);
    double yrang = abs(ymax-ymin);
    
    double xstart = (-xrang)/(2.0*800);
    double ystart = (yrang)/(800*2.0);

    int i=0;
    int j=0;

    for ( double y = ymax ; i<800  ; y = (y - (yrang/800.0)) ){

        for ( double x = xmin ; j<800  ; x = (x + (xrang/800.0)) ){
        Complex l = new Complex(x,y);
        int s = find_iterations(1000,l);
        
        if ( s < 1000 ){
               canvas.setRGB(j, i, color); 
            }
        
        j++;
        }

    j=0;
    i++;
    }

 /*   for (int i=0 ; i< 400 ; i++){
       for (int j=0; j<400 ; j++)
       canvas.setRGB(i, j, color);
    }*/
}

    /**
     *
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }


   public static void main(String[] args) {
        int width = 800;
        int height = 800;
        JFrame frame = new JFrame("Direct draw demo");

        printpoint panel = new printpoint(width, height,Color.BLUE);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

public int find_iterations(int iterations , Complex c){
    
    int i;
    Complex z0 = new Complex(0,0);
    Complex z = z0;
    
    for ( i=1 ; i<iterations ; i++){
        if ( z.get_absolute_square() > 4.0 ){
            return i;
        }
        else{ 
            z0 = z;            
            z = z0.complex_mul(z0).complex_add(c);                    
        }           
    }
return i;
}
}

