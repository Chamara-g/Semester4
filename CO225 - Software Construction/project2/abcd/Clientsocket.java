import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Clientsocket extends Thread{
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Socket socket;
    private String name;
    private Item item;
    private HashMap item_map;
    private String symbol;
    private BufferedWriter log;
    private HashMap<String, ArrayList<Clientsocket>> bid_control;
    private PrintWriter out;
    private GUI gui;

    public Clientsocket(Socket socket, HashMap item_map, BufferedWriter log, HashMap bid_control, GUI gui){
        this.socket = socket;
        this.name = "";
        this.item = null;
        this.item_map = item_map;
        this.symbol = "";
        this.log = log;
        this.bid_control = bid_control;
        this.gui = gui;

        try {
            out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void out_write(String s){
        try {
            this.out.write(s);
            this.out.flush();
        } catch (NullPointerException ignored) {
        }
    }

    private synchronized void log_write(String s){
        try {
            this.log.write(s);
            this.log.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String line;

            while (this.name.equals("")) {
                out_write("Enter name : ");
                name = in.readLine().trim();
            }

            System.out.println(name + " successfully connected\n");
            log_write(sdf.format(new Date()) + " , " + name + " successfully connected\n");
            out_write("Name recorded.\n");

            while (true){
                out_write("To bid for an item enter it's Symbol or to exit from session enter 'quit'\n");
                out_write("Enter symbol : ");

                if ((symbol = in.readLine()).equals("quit")){
                    break;
                }

                item = (Item) item_map.get(symbol);

                while (item == null && !symbol.equals("quit")){
                    out_write("Enter valid symbol : ");
                    symbol = in.readLine().trim();
                    item = (Item) item_map.get(symbol);
                }

                if (symbol.equals("quit")){
                    break;
                }

                if (!bid_control.containsKey(symbol)){
                    bid_control.put(symbol, new ArrayList<>());
                }
                bid_control.get(symbol).add(this);

                out_write("Current price = " + item.get_price() + "\n");
                out_write("To bid for the item enter the bid. To exit from item enter quit\n");
                out_write("Enter bid : ");

                float bid;
                boolean reply;
                String logtxt;
                ArrayList<Clientsocket> list;
                for(line = in.readLine(); line != null && !line.equals("quit") ; line = in.readLine()) {
                    try{
                        bid = Float.parseFloat(line.trim());
                        reply = item.make_bid(bid);
                        if (reply){     //bid successfull : write to log and inform all others, update gui
                            logtxt = sdf.format(new Date()) + " , " + name + " , " + symbol + " , " + bid + "\n";
                            this.out_write("Bid successful. Current price is " + bid + "\n");   //send to client
                            this.log_write(logtxt);                                             //write to log
                            list = bid_control.get(symbol);
                            for (Clientsocket ss : list) {                                      //inform other cllients
                                if (!this.equals(ss)) {
                                    ss.out_write("\nA new bid has been made. Current price is " + bid + "\nEnter bid : ");
                                }
                            }
                            gui.update_gui(symbol, logtxt);     //update gui
                        }else{
                            this.out_write("Bid higher. Current price is " + item.get_price() + "\n");
                        }
                    }catch (NumberFormatException e){
                        out.print("Invalid bid.\n");
                    }finally{
                        out.print("Enter bid : ");
                        out.flush();
                    }
                }
                bid_control.get(symbol).remove(this);
            }
            out_write("Exiting from session\n");
        }catch (IOException | NullPointerException ignored) {
        }

        try {
            System.out.println("Closing connection with " + name + "\n");
            log_write(sdf.format(new Date()) + " , " + "Closing connection with " + name + "\n");
            this.socket.close();
        } catch(IOException ignored){
        }
    }
}

