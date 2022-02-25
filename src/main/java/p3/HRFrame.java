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

public class HRFrame extends JFrame  {
	
	 HRFrame(){
		
    	final List<Employee> a = new ArrayList<Employee>(); 
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		ResultSet rs = null;
    		try {
    			stmt = connection.createStatement();
    			rs =stmt.executeQuery("Select id,role,name,deptno,salary,email,vacancy from employee");
    			while (rs.next()) {
    				a.add(new Employee(rs.getInt("id"),rs.getString("role"),rs.getString("name"),rs.getInt("deptno"),rs.getInt("salary"),rs.getString("email"),rs.getInt("vacancy")));
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
        setBounds(0,0,1500,1300);    
        getContentPane().setLayout(null);
        JScrollPane scroll=new JScrollPane();
        scroll.setBounds(0,80,1500,700);
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
         			return String.class;
         		case 2:
         			return String.class;
         		case 3:
         			return Integer.class;
         		case 4:
         			return Integer.class;
         		case 5:
         			return String.class;
         		case 6:
         			return String.class;
         		case 7:
         			return Boolean.class;
         			default:
         				return Integer.class;
         		}
         	}
        };
        table.setModel(model);
        model.addColumn("Id");
        model.addColumn("Role");
        model.addColumn("Name");
        model.addColumn("Depatament");
        model.addColumn("Salary");
        model.addColumn("Email");
        model.addColumn("Is in vacancy");
        model.addColumn("Select");
        int i=0;
        for(Employee or: a) {
        	model.addRow(new Object[0]);
        	model.setValueAt(or.getId(),i,0);
        	model.setValueAt(or.getRole(), i, 1);
        	model.setValueAt(or.getName(), i, 2);
        	model.setValueAt(or.getDeptno(), i, 3);
        	model.setValueAt(or.getSal(), i, 4);
        	model.setValueAt(or.getEmail(), i, 5);
        	model.setValueAt(or.isVacancy(), i, 6);
        	model.setValueAt(false, i, 7);
        	i++;
        	}
        
        JButton back = new JButton("MODIFY");
        back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i=0; i<table.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(table.getValueAt(i, 7).toString());
					int id = Integer.valueOf(table.getValueAt(i, 0).toString());
					if (checked) {
						modify(id,a);
					}
				}
			}
        	
        });
        back.setBounds(20,30,130,30);
        getContentPane().add(back);
        JButton add = new JButton("ADD");
        add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				 AddEMPFrame nextframe = new AddEMPFrame(0);
				 nextframe.setTitle("BOOK ERP");
	             nextframe.setVisible(true);
	             nextframe.setBounds(10, 10, 300, 600);        
	             nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             dispose();
			}
        	
        });
        add.setBounds(160,30,130,30);
        getContentPane().add(add);
        JButton delete = new JButton("DELETE");
        delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i=0; i<table.getRowCount(); i++) {
					Boolean checked = Boolean.valueOf(table.getValueAt(i, 7).toString());
					int id = Integer.valueOf(table.getValueAt(i, 0).toString());
					if (checked) {
						delete(id,a);
					}
				}
			}
        	
        });
        delete.setBounds(300,30,130,30);
        getContentPane().add(delete);
	}
	 public void modify(int id, List<Employee> a) {
		 for(Employee or: a) {
			 if(or.getId()==id) {
				 AddEMPFrame nextframe = new AddEMPFrame(id);
				 nextframe.setTitle("BOOK ERP");
				 nextframe.setink(or.getRole());
				 nextframe.setpaper(or.getName());
				 nextframe.setno(String.valueOf(or.getDeptno()));
				 nextframe.sets(String.valueOf(or.getSal()));
				 nextframe.sete(or.getEmail());
	             nextframe.setVisible(true);
	             nextframe.setBounds(10, 10, 300, 600);        
	             nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             dispose();
			 }
		 }
	 }
	 public void delete(int id, List<Employee> a) {
		 for(Employee or: a) {
			 if(or.getId()==id) {
				 try {
			    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
			    		Statement stmt = null;
		    			
			    		try {
			    			stmt = connection.createStatement();
			    			stmt.executeUpdate("DELETE FROM `pi`.`employee` WHERE id = "+ id +";");
			    			
			    		}
			    		catch (SQLException ex)
			    		{
			    			//JOptionPane.showMessageDialog(this, "Error connecting to the database");
			    		}
			    	} catch (SQLException h) {
			    		
			    		//JOptionPane.showMessageDialog(this, "Error connecting to the database");
			    	
			    			}
				 HRFrame nextframe = new HRFrame();
		        	nextframe.setVisible(true);
		            this.dispose();
			 }
		 }
	 }
	
}
