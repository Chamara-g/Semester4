import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class GUI extends JFrame implements ActionListener, Runnable {
    private static String[] symbol_list = {"FB", "VRTU", "MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};
    private static int len = symbol_list.length;
    private Item[] display_list;
    private HashMap<String, Item> item_map;
    private JLabel[][] labels;
    private TextArea textarea;
    private HashMap<String, ArrayList<String>> bid_log;
    private TextArea t_filter;


    public GUI(HashMap item_map){
        //create frame
        super("Stocks");
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.item_map = item_map;
        this.labels = new JLabel[len][3];
        this.display_list = new Item[len];
        this.textarea = new TextArea();
        this.bid_log = new HashMap<>();

        //create main panel
        Container c = this.getContentPane();
        c.setLayout(new GridLayout(3, 1));

        //panel 1
        Container c1 = new Container();
        c1.setLayout(new GridLayout(len+1,3));
        c.add(c1);

        c1.add(new JLabel("Symbol", SwingConstants.CENTER));
        c1.add(new JLabel("Name", SwingConstants.CENTER));
        c1.add(new JLabel("Price", SwingConstants.CENTER));
        for (int i = 0; i < len; i++){
            display_list[i] = (Item) item_map.get(symbol_list[i]);
            labels[i][0] = new JLabel(display_list[i].get_symbol(), SwingConstants.CENTER);
            labels[i][1] = new JLabel(display_list[i].get_name(), SwingConstants.CENTER);
            labels[i][2] = new JLabel(String.valueOf(display_list[i].get_price()), SwingConstants.CENTER);
            for (int j = 0; j < 3; j++){
                c1.add(labels[i][j]);
            }
        }

        textarea.setEditable(false);
        c.add(textarea);

        //panel 3
        Container c2 = new Container();
        c2.setLayout(new GridLayout(1,3));
        JLabel l_filter = new JLabel("Enter symbol", SwingConstants.CENTER);
        c2.add(l_filter);
        t_filter = new TextArea();
        c2.add(t_filter);
        JButton b_filter = new JButton("Filter");
        c2.add(b_filter);
        b_filter.addActionListener(this);
        c.add(c2);

        Timer timer = new Timer(500, e -> {
            for (int i = 0; i < len; i++) {
                display_list[i] = (Item) item_map.get(symbol_list[i]);
                labels[i][2].setText(String.valueOf(display_list[i].get_price()));
            }
        });
        timer.start();
    }

    public void update_gui(String sym, String text){
        textarea.append(text);                                      //update textarea
        for (int i = 0; i < len; i++){                              //update display major companies
            if (sym.equals(symbol_list[i])){
                labels[i][2].setText(String.valueOf(display_list[i].get_price()));
            }
        }
        if (!bid_log.containsKey(sym)){                              //update gui_log
            bid_log.put(sym, new ArrayList<String>());
        }
        bid_log.get(sym).add(text);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String sym = t_filter.getText().trim();
        ArrayList<String> l = bid_log.get(sym);
        JFrame f = new JFrame("Filtered bid");
        Container c = f.getContentPane();
        if (l != null){
            TextArea t = new TextArea();
            t.setEditable(false);
            for (String s : l) {
                t.append(s);
            }
            c.add(t);
        }else if(item_map.get(sym) == null){
            c.setPreferredSize(new Dimension(400,100));
            JLabel lab = new JLabel("Item doesn't exist", SwingConstants.CENTER);
            c.add(lab);
        }else {
            c.setPreferredSize(new Dimension(400,100));
            JLabel lab = new JLabel("No bids made for item", SwingConstants.CENTER);
            c.add(lab);
        }
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void run() {
        this.pack();
        this.setVisible(true);
    }
}
