/*
E/14/108

*/

import java.awt.*; /* java abstract window toolkit */
import static java.lang.Math.abs;
import javax.swing.*;

class Panel extends JPanel { 
    // inherit JPanel
    private final int width;
   private final int height; 
   double zoom;
    int iterations;
    double xmax ;
    double xmin ;
    double ymin ;
    double ymax ;   
    int choise;
    double cRel;
    double cIm;
    
    
    public Panel(int width, int height , double xmax , double xmin , double ymax , double ymin , int iterations ,int choise , double zoom ,double cRel ,double cIm) { 
	// set the panel size 
	this.width  = width; 
	this.height = height;
        this.xmax = xmax;
        this.xmin = xmin;
        this.ymax = ymax;
        this.ymin = ymin;
        this.choise = choise;
        this.iterations = iterations;
        this.zoom = zoom;
	setPreferredSize(new Dimension(width, height));
        this.cRel = cRel;
        this.cIm = cIm;

    }

    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);

        double xrange = abs(xmax-xmin);
        double yrange = abs(ymax-ymin);
        
        Algorithm al = new Algorithm(cRel , cIm , iterations , choise ,g);

int numThreads = 4;
            // The threads used in this program
            Thread[] threads = new Thread[numThreads];
            
            for (int a = 0; a < numThreads; a++) {
            // Create a new thread with the appropriate array as the parameter.
            threads[a] = new ThreadNew(xmax , xmin , ymax , ymin , iterations ,choise , zoom , a ,cRel , cIm);
            }
            
            for (Thread t : threads) {
            t.start();
            }
            
            // Join the threads
            for (Thread t : threads) {
            try {
            t.join();
            }
            catch (InterruptedException e) {}
            }
            al.printgraph();
       
    }
    

}