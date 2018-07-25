
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shehan
 */
public class Main {
    
    private static final String FILENAME = "/home/shehan/NetBeansProjects/Project2_CO225/src/stocks.csv";
    
    public static int symbolNo=-1,nameNo=-1,priceNo=-1;
    
     public static Map createList(){
            
        Map<String,Stock> stockList = new HashMap<>();
            
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

        	String sCurrentLine;

                if((sCurrentLine = br.readLine()) != null){
                    String words[] = sCurrentLine.split(",");

                    for(int i =0 ;i<words.length;i++){
                        if(words[i].equals("Symbol")){
                            symbolNo = i;
                        }else if(words[i].equals(("Security Name"))){
                            nameNo = i;
                        }else if(words[i].equals("Price")){
                            priceNo = i;
                        }
                    
                    }
                }
                
                if(symbolNo==-1||nameNo==-1||priceNo==-1){
                    System.out.println(symbolNo+" "+nameNo+" "+priceNo);
                    System.out.println("CSV file is not in correct format");
                    return stockList;
                    
                }


		while ((sCurrentLine = br.readLine()) != null) {
				
                    String words[] = sCurrentLine.split(",");
                    //System.out.println(words[symbolNo]);
                    if(words[priceNo].equals("N") || words[priceNo].equals("D")){
                        words[priceNo] = "0";
                    }
                    stockList.put(words[symbolNo],new Stock(words[symbolNo],words[nameNo],Double.valueOf(words[priceNo])));
		
                }

		} catch (IOException e) {
			e.printStackTrace();
		}
        return stockList;
    }
    
    public static int GetSymbolNo(){
        return symbolNo;
    }
    
    public static int GetNameNo(){
        return nameNo;
    }
    
    public static int GetPriceNo(){
        return priceNo;
    }
    
    public static void main(String [] args) throws IOException{
        Map <String,Stock> stockList = createList();
        
        //Create and set up the window.
        JFrame frame = new JFrame("Auction");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        Server server = new Server(2000);
        
        //Create and set up the content pane.
        JComponent newContentPane = server;
        // newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
        //Display the window.
        frame.setSize(420, 350);
        frame.setVisible(true);
        
        server.SetMap(stockList); //server add Map
        server.SetFrame(frame); //set JFrame
        
        server.server_loop();
        
       
    }
}
