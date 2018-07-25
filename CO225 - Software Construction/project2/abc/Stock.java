/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shehan
 */
public class Stock {
    
    private final String symbol,security_name;
    private double price;
    private Bidder bidder;
  
    public Stock(String symbol,String name,double price){
        this.symbol = symbol;
        this.security_name = name;
        this.price = price;
        
    }
    public String getSymbol(){
         return this.symbol;
    }
    
    public String getName(){
        return this.security_name;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public void UpdatePrice(double pri){
        this.price = pri;
    }
    
    public void SetBidder(Bidder bidder){
        this.bidder = bidder;
    }
    
    public Bidder GetBidder(){
        return this.bidder;
    }
}  
