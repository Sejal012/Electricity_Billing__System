package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;


public class signup extends JFrame implements ActionListener
{
    Choice loginas;
    JButton create,back;
    JTextField tmeterno,tusername,tname,tpass;
    signup()
    {
     getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(null);
       JLabel signupas =new JLabel("Create Account as");
       signupas.setBounds(30,20,150,50);
       add(signupas);
        loginas=new Choice();
        loginas.add("Admin");
        loginas.add("Customer");
        loginas.setBounds(180, 30,150,25);
        add(loginas);



        JLabel jmeterno=new JLabel("Meter Numder");
        jmeterno.setBounds(30,70,150,50);
        jmeterno.setVisible(false);
        add(jmeterno);

        tmeterno =new JTextField();
        tmeterno.setBounds(180,80,150,25);
        tmeterno.setVisible(false);
        add(tmeterno);





        JLabel jusername =new JLabel("User Name :") ;
        jusername.setBounds(30,110,300,40);
        add(jusername);
         tusername =new JTextField();
        tusername.setBounds(180,120,150,25);
        add(tusername);
        JLabel jname =new JLabel("Name :") ;
        jname.setBounds(30,150,300,40);
        add(jname);
         tname =new JTextField();
        tname.setBounds(180,160,150,25);
        add(tname);

        tmeterno.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Connect c=new Connect();
                    ResultSet res=c.s.executeQuery("select *from login where meter_no ='"+tmeterno.getText()+"'");
                    while(res.next()){
                        tname.setText(res.getString("name"));
                    }
                }
                catch(Exception a)
                {

                }
            }
        });

        JLabel jpass =new JLabel("Password :") ;
        jpass.setBounds(30,190,300,40);
        add(jpass);
         tpass =new JTextField();
        tpass.setBounds(180,200,150,25);
        add(tpass);


        loginas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user=loginas.getSelectedItem();
                if(user.equals("Admin"))
                {

                    jmeterno.setVisible(false);
                    tmeterno.setVisible(false);
                    tname.setEditable(true);

                }
                else if( user.equals("Customer"))
                {

                    jmeterno.setVisible(true);
                    tmeterno.setVisible(true);
                    tname.setEditable(false);
                }
            }
        });
        create =new JButton("Create");
        create.setBackground(new Color(10, 100, 213));
        create.setBounds(40,250,100,30);
        create.addActionListener(this);
        add(create);
        back =new JButton("Back");
        back.setBackground(new Color(10, 100, 213));
        back.setBounds(200,250,100,30);
        back.addActionListener(this);
        add(back);

       /*ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("user-login-3051.svg"));
        JLabel img=new JLabel(i);
        img.setBounds(300,20,170,170);
        add(img);*/

        setSize(600,370);
        setLocation(500,200);
        setVisible(true);
        setTitle("Signup Page");

    }


   public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==create)
        {
            String accounttype=loginas.getSelectedItem();
            String username=tusername.getText();
            String name=tname.getText();
            String passward=tpass.getText();
            String meterno=tmeterno.getText();
            try {
                Connect c=new Connect();
                String query=null;
                if(loginas.equals("Admin"))
                       query= "insert into login values ('"+meterno+"','"+username+"','"+name+"','"+passward+"','"+accounttype+"')";
                else{
                    query="update login set username='"+username+"',password='"+passward+"', usertype='"+accounttype+"'where meter_no='"+meterno+"'";
                }
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account Created Successfully");
                setVisible(false);
                new login();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==back)
        {
            setVisible(false);
            new login();
        }
    }
    public static void main(String []args)
    {

        new signup();
    }
}
