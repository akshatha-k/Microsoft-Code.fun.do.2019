package library;

import java.sql.*;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

@SuppressWarnings("serial")
class GUI1 extends JFrame implements ActionListener{
	/**
	 * This is the first Page that shows after a successful login. The User can then click on the appropriate buttons to get to the required functionalities.
	 * 
	 */
	JButton btn1, btn2, btn3, btn4, btn5, btn6, bchangepass, bupdatepass;
	JLabel lb_welcome, lb1, cred_lbl, loldpass, lnewpass;
	JPasswordField toldpass, tnewpass;
	Image image = new ImageIcon(this.getClass().getResource("/resources/icon.png")).getImage();
	static int i = 0;
	Statement stmt;
	Connection conn;
	int clkd=0;
	ResultSet rs;
	String login_id;
	
	GUI1(Connection conn, String tx){
		super("Library-Management System");
		setSize(1000, 540);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setIconImage(image);
		this.conn = conn;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.login_id = tx;
		
		//LABEL DEFINITION
		cred_lbl = new JLabel("Developed by: Shashank Shirol and Rajat Shenoy");
		cred_lbl.setFont(new Font("Monospaced", Font.BOLD, 15));
		cred_lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		loldpass = new JLabel("Current Password: ");
		loldpass.setFont(new Font("Sans", Font.BOLD, 9));
		loldpass.setAlignmentX(Component.CENTER_ALIGNMENT);
		loldpass.setVisible(false);
		
		lnewpass = new JLabel("New Password: ");
		lnewpass.setFont(new Font("Sans", Font.BOLD, 9));
		lnewpass.setAlignmentX(Component.CENTER_ALIGNMENT);
		lnewpass.setVisible(false);
		
		lb1 = new JLabel("Library Management System");
		lb1.setFont(new Font("Monospaced", Font.BOLD, 26));
		lb1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lb1.setBorder(new EmptyBorder(30, 0, 40, 0));
		
		//PASSWORD 
		toldpass = new JPasswordField(32);
		toldpass.setAlignmentX(Component.CENTER_ALIGNMENT);
		toldpass.setMaximumSize(new Dimension(200, 20));
		toldpass.setVisible(false);
        
        tnewpass = new JPasswordField(32);
        tnewpass.setAlignmentX(Component.CENTER_ALIGNMENT);
        tnewpass.setMaximumSize(new Dimension(200, 20));
        tnewpass.setVisible(false);
		
		btn2 = new JButton("Issue Details");
		btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btn1 = new JButton("Books");
		btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		bchangepass = new JButton("Change Password");
		bchangepass.setAlignmentX(Component.CENTER_ALIGNMENT);
		Dimension d = bchangepass.getMaximumSize();
		btn1.setMaximumSize(new Dimension(d));
		btn2.setMaximumSize(new Dimension(d));
		
		bupdatepass = new JButton("Update Password");
		bupdatepass.setAlignmentX(Component.CENTER_ALIGNMENT);
		bupdatepass.setMaximumSize(new Dimension(d));
		bupdatepass.setVisible(false);
		
		btn3 = new JButton("Members");
		btn3.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn3.setMaximumSize(new Dimension(d));
		
		btn6 = new JButton("Publishers");
		btn6.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn6.setMaximumSize(new Dimension(d));
		
		btn4 = new JButton("Logout");
		btn4.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn4.setMaximumSize(new Dimension(d));
		
		btn5 = new JButton("Exit");
		btn5.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn5.setMaximumSize(new Dimension(d));
		
		btn1.setActionCommand("btn1");
		btn2.setActionCommand("btn2");
		btn3.setActionCommand("btn3");
		btn4.setActionCommand("btn4");
		btn5.setActionCommand("btn5");
		btn6.setActionCommand("btn6");
		bchangepass.setActionCommand("bchangepass");
		bupdatepass.setActionCommand("bupdatepass");
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		bchangepass.addActionListener(this);
		bupdatepass.addActionListener(this);
		
		add(lb1);
		add(btn1);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(btn2);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(btn3);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(btn6);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(bchangepass);
		add(loldpass);
		add(toldpass);
		add(lnewpass);
		add(tnewpass);
		add(bupdatepass);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(btn4);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(btn5);
		
		
		add(Box.createVerticalGlue());
		add(cred_lbl);
		
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.darkGray));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("btn1")){
			new Book(this, 1, stmt, login_id).setVisible(true);
			setVisible(false);
		}
		else if (e.getActionCommand().contentEquals("btn2")){
			new Issue(this, 0, stmt, "").setVisible(true);
			setVisible(false);
		}
		else if (e.getActionCommand().equals("btn3")){
			new Members(this,stmt).setVisible(true);
			setVisible(false);
		}
		else if (e.getActionCommand().equals("btn4")){
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new Login();
			dispose();
		}
		else if (e.getActionCommand().equals("btn6")){
			new Publisher(this, stmt).setVisible(true);
			setVisible(false);
		}
		else if (e.getActionCommand().equals("bchangepass")) {
			
			if(clkd==0) {
				loldpass.setVisible(true); lnewpass.setVisible(true); toldpass.setVisible(true); tnewpass.setVisible(true);
				bupdatepass.setVisible(true); bchangepass.setText("Cancel");
				clkd=1;
			}
			else {
				loldpass.setVisible(false); lnewpass.setVisible(false); toldpass.setVisible(false); tnewpass.setVisible(false);
				bupdatepass.setVisible(false); clkd=0; bchangepass.setText("Change Password");
			}
			
		}
		else if (e.getActionCommand().equals("bupdatepass")) {
			
			loldpass.setVisible(false); lnewpass.setVisible(false); toldpass.setVisible(false); tnewpass.setVisible(false);
			bupdatepass.setVisible(false); clkd=0; bchangepass.setText("Change Password");
			
			//Checking for correct password
			try {
				rs = stmt.executeQuery("select password from login where lib_id="+login_id);
				if(!rs.next()) JOptionPane.showMessageDialog(null,"Invalid Password", "Alert!", JOptionPane.INFORMATION_MESSAGE);
				else {
					String chk = rs.getString(1);
					if(Arrays.equals(chk.toCharArray(), toldpass.getPassword())){
						System.out.println("Update pass");
						String np = String.copyValueOf(tnewpass.getPassword());
						stmt.executeUpdate("UPDATE LOGIN SET PASSWORD='"+np+"' where lib_id="+login_id);
						JOptionPane.showMessageDialog(null,"Password Updated Successfully!", "Alert!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			catch(SQLException eee) {
				JOptionPane.showMessageDialog(null,"Fatal Error Encountered.", "Alert!", JOptionPane.INFORMATION_MESSAGE);
			}
		
			
		}
		else {
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}
}