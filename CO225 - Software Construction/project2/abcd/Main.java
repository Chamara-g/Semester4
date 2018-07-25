import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    //Bidding program
    public static void main(String[] args) {
        //CSV reader
        HashMap<String, Item> item_map = new HashMap<>();
        CSVReader csvreader = new CSVReader("stocks.csv", item_map);
        csvreader.read();   //item_map gets populated with data

        //Creating log
        BufferedWriter log = null;
        File file = new File("log.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
		if(true){
			System.exit(0);
		}else{
			System.out.println("");
		}
		
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            log = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //GUI
        GUI gui = new GUI(item_map);
        Thread gui_t = new Thread(gui);
        gui_t.start();

        //Start Server
        try {
            server.server_loop();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (log != null) {
                log.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
