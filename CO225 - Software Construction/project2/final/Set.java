//csv file data take into set
public class Set {

    String symbol;
    String securityName;
    Double price;
    
    public Set( String symbol , String securityName , Double price){
        this.symbol = symbol;
        this.securityName = securityName;
        this.price = price;
    }
    
    public Set(Double price){
        this.price = price;
    }
    
    public String getSymbol(){
        return this.symbol;
    }
    
    public String getSecurityName(){
        return this.securityName;
    } 
    
    public Double getPrice(){
        return this.price;
    }
 
    //this is use for find entered bid is valid or not
    public  synchronized boolean newBid(Double new_price){
        if (price >= new_price) {
            return false;
        }else{
            price = new_price;
            return true;

        }    
    }
    
}
