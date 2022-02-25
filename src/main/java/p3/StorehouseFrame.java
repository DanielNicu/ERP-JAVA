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

public class StorehouseFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	Container container = getContentPane();
    JLabel inkLabel = new JLabel("ink level:");
    JLabel paperLabel = new JLabel("paper level:");
    JLabel glueLabel = new JLabel("glue level:");
    JLabel coverLabel = new JLabel("cover level:");
    JLabel max_inkLabel = new JLabel("max ink level:");
    JLabel max_paperLabel = new JLabel("max paper level:");
    JLabel max_glueLabel = new JLabel("max glue level:");
    JLabel max_coverLabel = new JLabel("max cover level:");
    JTextField inkText = new JTextField("ayayaya");
    JTextField paperText = new JTextField("ayayaya");
    JTextField glueText = new JTextField("ayayaya");
    JTextField coverText = new JTextField("ayayaya");
    JTextField max_inkText = new JTextField("ayayaya");
    JTextField max_paperText = new JTextField("ayayaya");
    JTextField max_glueText = new JTextField("ayayaya");
    JTextField max_coverText = new JTextField("ayayaya"); 
    JButton addDel = new JButton("MODIFY STOCK");
    JButton show = new JButton("SHOW PROVIDERS LIST");
    
    StorehouseFrame() {
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
    	max_inkLabel.setBounds(350, 150, 150, 30);
    	max_paperLabel.setBounds(350, 220, 150, 30);
    	glueLabel.setBounds(50, 290, 100, 30);
    	coverLabel.setBounds(50, 360, 100, 30);
    	max_glueLabel.setBounds(350, 290, 100, 30);
    	max_coverLabel.setBounds(350, 360, 100, 30);
    	inkText.setBounds(150, 150, 100, 30);
    	paperText.setBounds(150, 220, 100, 30);
    	max_inkText.setBounds(550, 150, 100, 30);
    	max_paperText.setBounds(550, 220, 100, 30);
    	glueText.setBounds(150, 290, 100, 30);
    	coverText.setBounds(150, 360, 100, 30);
    	max_glueText.setBounds(550, 290, 100, 30);
    	max_coverText.setBounds(550, 360, 100, 30);
    	addDel.setBounds(100,500,150,30);
    	show.setBounds(400,500,200,30);
    	inkText.setEditable(false);
    	paperText.setEditable(false);
    	max_inkText.setEditable(false);
    	max_paperText.setEditable(false);
    	glueText.setEditable(false);
    	coverText.setEditable(false);
    	max_glueText.setEditable(false);
    	max_coverText.setEditable(false);
    }
    public void addActionEvent() {
        addDel.addActionListener(this);
        show.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDel) {
        	 AddDeliveryFrame nextframe = new AddDeliveryFrame();
             nextframe.setink(String.valueOf(this.getink()));
             nextframe.setpaper(String.valueOf(this.getpaper()));
             nextframe.setglue(String.valueOf(this.getglue()));
             nextframe.setcover(String.valueOf(this.getcover()));
             nextframe.ssetink(String.valueOf(this.getink()));
             nextframe.ssetpaper(String.valueOf(this.getpaper()));
             nextframe.ssetglue(String.valueOf(this.getglue()));
             nextframe.ssetcover(String.valueOf(this.getcover()));
             nextframe.setmax_ink(String.valueOf(this.getmax_ink()));
             nextframe.setmax_paper(String.valueOf(this.getmax_paper()));
             nextframe.setmax_glue(String.valueOf(this.getmax_glue()));
             nextframe.setmax_cover(String.valueOf(this.getmax_cover()));
             nextframe.setTitle("BOOK ERP");
             nextframe.setVisible(true);
             nextframe.setBounds(10, 10, 400, 700);        
             nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             this.dispose();
        }
        if(e.getSource()== show) {
        	ProviderFrame nextframe = new ProviderFrame();
            nextframe.setink(String.valueOf(this.getink()));
            nextframe.setpaper(String.valueOf(this.getpaper()));
            nextframe.setglue(String.valueOf(this.getglue()));
            nextframe.setcover(String.valueOf(this.getcover()));
            nextframe.setmax_ink(String.valueOf(this.getmax_ink()));
            nextframe.setmax_paper(String.valueOf(this.getmax_paper()));
            nextframe.setmax_glue(String.valueOf(this.getmax_glue()));
            nextframe.setmax_cover(String.valueOf(this.getmax_cover()));    
            nextframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
        }
    }
    
    public void addComponentsToContainer() {
        container.add(inkLabel);
        container.add(paperLabel);
        container.add(max_inkLabel);
        container.add(max_paperLabel);
        container.add(glueLabel);
        container.add(coverLabel);
        container.add(max_glueLabel);
        container.add(max_coverLabel);
        container.add(inkText);
        container.add(paperText);
        container.add(max_inkText);
        container.add(max_paperText);
        container.add(glueText);
        container.add(coverText);
        container.add(max_glueText);
        container.add(max_coverText);
        container.add(addDel);
        container.add(show);
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
    public void setred1() {inkText.setForeground(Color.RED);}
    public void setred2() {paperText.setForeground(Color.RED);}
    public void setred3() {glueText.setForeground(Color.RED);}
    public void setred4() {coverText.setForeground(Color.RED);}
}
