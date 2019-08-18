import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;


@SuppressWarnings("serial")

class Login extends JFrame{
    ImageIcon fing_print = new ImageIcon("fingerprint.gif");
    JLabel fing_holder = new JLabel(fing_print);
    JLabel curr_Location = new JLabel("Manipal - 576104");
    public static String uid = "";
    JLabel inst;
    String query;
    Connection conn = null;

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

        add(inst);
        add(Box.createVerticalGlue());
        add(fing_holder);
        add(curr_Location);

        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.magenta));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);		
        setVisible(true);
        setResizable(false);
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // code goes here.
                 try{
                        String run_command = "python script.py V"; //begins validation
                        Process proc = Runtime.getRuntime().exec(run_command);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                        String line = "";
                        while((line = reader.readLine()) != null) {
                            uid = line;
                        }
                        proc.waitFor();
                        proc.destroy();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                try{
                        String run_command = "python script.py B"; // ends validation
                        Process proc = Runtime.getRuntime().exec(run_command);
                        proc.waitFor();
                        proc.destroy();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                String query = "SELECT * FROM dbo.voter_data where uid = "+uid;
                try{
                    Statement stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);
                    resultSet.next();
                    String uname = resultSet.getString(2);
                    String uinfo = resultSet.getString(3);
                    String upic = resultSet.getString(4);
                    String uarea = resultSet.getString(5);
                    String query2 = "SELECT cpic from dbo.candidate_data where carea = \'"+uarea+"\'";
                    resultSet = stmt.executeQuery(query2);
                    String[] imgs = new String[4];
                    int k = 0;
                    while(resultSet.next()){
                        imgs[k] = resultSet.getString(1);
                        k++;
                    }

                    
                    System.out.println(k);
                    new GUI(uname, uinfo, upic, uarea, imgs, k);
                    conn.close();
                }
                catch(Exception e){System.out.println("Error");}
                dispose();
            }
        });
        t1.start();
    }
    
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Login();
            }
        });
    }
}
