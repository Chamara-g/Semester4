//csv file data read in hear

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class ReadFile {

String fileName;//csv file name
HashMap stockCollection;//data collect to this map

    public ReadFile( String fileName , HashMap  stockCollection ){
        this.fileName = fileName;
        this.stockCollection = stockCollection;
    }
    
    public void getStock() throws FileNotFoundException, IOException{
        
        String line;//use for line by line read
        String [] setValue;
        int col = 0;
        
        FileReader fileReader;
        fileReader = new FileReader(fileName);
        
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        while ( ( line = bufferedReader.readLine() ) != null ){
           
            if ( col == 0){
            
            }
            else{
            
            setValue = line.split(",");
            
            String symbol = setValue[0];
            String securityName = setValue[1];
            Double price = Double.parseDouble(setValue[setValue.length - 1]);
            
            //System.out.println(symbol + " " + securityName + " " + price);
            
            //data put into list
            stockCollection.put( symbol , new Set( symbol,securityName,price) );
            
            }
            col++;
        }
        
        //System.out.print(stockCollection.keySet());
        
    }
    
}
