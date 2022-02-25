package p3;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container container = getContentPane();
    JLabel inkLabel = new JLabel("Book type:");
    JLabel paperLabel = new JLabel("Quantity:");
    JTextField inkText = new JTextField("");
    JTextField paperText = new JTextField("");
    JButton submit = new JButton("SUBMIT");
    JButton cancel = new JButton("CANCEL");
    
    AddFrame() {
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
    	inkText.setBounds(150, 150, 100, 30);
    	paperText.setBounds(150, 220, 100, 30);
    	submit.setBounds(40,420,100,30);
    	cancel.setBounds(180,420,100,30);

    }
    public void addActionEvent() {
        submit.addActionListener(this);
        cancel.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
        	if (Integer.parseInt(this.inkText.getText()) != 1 && Integer.parseInt(this.inkText.getText()) != 2 && Integer.parseInt(this.inkText.getText()) != 3) {
        		 JOptionPane.showMessageDialog(this, "Invalid book type");
        	}
        	else {
        	List<Order> a = getOrders();
        	Storehouse b = getStorehouse();
        	Book h = getBook();
        	if(b.getInk()<((h.getInk()*Integer.parseInt(this.paperText.getText())/10)) || b.getPaper()<((h.getPaper()*Integer.parseInt(this.paperText.getText())/10)) || b.getGlue()<((h.getGlue()*Integer.parseInt(this.paperText.getText())/10)) || b.getCover()<((h.getCover()*Integer.parseInt(this.paperText.getText())/10))){
        		JOptionPane.showMessageDialog(this, "The order cannot be placed\nThe resources needed passes the resources we currently have");
        	}
        	else {
        	updateOrders(a);
    		b = updatestorehouse(b,h);
        	WorkerFrame1 nextframe = new WorkerFrame1();
        	nextframe.setVisible(true);
            this.dispose();    
            }}
        	}
        
        if(e.getSource() == cancel) {
        	WorkerFrame1 nextframe = new WorkerFrame1();
        	nextframe.setVisible(true); 
            this.dispose();
        }
    }
    public void addComponentsToContainer() {
        container.add(inkLabel);
        container.add(paperLabel);
        container.add(inkText);
        container.add(paperText);
        container.add(submit);
        container.add(cancel);
    }
    public List<Order> getOrders(){
    	final List<Order> a = new ArrayList<Order>();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		ResultSet rs = null;
    		try {
    			stmt = connection.createStatement();
    			rs =stmt.executeQuery("Select typeb,ink,paper,glue,cover,dept,oid,quantity,date_created,date_finished,dept,cid from Orderb inner join Book using (typeb)");
    			while (rs.next()) {
    				a.add(new Order(rs.getInt("typeb"),rs.getInt("ink"),rs.getInt("paper"),rs.getInt("glue"),rs.getInt("cover"),rs.getInt("oid"),rs.getInt("quantity"),dateFormat.format(rs.getDate("date_created")),dateFormat.format(rs.getDate("date_finished")),rs.getInt("dept"),rs.getInt("cid")));
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
    	return a;
    }
    public void updateOrders(List<Order> a) {
    	 Date todaysDate = new Date();
		 DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
		 String d = df3.format(todaysDate);
				 try {
			    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
			    		Statement stmt = null;
			    		try {
			    			stmt = connection.createStatement();
			    			stmt.executeUpdate("INSERT INTO `orderb` (`typeb`,`quantity`,`date_created`,`date_finished`,`cid`,`dept`) values("+ Integer.parseInt(this.inkText.getText())  +","+Integer.parseInt(this.paperText.getText())+", DATE('"+ d +"'),DATE('2021-10-21'),2,10);");
			    			
			    		}
			    		catch (SQLException ex)
			    		{
			    			//JOptionPane.showMessageDialog(this, "Error connecting to the database");
			    		}
			    	} catch (SQLException e) {
			    		
			    		//JOptionPane.showMessageDialog(this, "Error connecting to the database");
			    	
			    			}
			 }
    public Storehouse getStorehouse() {
    	Storehouse a=null;
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		ResultSet rs = null;
    		try {
    			stmt = connection.createStatement();
    			rs =stmt.executeQuery("Select ink,paper,glue,cover,max_ink,max_paper,max_glue,max_cover from Storehouse ");
    			while (rs.next()) {
    				a=new Storehouse(rs.getInt("ink"),rs.getInt("paper"),rs.getInt("glue"),rs.getInt("cover"),rs.getInt("max_ink"),rs.getInt("max_paper"),rs.getInt("max_glue"),rs.getInt("max_cover"));
 
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
    	return a;
    }
    public List<Order> createOrder(){
    	List<Order> a = new ArrayList<Order>();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		ResultSet rs = null;
    		try {
    			stmt = connection.createStatement();
    			rs =stmt.executeQuery("Select typeb,ink,paper,glue,cover,dept,oid,quantity,date_created,date_finished,dept,cid from Orderb join Book using (typeb)");
    			while (rs.next()) {
    				a.add(new Order(rs.getInt("typeb"),rs.getInt("ink"),rs.getInt("paper"),rs.getInt("glue"),rs.getInt("cover"),rs.getInt("oid"),rs.getInt("quantity"),dateFormat.format(rs.getDate("date_created")),dateFormat.format(rs.getDate("date_finished")),rs.getInt("dept"),rs.getInt("cid")));
    			}
    			
    		}
    		catch (SQLException ex)
    		{
    			//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    		}
    		finally {
    			try {
    				rs.close();
    			}catch (SQLException sqlEx) {}
    			rs= null;
    		}
    	} catch (SQLException e) {
    		
    		//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    	
    			}
    	return a;
    }
    public List<Dept> createDept()
    {
    	List<Dept> a = new ArrayList<Dept>();
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		ResultSet rs = null;
    		try {
    			stmt = connection.createStatement();
    			rs =stmt.executeQuery("Select deptno, job, ordersnf from Dept ");
    			while (rs.next()) {
    				a.add(new Dept(rs.getInt("deptno"),rs.getString("job"),rs.getInt("ordersnf")));
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
    	return a;
    }
    public Dept getmindept(List<Dept> a) {
    	int min=9999;
    	int i=-1;
    	int j=0;
    	for(Dept dept: a) {
    		i=i+1;
    		if(dept.getOrdersnf()<min && dept.getJob()=="Workers")
    		{
    			min=dept.getOrdersnf();
    			j=i;
    		}
    	}
    	return a.get(j);
    }
    public Order updateord(Order ord, Dept dept) {
    	ord.setDept(dept.getDeptno());
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		try {
    			stmt = connection.createStatement();
    			stmt.executeUpdate("UPDATE orderb SET dept = " + String.valueOf(dept.getDeptno()) +    " WHERE id = " + String.valueOf(ord.getId()));
    			
    		}
    		catch (SQLException ex)
    		{
    			//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    		}
    	} catch (SQLException e) {
    		
    		//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    	
    			}
    	return ord;
    }
    public List<Dept> updatedept (Dept dept, List<Dept> de) {
    	for (Dept dep: de) {
    		if(dep.getDeptno()==dept.getDeptno()) {
    	dep.setOrdersnf(dep.getOrdersnf()+1);
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		try {
    			stmt = connection.createStatement();
    			stmt.executeUpdate("UPDATE dept SET ordersnf = " + String.valueOf(dep.getOrdersnf()) +    " WHERE deptno = " + String.valueOf(dep.getDeptno()));
    			
    		}
    		catch (SQLException ex)
    		{
    			//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    		}
    	} catch (SQLException e) {
    		
    		//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    	
    			}
    	
    	}}
    	return de;
    }
    public Storehouse updatestorehouse(Storehouse s, Book o) {
    	s.setInk(s.getInk()-((o.getInk()*Integer.parseInt(this.paperText.getText()))/10));
    	s.setPaper(s.getPaper()-((o.getPaper()*Integer.parseInt(this.paperText.getText()))/10));
    	s.setGlue(s.getGlue()-((o.getGlue()*Integer.parseInt(this.paperText.getText()))/10));
    	s.setCover(s.getCover()-((o.getCover()*Integer.parseInt(this.paperText.getText()))/10));
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		try {
    			stmt = connection.createStatement();
    			stmt.executeUpdate("UPDATE storehouse SET ink = "+ String.valueOf(s.getInk()) +", paper = "+ String.valueOf(s.getPaper()) + ", glue = "+ String.valueOf(s.getGlue()) +", cover =  "+ String.valueOf(s.getCover()));
    			
    		}
    		catch (SQLException ex)
    		{
    			JOptionPane.showMessageDialog(this, "Error connecting to the database");
    		}
    	} catch (SQLException e) {
    		
    		JOptionPane.showMessageDialog(this, "Error connecting to the database");
    	
    			}
    	return s;
    }
    public Book getBook() {
    	Book a=new Book(0,0,0,0,0);
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		ResultSet rs = null;
    		try {
    			stmt = connection.createStatement();
    			rs =stmt.executeQuery("Select typeb,ink,paper,glue,cover from Book where typeb = "+ Integer.parseInt(this.inkText.getText()));
    			while (rs.next()) {
    				a.setType(rs.getInt("typeb"));
    				a.setInk(rs.getInt("ink"));
    				a.setPaper(rs.getInt("paper"));
    				a.setGlue(rs.getInt("glue"));
    				a.setCover(rs.getInt("cover"));
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
    	return a;
    }
}
