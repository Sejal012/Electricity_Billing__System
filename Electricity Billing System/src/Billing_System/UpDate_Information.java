package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpDate_Information extends JFrame implements ActionListener {

    JTextField taddress,tcity,tstate,temail,tphone;
    JButton udate,cancel;
    String meter;
    JLabel jname;
    UpDate_Information(String meter)
    {
        this.meter=meter;
       setBounds(300,150,850,450);
       setLayout(null);

        JLabel heading =new JLabel("Update Customer Information");
        heading.setBounds(150,5,500,40);
        heading.setFont(new Font("Tahona",Font.PLAIN,20) );
        add(heading);

        JLabel name =new JLabel("Name");
        name.setBounds(50,70,100,40);
        add(name);

         jname =new JLabel("");
        jname.setBounds(200,70,100,40);
        add(jname);

        JLabel meterno =new JLabel("Meter No");
        meterno.setBounds(50,110,100,40);
        add(meterno);

        JLabel jmeterno =new JLabel("");
        jmeterno.setBounds(200,110,100,40);
        add(jmeterno);

        JLabel address =new JLabel("Address");
        address.setBounds(50,150,100,40);
        add(address);

         taddress =new JTextField();
        taddress.setBounds(200,160,200,20);
        add(taddress);
        JLabel city =new JLabel("City");
        city.setBounds(50,190,100,40);
        add(city);

         tcity =new JTextField();
        tcity.setBounds(200,200,200,20);
        add(tcity);
        JLabel state =new JLabel("State");
        state.setBounds(50,230,100,40);
        add(state);

         tstate =new JTextField();
        tstate.setBounds(200,240,200,20);
        add(tstate);

        JLabel email =new JLabel("Email");
        email.setBounds(50,270,200,40);
        add(email);

         temail =new JTextField();
        temail.setBounds(200,280,200,20);
        add(temail);

        JLabel phone =new JLabel("Phone No.");
        phone.setBounds(50,310,100,40);
        add(phone);

        tphone =new JTextField();
        tphone.setBounds(200,320,200,20);
        add(tphone);


        try{
            Connect c=new Connect();
            ResultSet res=c.s.executeQuery("select * from customer where meter_no='"+meter+"'");
            while(res.next())
            {
                name.setText(res.getString(("name")));
                taddress.setText(res.getString("address"));
                tcity.setText(res.getString("city"));
                tstate.setText(res.getString("state"));
                temail.setText(res.getString("email"));
                tphone.setText(res.getString("phone"));
                jmeterno.setText(res.getString("meter_no"));


            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        cancel = new JButton("cancel");
        cancel.setBounds(70, 370, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        udate = new JButton("Update");
        udate.setBounds(250, 370, 100, 25);
        udate.setBackground(Color.BLACK);
        udate.setForeground(Color.WHITE);
        udate.addActionListener(this);
        add(udate);
       setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==cancel)
        {
            setVisible(false);
        }
        else if(ae.getSource()==udate)
        {
            String name=jname.getText();
            String laddress=taddress.getText();
            String lcity=tcity.getText();
            String lstate=tstate.getText();
            String lemail=temail.getText();
            String lphone=tphone.getText();
     try{
         Connect c=new Connect();
         c.s.executeUpdate("update customer set address='"+laddress+"',city='"+lcity+"',state='"+lstate+"',email='"+lemail+"',phone='"+lphone+"'");
         JOptionPane.showMessageDialog(null,"User Information updated successfully");
     }
     catch(Exception a)
     {

     }

        }
    }

    public static void main(String[] args) {
        new UpDate_Information("");
    }
}
