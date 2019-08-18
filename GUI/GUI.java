import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.net.URL;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

@SuppressWarnings("serial")

class GUI extends JFrame{
    JLabel Name, Location, Info, curr_Location, imageLabel;
    JLabel cand_info;
    JPanel parties;
    String ps[];
    JLabel b2[];
    int k;
    int z = 0;
    int n = 0;
    GUI(String uname, String uinfo, String upic, String uarea, String ps[], int k){
        super("GUI-Test");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        
        this.ps = ps;
        this.k = k;
        try{
                URL url = new URL(upic);
                BufferedImage img = ImageIO.read(url);
                ImageIcon image = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                imageLabel = new JLabel(image);
        }
        catch(Exception e){}
        
        Name = new JLabel("Name: "+uname);
        Location = new JLabel("From: "+uarea);
        Info = new JLabel(uinfo);
        curr_Location = new JLabel("Manipal - 576104");
        parties = vote();
        cand_info = new JLabel("<html>Candidate Info: <br> Name:<br> Region:<br> Party:<br> DOB:<br> Work(s):");
        
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        cand_info.setBorder(new EmptyBorder(0, 50, 0, 0));
        cand_info.setAlignmentX(Component.CENTER_ALIGNMENT);
        Name.setAlignmentX(Component.CENTER_ALIGNMENT);
        Name.setBorder(new EmptyBorder(0, 0, 20, 0));
        Location.setAlignmentX(Component.CENTER_ALIGNMENT);
        Location.setBorder(new EmptyBorder(0, 0, 20, 0));
        Info.setAlignmentX(Component.CENTER_ALIGNMENT);
        Info.setBorder(new EmptyBorder(0, 25, 20, 25));
        curr_Location.setAlignmentX(Component.CENTER_ALIGNMENT);

        parties.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(Box.createVerticalStrut(50));
        add(imageLabel);
        add(Name);
        add(Location);
        add(Info);
        add(Box.createVerticalStrut(50));
        add(parties);
        add(Box.createVerticalStrut(25));
        add(cand_info);

        add(Box.createVerticalGlue());
        add(curr_Location);

        getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.MAGENTA));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    private String onMouseClicked(MouseEvent e) {
        for (int i = 0; i < 10; i++)
            if (e.getSource() == b2[i]) {
                return Integer.toString(i);
            }
        return "";
    }

    private JPanel vote(){
        JPanel jp = new JPanel(new FlowLayout());
        b2 = new JLabel[k];
        
        while(n<k){
            try{
                URL url = new URL(ps[n]);
                BufferedImage img = ImageIO.read(url);
                ImageIcon image = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                b2[n]= new JLabel(image);
                b2[n].setBorder(new EmptyBorder(0, 0, 0, 10));
                b2[n].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e){
                        JOptionPane.showMessageDialog(null, "Vote Casted to "+ onMouseClicked(e));
			new Login();
                        dispose();
                    }
                });
                jp.add(b2[n]);
            }
            catch(Exception e){}
            n++;
        }
        return jp;
    }
}
