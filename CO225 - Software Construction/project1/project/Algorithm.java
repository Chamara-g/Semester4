/*
E/14/108
All calculations which want do to get Mandelbrot and julia set is doing hear
*/


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Algorithm{
    
    static int iterations;  
    static int choise; //which set do you run choies using this.(Mandel or julia)
    static Graphics g; 
    
    static float [][]matrixh = new float[800][800];
    static float [][]matrixh1 = new float[800][800];
    
    double cRel,cIm;
    
    public Algorithm(){
    }
    
    public Algorithm(double cIm , double cRel , int iterations , int choise , Graphics g){
        this.iterations = iterations;  
        this.choise = choise;
        this.g = g;
        this.cRel = cRel;
        this.cIm = cIm;
    }
    
    //print a colored point in frame
    private static void printPoint(Graphics2D frame, Color c, int x ,int y) {
        frame.setColor(c); 
	frame.draw(new Line2D.Double(x, y, x, y)); 
    }

    //main calculation doing hear 
    public void calculation(int j , int i , double xrange ,double yrange  , int xlast ,int ylast , int jnew  , double xstart , double ystart , double zoom , double cRel , double cIm){

        int itr;
        double zabs;

        //change imaginary value of complex number
        for ( double y = ystart ; i<ylast  ; y = (y - (yrange/800*zoom)) ){

            //change real value of complex number
            for ( double x = xstart ; j<xlast  ; x = (x + (xrange/800*zoom)) ){
            //make a complex number using x and y value    
            Complex l = new Complex(x,y);

                //if choies is 1 then doing calculation to get julia set
                if ( choise == 1){
                   Complex m = new Complex(cRel,cIm);//m is a complex number which user give
                   itr = Julia(iterations,l,m);//get value of iterations that point take (z = z0*z0 + c)
                   //zabs = Julia(iterations,l,m).getCom().get_absolute_square();//get square of z absolute value
                }
                //otherwise doing calculations for madenlbrot set
                else{
                    itr = Mandelbrot(iterations,l);//get value of iterations that point take (z = z0*z0 + c)
                    //zabs = Mandelbrot(iterations,l).getCom().get_absolute_square();//get square of z absolute value
                }

                    //find color of point using iteration value got from above calculation
                    if ( itr < 1000 ){
                        float h = (float)(0.0 + ((((float)itr)/1000)));
                        float h1 = (float)(0.1 + (((float)itr)/200));
                        matrixh[i][j] = h;
                        matrixh1[i][j] = h1;
 }
                    else {
                        float h = (float)(0.0 + ((((float)itr)/1000)));
                        float h1 = (float)(0.1 + (((float)itr)/200)); 
                        matrixh[i][j] = h;
                        matrixh1[i][j] = h1;
                    }
                    j++;
            }

            j=jnew;
            i++;
        }

    }
    public void printgraph(){
        for ( int x = 0 ; x < 800 ; x++){
            for ( int y = 0 ; y < 800 ; y++){
                try{
                    //System.out.println(matrixh[x][y]);
                    printPoint((Graphics2D)g, Color.getHSBColor(matrixh[x][y] ,1,  matrixh1[x][y]) ,y,x);
                }
                catch(ArrayIndexOutOfBoundsException ex){
                }
            }
        }
    
    }
    //doing calculation to get iterations and complex value that satisfy z = z0*z0 + c for mandel set
    public int Mandelbrot(int iterations , Complex c){
        int stop_itr;
        Complex z0 = new Complex(0,0);// z0 = 0 + 0i
        Complex z = z0;// z = z0

        //going loop untile iterations exceed given value or untile 'abs(z) > 2' expreation satisfy
        for ( stop_itr = 1 ; stop_itr < iterations ; stop_itr++){
            if ((z.get_absolute_square()) > 4.0 ){
                return stop_itr;
            }
            else{ 
                z0 = z;            
                z = z0.complex_mul(z0).complex_add(c);// z = z0*z0 + c                    
            }           
        }
    return stop_itr;//return value of abs(Z)*abs(Z) that stop loop and related iteration value
    }    

    ////doing calculation to get iterations and complex value that satisfy z = z0*z0 + c for julia set
    public int Julia(int iterations , Complex c , Complex c1){
        int stop_itr;
        Complex z0 = c;
        Complex z = z0;

        for ( stop_itr=1 ; stop_itr<iterations ; stop_itr++){
            if ( z.get_absolute_square() > 4.0 ){
                break;
            }
            else{ 
                z0 = z;            
                z = z0.complex_mul(z0).complex_add(c1);                    
            }           
        }
    return stop_itr;
    }
    

}
