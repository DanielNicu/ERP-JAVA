package p3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class AddEMPFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	Container container = getContentPane();
    JLabel inkLabel = new JLabel("Role:");
    JLabel paperLabel = new JLabel("Name:");
    JLabel noLabel = new JLabel("Dept:");
    JLabel sLabel = new JLabel("Salary:");
    JLabel eLabel = new JLabel("Email:");
    JTextField inkText = new JTextField("");
    JTextField paperText = new JTextField("");
    JTextField noText = new JTextField("");
    JTextField sText = new JTextField("");
    JTextField eText = new JTextField("");
    JButton submit = new JButton("SUBMIT");
    JButton cancel = new JButton("CANCEL");
    int a;
    
    AddEMPFrame(int b) {
    	this.a=b;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
    	inkLabel.setBounds(50, 100, 100, 30);
    	paperLabel.setBounds(50, 140, 100, 30);
    	noLabel.setBounds(50, 190, 100, 30);
    	sLabel.setBounds(50, 230, 100, 30);
    	eLabel.setBounds(50, 270, 100, 30);
    	inkText.setBounds(150, 100, 100, 30);
    	paperText.setBounds(150, 140, 100, 30);
    	noText.setBounds(150, 190, 100, 30);
    	sText.setBounds(150, 230, 100, 30);
    	eText.setBounds(150, 270, 100, 30);
    	submit.setBounds(40,420,100,30);
    	cancel.setBounds(180,420,100,30);

    }
    public void addActionEvent() {
        submit.addActionListener(this);
        cancel.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
        	if(a==0) {
        		 try {
			    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
			    		Statement stmt = null;
		    			List<Employee> u = getEMP();
		    			int h = u.get(u.size()-1).getId()+1;
			    		try {
			    			stmt = connection.createStatement();
			    			stmt.executeUpdate("INSERT INTO `employee` values(" + h + ",\"" + inkText.getText()+"\",\""+paperText.getText()+"\","+noText.getText() +","+sText.getText()+",\""+eText.getText()+"\", 0)");
			    			
			    		}
			    		catch (SQLException ex)
			    		{
			    			//JOptionPane.showMessageDialog(this, "Error connecting to the database");
			    		}
			    	} catch (SQLException h) {
			    		
			    		//JOptionPane.showMessageDialog(this, "Error connecting to the database");
			    	
			    			}
        	}
        	else
        	{
        		try {
		    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
		    		Statement stmt = null;
	    			
		    		try {
		    			stmt = connection.createStatement();
		    			stmt.executeUpdate("UPDATE employee SET  role = \""+ inkText.getText() +"\", name = \""+ paperText.getText() +"\", deptno = \""+ noText.getText() +"\", salary = \""+ sText.getText() +"\", email = \""+ eText.getText() +"\" WHERE id = "+ String.valueOf(a) );
		    			
		    		}
		    		catch (SQLException ex)
		    		{
		    			//JOptionPane.showMessageDialog(this, "Error connecting to the database");
		    		}
		    	} catch (SQLException h) {
		    		
		    		//JOptionPane.showMessageDialog(this, "Error connecting to the database");
		    	
		    			}
        	}
        	HRFrame nextframe = new HRFrame();
        	nextframe.setVisible(true);
            this.dispose();    
            }

        
        if(e.getSource() == cancel) {
        	HRFrame nextframe = new HRFrame();
        	nextframe.setVisible(true); 
            this.dispose();
        }
}
    public void addComponentsToContainer() {
        container.add(inkLabel);
        container.add(paperLabel);
        container.add(inkText);
        container.add(paperText);
        container.add(noLabel);
        container.add(sLabel);
        container.add(noText);
        container.add(sText);
        container.add(eLabel);
        container.add(eText);
        container.add(submit);
        container.add(cancel);
    }

    public void setink(String user) {this.inkText.setText(user);}
    public void setpaper(String user) {this.paperText.setText(user);}
    public void setno(String user) {this.noText.setText(user);}
    public void sets(String user) {this.sText.setText(user);}
    public void sete(String user) {this.eText.setText(user);}
    public List<Employee> getEMP(){
    	List<Employee> a = new ArrayList<Employee>(); 
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
    	return a;

    }

    
}
