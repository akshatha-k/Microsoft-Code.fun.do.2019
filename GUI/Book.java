package library;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.sql.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import layout.SpringUtilities;

@SuppressWarnings("serial")
public class Book extends JFrame{
	JFrame parent;
	String login_id;
	JButton btn, btn2;
	Image image = new ImageIcon(this.getClass().getResource("/resources/icon.png")).getImage();
	JRadioButton jrb;
	boolean ins_flag = true;
	ArrayList<JTextField> ret_list = new ArrayList<JTextField>();
	ArrayList<JTextField> ins_list = new ArrayList<JTextField>();
	String filters[] = new String[5];
	
	Book(JFrame parent, int n, Statement stmt, String login_id){
		super("Library-Management System");
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));;
		setIconImage(image);
		this.parent = parent;
		this.login_id = login_id;
		btn = new JButton("Back");
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				parent.setVisible(true);
			}
		});
		
		JPanel p1 = ins_gui();
		JPanel p2 = ret_gui();
		
		JTabbedPane tp = new JTabbedPane();
		tp.setTabPlacement(JTabbedPane.TOP);
		tp.setBorder(new EmptyBorder(0, 10, 10, 10));
		if(n == 1)
			tp.add("Insert", p1);
		tp.add("Retrieve", p2);
		
		btn2 = new JButton("Submit");
		btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tp.getSelectedIndex() == 0 && n == 1) {
					//INSERTION
					String base = "INSERT INTO BOOK VALUES(";
					String append = "";
					try {
						for (int i = 0; i<ins_list.size(); i++) {
							if(ins_list.get(i).getText().trim().equalsIgnoreCase("")) {
								ins_flag = false;
								break;
							}
							if(i == 0 || i == 3 || i == 4 || i == 5){
								append = append + ins_list.get(i).getText().trim() + ",";
							}
							else if(i == 7) {
								append = append + ins_list.get(i).getText().trim();
							}
							else {
								append = append + "'" + ins_list.get(i).getText().trim() + "'" + ",";
							}
						}
						append = append + ")";
						
						//Processing the string:
						if(ins_flag) {
							String pub_no = ins_list.get(4).getText().trim();
							ResultSet rs = stmt.executeQuery("select * from publisher where pub_no = "+pub_no);
							if(!rs.next()) {
								JOptionPane.showMessageDialog(getRootPane(), "Insert the new publisher to the Publisher Table first.", "Alert", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								stmt.executeUpdate(base + append);
								JOptionPane.showMessageDialog(getRootPane(), "Record Inserted Successfully!!!", "Alert", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else JOptionPane.showMessageDialog(getRootPane(), "Record cannot be inserted. Fields cannot be empty.", "Alert", JOptionPane.INFORMATION_MESSAGE);
					}
					catch(SQLException ee) {
						System.out.println("here");
						JOptionPane.showMessageDialog(getRootPane(), "An unexpected error occured while execution. Please contact the developer.", "Alert", JOptionPane.INFORMATION_MESSAGE);
					}
				
					
				}
				else {
					//RETRIEVE filter text box data to strings
					for (int i = 0; i<ret_list.size(); i++) {
						try {
							filters[i] = ret_list.get(i).getText().trim();
						}
						catch(Exception eeee) {
							filters[i] = "";
						}
					}
					
					new Result(ret_list, btn2, filters[0], filters[1], filters[2], filters[3], filters[4], stmt, n, login_id).setVisible(true);
					btn2.setEnabled(false);
				}
			}
		});
		
		add(Box.createRigidArea(new Dimension(0,10)));
		add(btn);
		add(Box.createRigidArea(new Dimension(0,10)));		
		add(tp);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(btn2);
		add(Box.createRigidArea(new Dimension(0,10)));
		
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.darkGray));		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	parent.setVisible(true);
            }
        });
		
		setVisible(true);
	}
	
	private JPanel ret_gui() {
		// TODO Auto-generated method stub
		int i;
		String[] labels = {"Title:", "Author:", "ISBN:", "Publisher:", "Genre:"};
		JPanel p = new JPanel(new SpringLayout());
		p.setBorder(new EmptyBorder(30, 300, 10, 300));
		
		for(i = 0; i<labels.length; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			JTextField tf = new JTextField();
			tf.setMaximumSize(new Dimension(200, 20));
			l.setLabelFor(tf);
			p.add(tf);
			ret_list.add(tf);
		}
		SpringUtilities.makeCompactGrid(p, labels.length, 2,
										10,10,
										10,10);
		
		
		
		return p;
	}
	private JPanel ins_gui() {
		// TODO Auto-generated method stub
		int i;
		String[] labels = {"ISBN:", "Title:", "Author:", "No. of Copies:", "Publisher No.:", "Edition:", "Genre:", "Aisle:"};
		JPanel p = new JPanel(new SpringLayout());
		p.setBorder(new EmptyBorder(30, 300, 10, 300));
		
		for(i = 0; i<labels.length; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			p.add(l);
			JTextField tf = new JTextField();
			tf.setMaximumSize(new Dimension(200, 20));
			l.setLabelFor(tf);
			p.add(tf);
			ins_list.add(tf);
		}
		SpringUtilities.makeCompactGrid(p, labels.length, 2,
										10,10,
										10,10);		
		return p;
	}
}
