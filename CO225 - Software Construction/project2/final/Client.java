
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client extends Thread{
    
    View view;
    //communicate with this socket
    private final Socket connectionSocket;
    PrintWriter out;
    
    //genarate to lists 
    public static Map <String,BidderDetails> bidderList = new HashMap<>();    
    public static HashMap < String , Set > stockCollection = new HashMap<>() ; 
    
    //use for read file data (csv file)
    ReadFile readFile = new ReadFile("stocks.csv",stockCollection);   
    Set set;
    
    public Client(Socket connectionSocket, View view) throws IOException{
        this.connectionSocket = connectionSocket;
        this.view = view;
            
        this.out = new PrintWriter(new OutputStreamWriter(this.connectionSocket.getOutputStream()));                
    }
  
    //use for server message display
    public void serverMsg(String message){         
            out.print(message);
            out.flush();
    }
     
    @Override
    public void run() {
        
        try {
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
    
        //line use for scan value that entered by client    
        String line,bidderName,symbol;    
        //bid save user entered bid
        Double bid;
        //current bid is save in this variable
        Double price;
          
        //System.out.println(bidderList.size());
        if ( bidderList.isEmpty() ){
            displayList();
            //System.out.print("no one\n");
        }
        else{
            displayList();
        }
        
        serverMsg(" Enter bidder name : ");
        bidderName = in.readLine().trim();
        //System.out.println("hi " + bidderName );
        readFile.getStock();
        
        while(true){
            displayList();
            serverMsg(" Enter Symbol : "); 
            symbol = in.readLine().trim();
           // BidderDetails bidder;
            //System.out.println(symbol);

            set = (Set) stockCollection.get(symbol);

            if (stockCollection.keySet().contains(symbol)){
                serverMsg(" Symbol (" + symbol + ") is there!");
                //System.out.println("Symbol (" + symbol + ") is there!");
            
                    if (bidderList.keySet().contains(symbol)){
                        price = bidderList.get(symbol).price;
                        serverMsg(" Symbol already have past bid of " + price);
                        //System.out.println("Symbol already have past bid!");
                    }

                    else{
                        serverMsg(" Bid not exist for this symbol!");
                        //System.out.print("no one\n");
                        price = 0.0;
                    }              
                
                //System.out.println("Current bid is " + price );
                //System.out.println("You should enter larger than above bid!");
                
                serverMsg(" Enter your bid: ");
                bid = Double.parseDouble(in.readLine().trim());

                boolean bidData;
                
                bidData = set.newBid(bid);
                
                if ( bid > price ){
                    
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    
                    String modifyDate = dateFormat.format(date);
                    //System.out.println(dateFormat.format(date));
                    
                    serverMsg(" Your bid is taken as valid enter!");
                    //System.out.println("Your bid is taken as valid enter!");
                   
                    //put data into bidderList
                    bidderList.put( symbol , new BidderDetails(bidderName,bid,modifyDate) );
                    //chage price of the stockCollection
                    stockCollection.get(symbol).price = bid;
                    displayList();
                    serverMsg(" Do you want add another bid [Y/N] :");
                    line = in.readLine().trim();
                        
                        if (line.equals("Y")){
                        
                        }
                        else{
                            break;
                        }

                }
                
                else{
                    displayList();
                    serverMsg(" Bid is not enorgy!");
                    //System.out.println("Bid is not enorgy!");
                        serverMsg(" Do you want add another bid [Y/N] :");
                        line = in.readLine().trim();

                            if (line.equals("Y")){

                            }
                            else{
                                break;
                            }
                    
                }
            }       

            else{
                serverMsg(" Symbol (" + symbol + ") is Invalid! To exit enter[quit] or enter new symbol[new] : ");
                //System.out.println("Symbol (" + symbol + ") is Invalid!");
                displayList();
                line = in.readLine().trim();
                    
                    if ( line.equals("new")){
                        
                    }
                    else{
                        break;
                    }
            }

            
        }
        
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.connectionSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //genarate table for bidder and Symbol
    public void displayList(){
        
            //selected symblos take into array
            String [] symbol = {"FB","VRTU","MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};
            //System.out.println(bidderList.size());
            //System.out.println("--------------------------------------------------------------");
            String bidderName,date;
            Double bid;
            
            for (int i = 0; i<8; i++) {
                if ( bidderList.keySet().contains(symbol[i])){
                    //text = "[Symbol : " + symbol[i] + "] - " + "[bidder Name : " + bidderList.get(symbol[i]).bidderName() + "] - " + "[bid : " + bidderList.get(symbol[i]).price + "] - " + "[Bid modified : " + bidderList.get(symbol[i]).date + "]";
                    //System.out.print("[Symbol : " + symbol[i] + "] - ");
 
                    bidderName = bidderList.get(symbol[i]).bidderName();
                    //System.out.print("[bidder Name : " + bidderList.get(symbol[i]).bidderName() + "] - ");

                    bid = bidderList.get(symbol[i]).price;
                    //System.out.print("[bid : " + bidderList.get(symbol[i]).price + "] - ");      

                    date = bidderList.get(symbol[i]).date;
                    //System.out.println("[Bid modified : " + bidderList.get(symbol[i]).date + "]");
                    
                    view.label[4*(i+2) + 2].setText(symbol[i]);
                    view.label[4*(i+2) + 3].setText(bidderName);
                    view.label[4*(i+2) + 4].setText(Double.toString(bid));
                    view.label[4*(i+2) + 5].setText(date);
                }

                else{
                    view.label[4*(i+2) + 2].setText(symbol[i]);
                    view.label[4*(i+2) + 3].setText("-");
                    view.label[4*(i+2) + 4].setText("-");
                    view.label[4*(i+2) + 5].setText("-");
                }             
                
             }
            //System.out.println("--------------------------------------------------------------");
              
    }    
      
}   
