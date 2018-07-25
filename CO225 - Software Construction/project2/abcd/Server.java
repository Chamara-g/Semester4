import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server{
    private static int port = 2000;
    private HashMap item_map;
    private ServerSocket serversocket;
    private BufferedWriter log;
    private HashMap<String, ArrayList<Clientsocket>> bid_control;       //Once new bid made send alert to all bidding to item
    private GUI gui;

    public Server(HashMap item_map, BufferedWriter log, GUI gui) throws IOException {
        serversocket = new ServerSocket(port);
        this.item_map = item_map;
        this.log = log;
        this.bid_control = new HashMap<>();
        this.gui = gui;
    }

    public void server_loop() throws IOException {
        while (true){
            Socket socket = serversocket.accept();
            Clientsocket ss = new Clientsocket(socket, item_map, log, bid_control);
            ss.start();
        }
    }
}
