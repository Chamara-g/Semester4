
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shehan
 */
public class Server extends JPanel implements Runnable{
    
    public static JFrame frame;
    public static JButton button;
    public static JLabel labelcmd,cPrice;
    
    public static final int PORT = 2000; //nc localhost 2000
    public static Map <String,Stock> stockList; //Stock List
    public static Map <String,Bidder> bidderList = new HashMap<>(); //All biders
    
    private static ServerSocket serverSocket;
    //private static int socketNum;
    
    private Socket connectionSocket;
    private String name;
    
    public Server(int socket) throws IOException{
        serverSocket = new ServerSocket(socket); //create socket
        //socketNum = socket; //?
        labelcmd = new JLabel("Current bid messages",JLabel.CENTER);
        labelcmd.setPreferredSize(new Dimension(400, 80));
        labelcmd.setBorder(BorderFactory.createLineBorder(Color.black));
        add(labelcmd, BorderLayout.NORTH);
        
        cPrice = new JLabel("Items in Server",JLabel.CENTER);
        cPrice.setPreferredSize(new Dimension(400, 220));
        cPrice.setBorder(BorderFactory.createLineBorder(Color.black));
        add(cPrice, BorderLayout.SOUTH);
    }
    
    public Server(Socket socket) { 
	this.connectionSocket = socket; 
    }
    
    public void SetName(String nam){
        this.name = nam;
    }
    
    public void SetMap(Map mapList){
        stockList = mapList;
    }
    
    public void SetFrame(JFrame frame){
        Server.frame = frame;
    }
    
    public void server_loop() throws IOException{
        while(true){
            Socket socket = serverSocket.accept();
            Thread worker = new Thread(new Server(socket)); 
            worker.start();
        }
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
            //PrintWriter send message to recever from server
            PrintWriter out = new PrintWriter(new OutputStreamWriter(this.connectionSocket.getOutputStream()));
            
            //read and print
            String line;
            
            //Enter name
            out.print("Enter your Name : ");
            out.flush();
            line = in.readLine();
            SetName(line);
            
            Bidder bidder1;
            if(bidderList.containsKey(line)){
                //old client
                //System.out.println("********");
                bidder1 = bidderList.get(line);
            }else{
                //new client
                bidder1 = new Bidder(line);
                bidderList.put(line, bidder1);
            }
            
            //client to say 
            out.print("Enter Symbol : ");
            out.flush();
            
            for(line = in.readLine();line != null && !line.equals("quite");line = in.readLine()){
                DisplayList();
                String display;
                if(stockList.get(line).GetBidder() == null){
                    display = bidder1.getName() +" : " + line;
                    System.out.println(display);
                    //button.setText(display);
                    labelcmd.setText(display);
                     //add(frame);
   
                }else{
                    display = bidder1.getName() +" : " + line + " (currently goes to " + stockList.get(line).GetBidder().getName() + ")";
                    System.out.println(display);
                    labelcmd.setText(display);
                }
                
                
                if(stockList.containsKey(line)){
                    //send to recever from server
                    String tmp = line;
                    out.print(stockList.get(line).getPrice() +"\n");
                    out.flush();
                    //get bid from user
                    out.print("Enter your bid : ");
                    out.flush();
                    
                    line = in.readLine();
                    
                    //System.out.println(stockList.get(tmp).getPrice());
                    
                    if(Double.valueOf(line)>stockList.get(tmp).getPrice()){
                        
                        //update value
                        bidder1.updateList(tmp, Double.valueOf(line));//update value to bidder price
                    
                        stockList.get(tmp).UpdatePrice(Double.valueOf(line)); //update value to Stock
                        display = bidder1.getName() + " set a bid for "+tmp+" : "+ bidder1.getBidList().get(tmp);
                        System.out.println(display);
                        labelcmd.setText(display);
                        
                        out.print("Your bid is susesfully added\n");
                        out.flush();
                        
                        stockList.get(tmp).SetBidder(bidder1);
                    
                    }else{
                        display = bidder1.getName() + " enterd smaller bid.";
                        System.out.println(display);
                        labelcmd.setText(display);
                        //client msg
                        out.print("Place a bid above " + stockList.get(tmp).getPrice() + " .\n");
                        out.flush();
                    }
                    
                    out.print("Enter Symbol : ");
                    out.flush();
                    
                }else{
                    out.print("-1\n");
                    out.flush();
                }
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
        
        try{
            this.connectionSocket.close();
        }catch(IOException e){
            
        }
        
    }
   
    public static void DisplayList(){
        //System.out.println("You at dispay");
        String  display = "<html><table><tr><td>Symbol</td><td>Price</td><td>Bidder</td</tr>";
        String [] SpList = {"FB","VRTU","MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};
        for(int i = 0;i<SpList.length;i++){
            
            if(stockList.get(SpList[i]).GetBidder() == null){
                display = display + "<tr><td>" + stockList.get(SpList[i]).getSymbol() + "</td><td>" + stockList.get(SpList[i]).getPrice() + "</td><td> </td></tr>";
            }else{
                display = display + "<tr><td>" + stockList.get(SpList[i]).getSymbol() + "</td><td>" + stockList.get(SpList[i]).getPrice() + "</td><td>" +
                stockList.get(SpList[i]).GetBidder().getName() + "</td></tr>";
            }
        }
        display = display + "</html>";
        cPrice.setText(display);
        //System.out.println(display);
    }
   
}
