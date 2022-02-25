package p3;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
public class ProviderFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JTextField inkText = new JTextField("ayayaya");
    JTextField paperText = new JTextField("ayayaya");
    JTextField glueText = new JTextField("ayayaya");
    JTextField coverText = new JTextField("ayayaya");
    JTextField max_inkText = new JTextField("ayayaya");
    JTextField max_paperText = new JTextField("ayayaya");
    JTextField max_glueText = new JTextField("ayayaya");
    JTextField max_coverText = new JTextField("ayayaya");
    JButton back = new JButton("BACK");
    
    ProviderFrame(){
    	JFrame f = new JFrame();
    	int j=0;
    	List<Provider> a = new ArrayList<Provider>();
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		ResultSet rs = null;
    		try {
    			stmt = connection.createStatement();
    			rs =stmt.executeQuery("Select pname, material, pcontact, delivery_time from Provider ");
    			while (rs.next()) {
    				a.add(new Provider(rs.getString("pname"),rs.getString("material"),rs.getString("pcontact"),rs.getInt("delivery_time")));
    				j++;
    			}
    			
    		}
    		catch (SQLException ex)
    		{
    			JOptionPane.showMessageDialog(this, "Error connecting to the database");
    		}
    		finally {
    			try {
    				rs.close();
    			}catch (SQLException sqlEx) {}
    			rs= null;
    		}
    	} catch (SQLException e) {
    		
    		JOptionPane.showMessageDialog(this, "Error connecting to the database");
    			}
    	String[][] data= new String[j][4];
    	int i=0;
    	for(Provider ad : a) {
    		data[i][0]= ad.getName();
    		data[i][1]=ad.getMaterial();
    		data[i][2]=ad.getContact();
    		data[i][3]=String.valueOf(ad.getDelivery_time());
    		i++;
    	}
    	String[] names = {
        		"Name",
        		"Material",
        		"Contact",
        		"Delivery time"
        };
    	JTable jt=new JTable(data,names);
    	back.setBounds(400,150,100,30);
    	back.addActionListener(this);
    	jt.setBounds(30,40,500,500);          
        JScrollPane sp=new JScrollPane(jt);  
        sp.add(back);
        f.add(sp); 
        f.setSize(600,400);    
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == back ) {
            this.setVisible(false);
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
                JOptionPane.showMessageDialog(rootPane, "Ink level is low");
            }
            if(b.verifyPaper())
            {
                nextframe.setred2();
                JOptionPane.showMessageDialog(rootPane, "Paper level is low");
            }
            if(b.verifyGlue())
            {
                nextframe.setred3();
                JOptionPane.showMessageDialog(rootPane, "Glue level is low");
            }
            if(b.verifyCover())
            {
                nextframe.setred4();
                JOptionPane.showMessageDialog(rootPane, "Cover level is low");
            }

            this.dispose();
    	}	
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
}
