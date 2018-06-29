/*
  E/14/108
  All complex calculations are hear
 */
 

public class Complex {

    private double real_part,img_part;
    
public Complex(){
    //default constructor
};    
    
public Complex(double real_part, double img_part){
    this.real_part = real_part;
    this.img_part = img_part;
}

//get real part of a complex number
public double get_real(){
    return this.real_part;
}

//get imaginary part of a complex number
public double get_img(){
    return this.img_part;
}


public void set(double x , double y){
    this.real_part = x ;
    this.img_part = y ;
}

//multiply two complex numbers
public Complex complex_mul(Complex p){
    return new Complex((this.get_real()*p.get_real()) - (this.get_img()*p.get_img()),(this.get_img()*p.get_real())+(p.get_img()*this.get_real()));
}

//add two complex numbers
public Complex complex_add(Complex p){
    return new Complex((p.get_real() + this.get_real()),(p.get_img()+this.get_img()));
}

//get square value of absolute complex number 
public double get_absolute_square(){
    return ((this.get_real()*this.get_real()) + (this.get_img()*this.get_img()));
}

}
