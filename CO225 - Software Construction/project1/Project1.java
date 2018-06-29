/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chamara
 */
public class Project1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        Complex p = new Complex(0,.89);
        Complex p1 = new Complex(2,3);
        
        System.out.println(p.get_real());
        System.out.println(p.get_img());
        Complex p3 = p1.complex_mul(p);
        System.out.println(p3.get_real());
        System.out.println(p1.get_absolute_square());
        Complex p2 = p1.complex_add(p1);
        System.out.println(p2.get_real());
        System.out.println(p2.get_img());
        
        Complex p4 = p.complex_mul(p);
        System.out.println(p4.get_real());
        System.out.println(p4.get_img());
        System.out.println(p4.get_absolute_square());
        
        Method m = new Method();
        Set x = m.find_iterations(1000, p);
        
        System.out.println(x.get_iterations());
        System.out.println(x.get_point().get_real());
        System.out.println(x.get_point().get_img());
    }
    
}
