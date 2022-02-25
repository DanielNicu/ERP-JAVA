package p3;

import javax.swing.JFrame;


public class main {
    public static void main(String[] a) {
        lg();
        

    }
    public static void lg()
    {
    	LoginFrame frame = new LoginFrame();
        frame.setTitle("BOOK ERP");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
