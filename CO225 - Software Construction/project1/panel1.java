import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*; 
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.Random;

class panel1 extends JPanel { // inherit JPanel 

    private int width, height; 
    private Point [] triangle; 
    private static int points = 100000; 

    public panel1(int width, int height) { 
	// set the panel size 
	this.width  = width; 
	this.height = height; 
	setPreferredSize(new Dimension(width, height)); 

	// panel is supposed to have a triangle in it. 
	triangle = new Point[1];
	triangle[0] = new Point(0d, 0d); 
    }

    private static void printPoint(Graphics2D frame, Color c, Point p) {

	frame.setColor(c); 
	frame.draw(new Line2D.Double(p.getX(), p.getY(), 
				     p.getX(), p.getY())); 
    }



    public static void main(String [] args) { 

	// create a frame
	JFrame frame = new JFrame("Serpenski's Triangle"); 
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	// set the content of the frame as one of this panel
	frame.setContentPane(new panel1(800, 800)); 

	frame.pack(); 
	frame.setLocationRelativeTo(null); 
	frame.setVisible(true); 
    }
}