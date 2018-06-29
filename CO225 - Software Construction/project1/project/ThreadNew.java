
import static java.lang.Math.abs;


public class ThreadNew extends Thread{
    
 
    double zoom;
    int iterations;
    double xmax ;
    double xmin ;
    double ymin ;
    double ymax ;   
    int choise;
    int part;
    double cRel,cIm;
    Algorithm al = new Algorithm();
    
    public ThreadNew(double xmax , double xmin , double ymax , double ymin , int iterations ,int choise , double zoom,int part,double cRel,double cIm) { 
	// set the panel size 
	 
	this.cRel = cRel;
        this.cIm = cIm;
        this.xmax = xmax;
        this.xmin = xmin;
        this.ymax = ymax;
        this.ymin = ymin;
        this.choise = choise;
        this.iterations = iterations;
        this.zoom = zoom;
        this.part = part;
    }
    
    @Override
    public void run(){
        

        double xrange = abs(xmax-xmin);
        double yrange = abs(ymax-ymin);


        for ( int p = part ; p < part+1 ; p++){
            al.calculation( 0 , 200*p , xrange , yrange , 800 , 200*(p+1) , 0  , xmin ,  ymax*(2-p)/2 , zoom , cRel , cIm);
        }
    }
    
    
}
