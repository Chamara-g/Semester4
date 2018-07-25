
/*
    Project2
    Group 20
    Last Modified 10/6/2017
*/

import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException{
    
        View view = new View();    
     
	Server server = new Server(2000,view);
	server.server_loop();      

        

        
    }
    
}
