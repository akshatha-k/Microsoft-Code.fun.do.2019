import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;
import java.io.*;


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

        try{
            String run_command = "python somescript.py \"V\"";
            Process proc = Runtime.getRuntime().exec(run_command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            while((line = reader.readLine()) != null){
                if(!inst.getText().equalsIgnoreCase(line)){
                    inst.setText(line);
                    inst.paintImmediately(inst.getVisibleRect());
                }
            }
            proc.destroy();
        }
        catch(Exception e){
            //TODO
        }
    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Login();
            }
        });
    }
}
