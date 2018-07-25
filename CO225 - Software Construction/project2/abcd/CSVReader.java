import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class CSVReader {
    private String filename;
    public HashMap item_map;

    public CSVReader(String filename, HashMap item_map){
        this.filename = filename;
        this.item_map = item_map;   //reference to hashmap in main
    }

    public void read(){
        String line = "";
        FileReader fr = null;
        String filepath = new File("").getAbsolutePath() + "/";
        try {
            fr = new FileReader(filepath + filename);
        } catch (FileNotFoundException e) {
            try {
                fr = new FileReader(filepath + "src/" + filename);  //When using IDE
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }

        try{
            BufferedReader reader = new BufferedReader(fr);
            //Read header; cannot be used due to extra commas
            line = reader.readLine();
            //Assume symbol to be always first, name to be next and price to be last

            String[] item;
            String symbol = "";
            String name = "";
            String[] name_arr;
            float price;

            for (line = reader.readLine(); line != null ; line = reader.readLine()) {

                item = line.split(",");

                symbol = item[0].trim();
                price = Float.parseFloat(item[item.length - 1]);
                name_arr = Arrays.copyOfRange(item, 1, item.length - 5);
                name = String.join(",", name_arr);

                //System.out.println(symbol + " " + name + " " + price);

                item_map.put(symbol, new Item(symbol, name, price));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
