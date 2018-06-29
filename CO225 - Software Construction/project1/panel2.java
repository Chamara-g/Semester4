
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class printpoint extends JPanel{

private final BufferedImage canvas;

public printpoint(int width,int height , Color c){
    int color = c.getRGB();
    canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    for (int i=0 ; i< 400 ; i++){
       for (int j=0; j<400 ; j++)
       canvas.setRGB(i, j, color);
    }
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
}

