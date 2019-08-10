import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

@SuppressWarnings("serial")

class Login extends JFrame{
    ImageIcon fing_print = new ImageIcon("fingerprint.gif");
    JLabel fing_holder = new JLabel(fing_print);
    JLabel curr_Location = new JLabel("Manipal - 576104");

    JLabel inst;

    Login(){
        super("Authenticate");
        setSize(fing_print.getIconWidth(),fing_print.getIconHeight() + 120);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

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

                    if(event.getAsciiString().equals("1"))
                        new GUI(#sendID);
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