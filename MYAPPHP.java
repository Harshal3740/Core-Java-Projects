
//CURD Application in java

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class MYAPPHP extends JFrame {

    MYAPPHP(){

        JTextField tf1 = new JTextField(15);
        JTextField tf2 = new JTextField(15);
        JTextField tf3 = new JTextField(15);
        JTextField tf4 = new JTextField(15);


        JButton insert = new JButton("Insert Record");
        JButton delete = new JButton("Delete Record");
        JButton modify = new JButton("Modify Record");
        JButton search = new JButton("Search Record");

        JTextArea ta =new JTextArea();

        Connection conn;
        Statement stat;

        JTabbedPane tp =new JTabbedPane();
        add(tp);

        JPanel p1 =new JPanel();
        p1.setLayout(new BorderLayout());

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 2, 10, 50));


        center.add(new JLabel("EMP_ID",JLabel.RIGHT));
        center.add(tf1);
        center.add(new JLabel("EMP_NAME",JLabel.RIGHT));
        center.add(tf2);
        center.add(new JLabel("EMP_SALARY",JLabel.RIGHT));
        center.add(tf3);
        center.add(new JLabel("EMP_DEPT",JLabel.RIGHT));
        center.add(tf4);

        p1.add(center,"Center");

        JPanel south =new JPanel();
        south.add(insert);
        south.add(delete);
        south.add(modify);
        south.add(search);
        add(south,"South");

        tp.addTab("RECORD MANAGEMENT", p1);


        JPanel p3 =new JPanel();
        p3.setLayout(new BorderLayout(100, 100));
        JScrollPane sp =new JScrollPane(ta);
        p3.add(sp);
        ta.setEditable(false);
        ta.setFont(new Font("Times New Roman",Font.PLAIN,20));
        tp.addTab("Display Records", p3);




        // Database connection details
        String url = "jdbc:mysql://localhost:3306/harshal"; // Change to your database name
        String user = "username "; // Change to your MySQL username
        String password = "passs@"; // Change to your MySQL password


        try{
                 // 1. Load the MySQL JDBC Driver
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 System.out.println("Driver Registered Successfully!");
     
                 // 2. Establish Connection
                 Connection con = DriverManager.getConnection(url, user, password);
                 System.out.println("Connected to the database.");
     
                 // 3. Create Statement
                 Statement stmt = con.createStatement();
        }
        catch(Exception e ){
            System.out.println(e);
        }
       
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String sql ="insert into employee values("+tf1.getText()+"',"+tf2.getText()+"'',"+tf3.getText()+",'"+tf4.getText()+"');";
            }
        });





        setBounds(100, 100, 800, 600);
        setTitle("MYAPP");
    }
    public static void main(String[] args) {
        MYAPPHP f =new MYAPPHP();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
}
