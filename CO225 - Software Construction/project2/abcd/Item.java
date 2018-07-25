import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Item {
    private String symbol;
    private String name;
    private float price;

    public Item(String symbol, String name, float price){
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String get_symbol(){
        return this.symbol;
    }

    public String get_name(){
        return this.name;
    }

    public float get_price() {
        return this.price;
    }

    public synchronized boolean make_bid(float new_price){   //This functions handles all updates during new bid
        if (price >= new_price) {
            return false;
        }else{
            price = new_price;
            return true;

        }
    }



}
