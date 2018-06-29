

public class Complex {

    private double real_part,img_part;
    
public Complex(){};    
    
public Complex(double real_part, double img_part){
    this.real_part = real_part;
    this.img_part = img_part;
}

public double get_real(){
    return this.real_part;
}

public double get_img(){
    return this.img_part;
}

public Complex complex_mul(Complex p){
    return new Complex((this.get_real()*p.get_real()) - (this.get_img()*p.get_img()),(this.get_img()*p.get_real())+(p.get_img()*this.get_real()));
}

public Complex complex_add(Complex p){
    return new Complex((p.get_real() + this.get_real()),(p.get_img()+this.get_img()));
}

public double get_absolute_square(){
    return ((this.get_real()*this.get_real()) + (this.get_img()*this.get_img()));
}

}
