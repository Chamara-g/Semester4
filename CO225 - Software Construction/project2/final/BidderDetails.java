//all details about current valid bid is in hear
public class BidderDetails {

String bidderName;
Double price;
String date;


    public BidderDetails(String bidderName, Double price,String date){
        this.bidderName = bidderName;     
        this.price = price;
        this.date = date;
    }
    
    public String bidderName(){
        return this.bidderName;
    }
       
}
