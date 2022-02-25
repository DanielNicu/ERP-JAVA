package p3;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WorkerFrame1 extends JFrame  {
	
	 WorkerFrame1(){
		
    	final List<Order> a = new ArrayList<Order>();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1000,1000);    
        getContentPane().setLayout(null);
        JScrollPane scroll=new JScrollPane();
        scroll.setBounds(70,80,800,800);
        getContentPane().add(scroll);
        final JTable table=new JTable();
        scroll.setViewportView(table);
        DefaultTableModel model = new DefaultTableModel()
        {

			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column)
         	{
         		switch(column)
         		{
         		case 0:
         			return Integer.class;
         		case 1:
         			return Integer.class;
         		case 2:
         			return Integer.class;
         		case 3:
         			return String.class;
         		case 4:
         			return Boolean.class;
         			default:
         				return Integer.class;
         		}
         	}
        };
        table.setModel(model);
        model.addColumn("Order Id");
        model.addColumn("Book Type");
        model.addColumn("Quantity");
        model.addColumn("Date Created");
        model.addColumn("Modify");
        int i=0;
        for(Order or: a) {
        	if(or.getDate_finished().equals("2021-10-21")) {
        	model.addRow(new Object[0]);
        	model.setValueAt(or.getId(),i,0);
        	model.setValueAt(or.getType(), i, 1);
        	model.setValueAt(or.getQuantity(), i, 2);
        	model.setValueAt(or.getDate_created(), i, 3);
        	model.setValueAt(false, i, 4);
        	i++;}
        }
        JButton back = new JButton("MODIFY");
        back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i=0; i<table.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(table.getValueAt(i, 4).toString());
					int id = Integer.valueOf(table.getValueAt(i, 0).toString());
					if (checked) {
						del(id,a);
					}
				}
				WorkerFrame1 nextframe = new WorkerFrame1();
            	nextframe.setVisible(true);
                dispose();
			}
        	
        });
        back.setBounds(20,30,130,30);
        getContentPane().add(back);
        JButton add = new JButton("ADD");
        add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				 AddFrame nextframe = new AddFrame();
				 nextframe.setTitle("BOOK ERP");
	             nextframe.setVisible(true);
	             nextframe.setBounds(10, 10, 300, 600);        
	             nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             dispose();
			}
        	
        });
        add.setBounds(160,30,130,30);
        getContentPane().add(add);
	}
	 public void del(int id, List<Order> a) {
		 Date todaysDate = new Date();
		 DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
		 String d = df3.format(todaysDate);
		 for(Order or:a) {
			 if(or.getId()==id) {
				 
				 or.setDate_finished(d);
				 try {
			    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
			    		Statement stmt = null;
			    		try {
			    			stmt = connection.createStatement();
			    			stmt.executeUpdate("UPDATE orderb SET date_finished = DATE ('" + or.getDate_finished() +    "') WHERE oid = " + String.valueOf(or.getId()));
			    			
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
	 }
	
}
