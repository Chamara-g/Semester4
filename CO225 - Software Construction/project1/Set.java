
public class Set {

private final int iterations;
Complex point;

public Set(int iterations, Complex point) {
     this.iterations = iterations;
     this.point = point;
 }


public int get_iterations(){
    return this.iterations;
}

public Complex get_point(){
    return this.point;
}

}