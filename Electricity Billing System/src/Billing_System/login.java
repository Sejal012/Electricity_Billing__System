package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener
{
    JButton jlogin,jsignup;
    JTextField tusername,tpass;
    JLabel jloginas;
    Choice loginas;
    login()
    {
        setLayout(null);
       JLabel jusername =new JLabel("User Name :") ;
       jusername.setBounds(200,20,300,40);
       add(jusername);
       tusername =new JTextField();
       tusername.setBounds(290,30,150,25);
       add(tusername);
        JLabel jpass =new JLabel("Password :") ;
        jpass.setBounds(200,60,300,40);
        add(jpass);
         tpass =new JTextField();
        tpass.setBounds(290,70,150,25);
        add(tpass);
         jloginas =new JLabel("Log in as :") ;
        jloginas.setBounds(200,100,70,40);
        add(jloginas);

        loginas=new Choice();
        loginas.add("Admin");
        loginas.add("Customer");
        loginas.setBounds(290, 110,150,25);
        add(loginas);

        jlogin =new JButton("log in");
        jlogin.setBounds(200,170,100,30);
        jlogin.addActionListener(this);
        add(jlogin);
        jsignup =new JButton("sign up");
        jsignup.setBounds(350,170,100,30);
        jsignup.addActionListener(this);
        add(jsignup);
        ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("PngItem_248235(1).png"));
        JLabel img=new JLabel(i);
        img.setBounds(20,20,170,170);
        add(img);

        setSize(500,250);
      setLocation(400,150);
      setTitle("Login page");
      setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==jsignup)
        {
            setVisible(false);
            new signup();
        }
        else if(ae.getSource()==jlogin)
        {
            String username=tusername.getText();
            String passward=tpass.getText();
            String login=loginas.getSelectedItem();
            try {
                Connect c=new Connect();
                String query="select * from login where username='"+username+"' and password='"+passward+"' and usertype='"+login+"'";
                ResultSet rs= c.s.executeQuery(query);
                if(rs.next())
                {
                    String meter=rs.getString("meter_no");
                    setVisible(false);
                    new Project(login,meter);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Invalid login");
                        setVisible(false);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[]args)
{
    new login();
}
}
