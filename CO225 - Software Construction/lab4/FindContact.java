
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindContact {
   
    static final String FileName = "sample.csv";
    static String line;
    static String []list; 
    
    public static void main(String []args) throws FileNotFoundException, IOException{
    //List<person> numList = new ArrayList<person>();
        List<NameAndContact> row = new ArrayList <NameAndContact>();
        
        FileReader fileReader;
        fileReader = new FileReader(FileName);
        
        BufferedReader bufferReader = new BufferedReader(fileReader);
        
        while ( (line = bufferReader.readLine()) != null ){
            
            list = line.split(",");
            NameAndContact details = new NameAndContact( list[0] , list[1] , list[2] ); 
            row.add(details);
            
        }
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter FistName(F) or SurName(L) : ");
        String Data = scan.nextLine();
        
        String Name[];
        Name = Data.split(":");
        
        switch (Name[0]) {
            case "F":
                for(int i=0 ; i < row.size() ; i++){
                    if ( row.get(i).getFirstName().equals(Name[1]) ){
                        System.out.print(row.get(i).getFirstName() + " " + row.get(i).getSurName() + " : ");
                        System.out.println(row.get(i).getPhoneNumber());    
                    }
                }
                break;
            case "L":
                for(int i=0 ; i < row.size() ; i++){
                    if ( row.get(i).getSurName().equals(Name[1]) ){
                        System.out.print(row.get(i).getFirstName() + " " + row.get(i).getSurName() + " : ");
                        System.out.println(row.get(i).getPhoneNumber());
                    }
                }
                break;
            default:
                break;
        }
    }
    
}
