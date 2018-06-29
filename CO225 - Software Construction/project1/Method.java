
public class Method {

String type;
int iterations;
    
public Method(){};

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
