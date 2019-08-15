import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")

class Login extends JFrame{
    ImageIcon fing_print = new ImageIcon("fingerprint.gif");
    JLabel fing_holder = new JLabel(fing_print);
    JLabel curr_Location = new JLabel("Manipal - 576104");

    JLabel inst;
    String query;
    Connection conn;

    Login(){
        super("Authenticate");
        setSize(fing_print.getIconWidth(),fing_print.getIconHeight() + 120);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        String hostName = "pepperoni.database.windows.net";
        String dbName = "cfd19";
        String user = "potato";
        String password = "BATATAv@d@";
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        
            try{
                conn = DriverManager.getConnection(url);
                String schema = conn.getSchema();
                System.out.println("Successful Conn - Schema: " + schema);
                query = "SELECT * FROM PEOPLE WHERE ID = " + ret_id;
            }
            catch(Exception e){
                e.printStackTrace();
            }

        inst = new JLabel("Place Your finger on the Scanner");
        inst.setAlignmentX(Component.CENTER_ALIGNMENT);
        inst.setBorder(new EmptyBorder(20, 0, 3, 0));
        
        fing_holder.setAlignmentX(Component.CENTER_ALIGNMENT);
        fing_holder.setBorder(new EmptyBorder(20, 0, 5, 0));

        curr_Location.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        /*
            The remaining portion requires Serial Port Communication; Uncomment once done.

            serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {

                // NOTE! - It is extremely important to read the data received from the
                // serial port.  If it does not get read from the receive buffer, the
                // buffer will continue to grow and consume memory.

                // print out the data received to the console
                try {
                    console.println("[ASCII DATA] " + event.getAsciiString());

                    if(!event.getAsciiString().isEmpty()){
                        try(Statement stmt = conn.createStatement();
                            ResultSet resultSet = stmt.executeQuery(query)){
                                String img_url = resultSet.getString(1);
                                String Name = resultSet.getString(2);
                                String Info = resultSet.getString(3);
                                String parties = resultSet.getString(4);
                        }
                    }
                    else{
                        System.out.println("Failed Authentication");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        */

        add(inst);
        add(Box.createVerticalGlue());
        add(fing_holder);
        add(curr_Location);

        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.magenta));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);		
        setVisible(true);
        setResizable(false);
    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Login();
            }
        });
    }
}