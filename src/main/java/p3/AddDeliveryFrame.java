package p3;
import javax.swing.*;


import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddDeliveryFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container container = getContentPane();
    JLabel inkLabel = new JLabel("ink delivery:");
    JLabel paperLabel = new JLabel("paper delivery:");
    JLabel glueLabel = new JLabel("glue delivery:");
    JLabel coverLabel = new JLabel("cover delivery:");
    JTextField inkText = new JTextField("ayayaya");
    JTextField paperText = new JTextField("ayayaya");
    JTextField glueText = new JTextField("ayayaya");
    JTextField coverText = new JTextField("ayayaya");
    JTextField sinkText = new JTextField("ayayaya");
    JTextField spaperText = new JTextField("ayayaya");
    JTextField sglueText = new JTextField("ayayaya");
    JTextField scoverText = new JTextField("ayayaya");
    JTextField max_inkText = new JTextField("ayayaya");
    JTextField max_paperText = new JTextField("ayayaya");
    JTextField max_glueText = new JTextField("ayayaya");
    JTextField max_coverText = new JTextField("ayayaya");
    JButton submit = new JButton("SUBMIT");
    JButton cancel = new JButton("CANCEL");
    
    AddDeliveryFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
    	inkLabel.setBounds(50, 150, 100, 30);
    	paperLabel.setBounds(50, 220, 100, 30);
    	glueLabel.setBounds(50, 290, 100, 30);
    	coverLabel.setBounds(50, 360, 100, 30);
    	inkText.setBounds(150, 150, 100, 30);
    	paperText.setBounds(150, 220, 100, 30);
    	glueText.setBounds(150, 290, 100, 30);
    	coverText.setBounds(150, 360, 100, 30);
    	submit.setBounds(40,420,100,30);
    	cancel.setBounds(180,420,100,30);

    }
    public void addActionEvent() {
        submit.addActionListener(this);
        cancel.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
        	int ok=1;
            if(Integer.parseInt(getink())>Integer.parseInt(getmax_ink())) {
            	ok=0;
            	JOptionPane.showMessageDialog(rootPane, "Ink level is too high");
            }
            if(Integer.parseInt(getpaper())>Integer.parseInt(getmax_paper())) {
            	ok=0;
            	JOptionPane.showMessageDialog(rootPane, "Paper level is too high");
            }
            if(Integer.parseInt(getglue())>Integer.parseInt(getmax_glue())) {
            	ok=0;
            	JOptionPane.showMessageDialog(rootPane, "Glue level is too high");
            }
            if(Integer.parseInt(getcover())>Integer.parseInt(getmax_cover())) {
            	ok=0;
            	JOptionPane.showMessageDialog(rootPane, "Cover level is too high");
            }
            if(ok==1)
            {
            	updatestorehouse(getink(),getpaper(),getglue(),getcover());
            	Storehouse b = new Storehouse(Integer.parseInt(this.getink()),Integer.parseInt(this.getpaper()),Integer.parseInt(this.getglue()),Integer.parseInt(this.getcover()),Integer.parseInt(this.getmax_ink()),Integer.parseInt(this.getmax_paper()),Integer.parseInt(this.getmax_glue()),Integer.parseInt(this.getmax_cover()));
            	StorehouseFrame nextframe = new StorehouseFrame();
                nextframe.setink(String.valueOf(this.getink()));
                nextframe.setpaper(String.valueOf(this.getpaper()));
                nextframe.setglue(String.valueOf(this.getglue()));
                nextframe.setcover(String.valueOf(this.getcover()));
                nextframe.setmax_ink(String.valueOf(this.getmax_ink()));
                nextframe.setmax_paper(String.valueOf(this.getmax_paper()));
                nextframe.setmax_glue(String.valueOf(this.getmax_glue()));
                nextframe.setmax_cover(String.valueOf(this.getmax_cover()));
                nextframe.setTitle("BOOK ERP");
                nextframe.setVisible(true);
                nextframe.setBounds(10, 10, 800, 900);         
                nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                if(b.verifyInk())
                {
                    nextframe.setred1();
                }
                if(b.verifyPaper())
                {
                    nextframe.setred2();
                }
                if(b.verifyGlue())
                {
                    nextframe.setred3();
                }
                if(b.verifyCover())
                {
                    nextframe.setred4();
                }
                this.dispose();
            }
        }
        if(e.getSource() == cancel) {
        	Storehouse b = new Storehouse(Integer.parseInt(this.sgetink()),Integer.parseInt(this.sgetpaper()),Integer.parseInt(this.sgetglue()),Integer.parseInt(this.sgetcover()),Integer.parseInt(this.getmax_ink()),Integer.parseInt(this.getmax_paper()),Integer.parseInt(this.getmax_glue()),Integer.parseInt(this.getmax_cover()));
        	StorehouseFrame nextframe = new StorehouseFrame();
            nextframe.setink(String.valueOf(this.sgetink()));
            nextframe.setpaper(String.valueOf(this.sgetpaper()));
            nextframe.setglue(String.valueOf(this.sgetglue()));
            nextframe.setcover(String.valueOf(this.sgetcover()));
            nextframe.setmax_ink(String.valueOf(this.getmax_ink()));
            nextframe.setmax_paper(String.valueOf(this.getmax_paper()));
            nextframe.setmax_glue(String.valueOf(this.getmax_glue()));
            nextframe.setmax_cover(String.valueOf(this.getmax_cover()));
            nextframe.setTitle("BOOK ERP");
            nextframe.setVisible(true);
            nextframe.setBounds(10, 10, 800, 900);        
            nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            if(b.verifyInk())
            {
                nextframe.setred1();
            }
            if(b.verifyPaper())
            {
                nextframe.setred2();
            }
            if(b.verifyGlue())
            {
                nextframe.setred3();
            }
            if(b.verifyCover())
            {
                nextframe.setred4();
            }
            this.dispose();
        }
    }
    public void addComponentsToContainer() {
        container.add(inkLabel);
        container.add(paperLabel);
        container.add(glueLabel);
        container.add(coverLabel);
        container.add(inkText);
        container.add(paperText);
        container.add(glueText);
        container.add(coverText);
        container.add(submit);
        container.add(cancel);
    }
    
    public void setink(String user) {this.inkText.setText(user);}
    public String getink() {return this.inkText.getText();}
    public void setpaper(String user) {this.paperText.setText(user);}
    public String getpaper() {return this.paperText.getText();}
    public void setglue(String user) {this.glueText.setText(user);}
    public String getglue() {return this.glueText.getText();}
    public void setcover(String user) {this.coverText.setText(user);}
    public String getcover() {return this.coverText.getText();}
    public void setmax_ink(String user) {this.max_inkText.setText(user);}
    public String getmax_ink() {return this.max_inkText.getText();}
    public void setmax_paper(String user) {this.max_paperText.setText(user);}
    public String getmax_paper() {return this.max_paperText.getText();}
    public void setmax_glue(String user) {this.max_glueText.setText(user);}
    public String getmax_glue() {return this.max_glueText.getText();}
    public void setmax_cover(String user) {this.max_coverText.setText(user);}
    public String getmax_cover() {return this.max_coverText.getText();}
    public void ssetink(String user) {this.sinkText.setText(user);}
    public String sgetink() {return this.sinkText.getText();}
    public void ssetpaper(String user) {this.spaperText.setText(user);}
    public String sgetpaper() {return this.spaperText.getText();}
    public void ssetglue(String user) {this.sglueText.setText(user);}
    public String sgetglue() {return this.sglueText.getText();}
    public void ssetcover(String user) {this.scoverText.setText(user);}
    public String sgetcover() {return this.scoverText.getText();}
    public void updatestorehouse(String a, String b, String c, String d) {
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		try {
    			stmt = connection.createStatement();
    			stmt.executeUpdate("UPDATE storehouse SET ink = "+ a +", paper = "+ b + ", glue = "+ c +", cover =  "+ d);
    			
    		}
    		catch (SQLException ex)
    		{
    			//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    		}
    	} catch (SQLException e) {
    		
    		//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    	
    			}
    }
}
