import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

@SuppressWarnings("serial")

class GUI extends JFrame{
    ImageIcon image = new ImageIcon(new ImageIcon("pro_pic.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
    JLabel imageLabel = new JLabel(image);
    JLabel Name, Location, Info, curr_Location;
    JPanel parties;
    GUI(){
        super("GUI-Test");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        Name = new JLabel("Name: Premchand Gopikishan");
        Location = new JLabel("From: Yo Mama's House");
        Info = new JLabel("<html>"+"Lorem ipsum dolor sit amet, consectetur adipiscing elit.Sed dictum elementum ipsum, ut suscipit mi porta sed.Cras eu quam fermentum odio imperdiet ornare at sed nunc.Fusce sed enim suscipit, laoreet neque nec, luctus nunc. Nunc pharetra erat in elit feugiat, porttitor."+"</html>");
        curr_Location = new JLabel("Manipal - 576104");
        parties = vote();
        
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

        Name.setAlignmentX(Component.CENTER_ALIGNMENT);
        Name.setBorder(new EmptyBorder(0, 0, 20, 0));
        Location.setAlignmentX(Component.CENTER_ALIGNMENT);
        Location.setBorder(new EmptyBorder(0, 0, 20, 0));
        Info.setAlignmentX(Component.CENTER_ALIGNMENT);
        Info.setBorder(new EmptyBorder(0, 10, 20, 10));
        curr_Location.setAlignmentX(Component.CENTER_ALIGNMENT);

        parties.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        add(imageLabel);
        add(Name);
        add(Location);
        add(Info);
        add(parties);

        add(Box.createVerticalGlue());
        add(curr_Location);

        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.magenta));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
    }

    private JPanel vote(){
        JPanel jp = new JPanel(new FlowLayout());
        JLabel b2;
        ImageIcon image = new ImageIcon(new ImageIcon("party_1.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        ImageIcon image2 = new ImageIcon(new ImageIcon("party_2.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        JLabel b1 = new JLabel(image);
        b2 = new JLabel(image2);

        jp.add(b1);
        jp.add(b2);
        return jp;
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new GUI();
            }
        });
    }
}
