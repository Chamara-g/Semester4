
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server{

    public static final int PORT = 2000;
    View view;
    
    //establish communication with the client
    private static ServerSocket serverSocket; 
    private static int socketNumber;

    
    public Server(int socket,View view) throws IOException{
        serverSocket = new ServerSocket(socket);
        socketNumber = socket;
        this.view = view;
    }

    
    public void server_loop() throws IOException{
        while(true){
            Socket socket = serverSocket.accept();
            Client worker = new Client(socket,view);
            worker.start();
        }
    }

}
