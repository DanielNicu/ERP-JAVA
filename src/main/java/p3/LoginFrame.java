package p3;

import javax.swing.*;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFrame extends JFrame implements ActionListener , KeyListener{
	private static final long serialVersionUID = 1L;
	Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    int c;

    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (verif(userText,pwdText)!=0) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                List<Order> or = createOrder();
                List<Dept> de = createDept();
            	Storehouse b = getStorehouse();
                for (Order ord: or) {
                	if (ord.getDept()==0) {
                		Dept dept = getmindept(de);
                		ord = updateord(ord,dept);
                		de = updatedept(dept,de);
                		b = updatestorehouse(b,ord);
                		de = createDept();
                	}
                }
                if(verif(userText,pwdText)==1) {
                    StorehouseFrame nextframe = new StorehouseFrame();
                    nextframe.setink(String.valueOf(b.getInk()));
                    nextframe.setpaper(String.valueOf(b.getPaper()));
                    nextframe.setglue(String.valueOf(b.getGlue()));
                    nextframe.setcover(String.valueOf(b.getCover()));
                    nextframe.setmax_ink(String.valueOf(b.getMax_ink()));
                    nextframe.setmax_paper(String.valueOf(b.getMax_paper()));
                    nextframe.setmax_glue(String.valueOf(b.getMax_glue()));
                    nextframe.setmax_cover(String.valueOf(b.getMax_cover()));
                    nextframe.setTitle("BOOK ERP");
                    nextframe.setVisible(true);
                    nextframe.setBounds(10, 10, 800, 900);        
                    nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    this.dispose();
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
                }
                if(verif(userText,pwdText)==2) {
                	WorkerFrame1 nextframe = new WorkerFrame1();
                	nextframe.setVisible(true);
                    this.dispose();
                }
                if(verif(userText,pwdText)==3) {
                	HRFrame nextframe = new HRFrame();
                	nextframe.setVisible(true);
                    this.dispose();
                }
                if(verif(userText,pwdText)==4) {
                	List<Employee> a = getEMP();
                	int i = 0;
                	for(Employee o: a) {
                		i=i+o.getSal();
                	}
                	HistogramPanel panel = new HistogramPanel();
                    panel.addHistogramColumn("Salaryes", i, Color.RED);
                    List<Order> c = createOrder();
                	i = 0;
                	for(Order o: c) {
                		i=i+o.getQuantity()*10;
                	}
                    panel.addHistogramColumn("Sellings", i, Color.YELLOW);
                    i=i/2;
                    panel.addHistogramColumn("Raw Material", i, Color.BLUE);
                    panel.layoutHistogram();

                    JFrame frame = new JFrame("Histogram Panel");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add( panel );
                    frame.setLocationByPlatform( true );
                    frame.pack();
                    frame.setVisible( true );
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
            

        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }


    public int verif(String user, String pass)
    {
    	int c=0;
    	List<Admin> a = new ArrayList<Admin>();
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		ResultSet rs = null;
    		try {
    			stmt = connection.createStatement();
    			rs =stmt.executeQuery("Select loginid, password, admin_type, aname, email from Admin ");
    			while (rs.next()) {
    				a.add(new Admin(rs.getString("loginid"),rs.getString("password"),rs.getInt("admin_type"),rs.getString("aname"),rs.getString("email")));
 
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
    	for (Admin ad : a) {
    		if (ad.verifyLogin(user, pass) != 0 ) {
    			c = ad.verifyLogin(user,pass);
    		}
    	}
    	return c;
    }

    public int getc() {
    	return this.c;
    }
    public JPasswordField getpass() {
    	return this.passwordField;
    }
    public JTextField getusr() {
    	return this.userTextField;
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
    public static Dept getmindept(List<Dept> a) {
    	int min=9999;
    	int i=-1;
    	int j=0;
    	for(Dept dept: a) {
    		i=i+1;
    		if(dept.getOrdersnf()<min && (dept.getDeptno()== 10 || dept.getDeptno() == 20))
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
    public Storehouse updatestorehouse(Storehouse s, Order o) {
    	s.setInk(s.getInk()-((o.getInk()*o.getQuantity())/10));
    	s.setPaper(s.getPaper()-((o.getPaper()*o.getQuantity())/10));
    	s.setGlue(s.getGlue()-((o.getGlue()*o.getQuantity())/10));
    	s.setCover(s.getCover()-((o.getCover()*o.getQuantity())/10));
    	try {
    		Connection	 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pi", "root", "Dani1234@");
    		Statement stmt = null;
    		try {
    			stmt = connection.createStatement();
    			stmt.executeUpdate("UPDATE storehouse SET ink = "+ String.valueOf(s.getInk()) +", paper = "+ String.valueOf(s.getPaper()) + ", glue = "+ String.valueOf(s.getGlue()) +", cover =  "+ String.valueOf(s.getCover()));
    			
    		}
    		catch (SQLException ex)
    		{
    			//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    		}
    	} catch (SQLException e) {
    		
    		//JOptionPane.showMessageDialog(this, "Error connecting to the database");
    	
    			}
    	return s;
    }
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
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {                                
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
                {
        	if (evt.getSource() == loginButton) {
                String userText;
                String pwdText;
                userText = userTextField.getText();
                pwdText = passwordField.getText();
                if (verif(userText,pwdText)!=0) {
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    List<Order> or = createOrder();
                    List<Dept> de = createDept();
                	Storehouse b = getStorehouse();
                    for (Order ord: or) {
                    	if (ord.getDept()==0) {
                    		Dept dept = getmindept(de);
                    		ord = updateord(ord,dept);
                    		de = updatedept(dept,de);
                    		b = updatestorehouse(b,ord);
                    		de = createDept();
                    	}
                    }
                    if(verif(userText,pwdText)==1) {
                        StorehouseFrame nextframe = new StorehouseFrame();
                        nextframe.setink(String.valueOf(b.getInk()));
                        nextframe.setpaper(String.valueOf(b.getPaper()));
                        nextframe.setglue(String.valueOf(b.getGlue()));
                        nextframe.setcover(String.valueOf(b.getCover()));
                        nextframe.setmax_ink(String.valueOf(b.getMax_ink()));
                        nextframe.setmax_paper(String.valueOf(b.getMax_paper()));
                        nextframe.setmax_glue(String.valueOf(b.getMax_glue()));
                        nextframe.setmax_cover(String.valueOf(b.getMax_cover()));
                        nextframe.setTitle("BOOK ERP");
                        nextframe.setVisible(true);
                        nextframe.setBounds(10, 10, 800, 900);        
                        nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        this.dispose();
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
                    }
                    if(verif(userText,pwdText)==2) {
                    	WorkerFrame1 nextframe = new WorkerFrame1();
                    	nextframe.setVisible(true);
                        this.dispose();
                    }
                    if(verif(userText,pwdText)==3) {
                    	HRFrame nextframe = new HRFrame();
                    	nextframe.setVisible(true);
                        this.dispose();
                    }
                    if(verif(userText,pwdText)==4) {
                    	List<Employee> a = getEMP();
                    	int i = 0;
                    	for(Employee o: a) {
                    		i=i+o.getSal();
                    	}
                    	HistogramPanel panel = new HistogramPanel();
                        panel.addHistogramColumn("Salaryes", i, Color.RED);
                        List<Order> c = createOrder();
                    	i = 0;
                    	for(Order o: c) {
                    		i=i+o.getQuantity()*10;
                    	}
                        panel.addHistogramColumn("Sellings", i, Color.YELLOW);
                        i=i/2;
                        panel.addHistogramColumn("Raw Material", i, Color.BLUE);
                        panel.layoutHistogram();

                        JFrame frame = new JFrame("Histogram Panel");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add( panel );
                        frame.setLocationByPlatform( true );
                        frame.pack();
                        frame.setVisible( true );
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }
                

            }
                }
    }

	public void keyTyped(KeyEvent e) {
		 if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
         {
 	
         String userText;
         String pwdText;
         userText = userTextField.getText();
         pwdText = passwordField.getText();
         if (verif(userText,pwdText)!=0) {
             JOptionPane.showMessageDialog(this, "Login Successful");
             List<Order> or = createOrder();
             List<Dept> de = createDept();
         	Storehouse b = getStorehouse();
             for (Order ord: or) {
             	if (ord.getDept()==0) {
             		Dept dept = getmindept(de);
             		ord = updateord(ord,dept);
             		de = updatedept(dept,de);
             		b = updatestorehouse(b,ord);
             		de = createDept();
             	}
             }
             if(verif(userText,pwdText)==1) {
                 StorehouseFrame nextframe = new StorehouseFrame();
                 nextframe.setink(String.valueOf(b.getInk()));
                 nextframe.setpaper(String.valueOf(b.getPaper()));
                 nextframe.setglue(String.valueOf(b.getGlue()));
                 nextframe.setcover(String.valueOf(b.getCover()));
                 nextframe.setmax_ink(String.valueOf(b.getMax_ink()));
                 nextframe.setmax_paper(String.valueOf(b.getMax_paper()));
                 nextframe.setmax_glue(String.valueOf(b.getMax_glue()));
                 nextframe.setmax_cover(String.valueOf(b.getMax_cover()));
                 nextframe.setTitle("BOOK ERP");
                 nextframe.setVisible(true);
                 nextframe.setBounds(10, 10, 800, 900);        
                 nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 this.dispose();
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
             }
             if(verif(userText,pwdText)==2) {
             	WorkerFrame1 nextframe = new WorkerFrame1();
             	nextframe.setVisible(true);
                 this.dispose();
             }
             if(verif(userText,pwdText)==3) {
             	HRFrame nextframe = new HRFrame();
             	nextframe.setVisible(true);
                 this.dispose();
             }
             if(verif(userText,pwdText)==4) {
             	List<Employee> a = getEMP();
             	int i = 0;
             	for(Employee o: a) {
             		i=i+o.getSal();
             	}
             	HistogramPanel panel = new HistogramPanel();
                 panel.addHistogramColumn("Salaryes", i, Color.RED);
                 List<Order> c = createOrder();
             	i = 0;
             	for(Order o: c) {
             		i=i+o.getQuantity()*10;
             	}
                 panel.addHistogramColumn("Sellings", i, Color.YELLOW);
                 i=i/2;
                 panel.addHistogramColumn("Raw Material", i, Color.BLUE);
                 panel.layoutHistogram();

                 JFrame frame = new JFrame("Histogram Panel");
                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 frame.add( panel );
                 frame.setLocationByPlatform( true );
                 frame.pack();
                 frame.setVisible( true );
                 this.dispose();
             }
         } else {
             JOptionPane.showMessageDialog(this, "Invalid Username or Password");
         }
         

     }
         }
		
	

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}   
}


