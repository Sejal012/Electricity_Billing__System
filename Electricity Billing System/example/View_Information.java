package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class View_Information extends JFrame implements ActionListener {
  String meter;
  JButton cancel;
    View_Information(String meter){
      this.meter=meter;
        setBounds(300,70,750,570);
      getContentPane().setBackground(new Color(118, 120, 225, 221));
      setLayout(null);

      JLabel heading =new JLabel("View Customer Information");
      heading.setBounds(250,5,500,40);
      heading.setFont(new Font("Tahona",Font.PLAIN,20) );
      add(heading);

        JLabel name =new JLabel("Name");
        name.setBounds(50,80,100,40);
        add(name);

        JLabel jname =new JLabel("");
        jname.setBounds(200,80,100,40);
        add(jname);

        JLabel meterno =new JLabel("Meter No");
        meterno.setBounds(50,140,100,40);
        add(meterno);

        JLabel jmeterno =new JLabel("");
        jmeterno.setBounds(200,140,100,40);
        add(jmeterno);

        JLabel address =new JLabel("Address");
        address.setBounds(50,200,100,40);
        add(address);

        JLabel jaddress =new JLabel("");
        jaddress.setBounds(200,200,100,40);
        add(jaddress);
        JLabel city =new JLabel("City");
        city.setBounds(50,260,100,40);
        add(city);

        JLabel jcity =new JLabel("");
        jcity.setBounds(200,260,100,40);
        add(jcity);
        JLabel state =new JLabel("State");
        state.setBounds(400,80,100,40);
        add(state);

        JLabel jstate =new JLabel("");
        jstate.setBounds(550,80,100,40);
        add(jstate);

        JLabel email =new JLabel("Email");
        email.setBounds(400,140,100,40);
        add(email);

        JLabel jemail =new JLabel("");
        jemail.setBounds(550,140,100,40);
        add(jemail);

        JLabel phone =new JLabel("Phone No.");
        phone.setBounds(400,200,100,40);
        add(phone);

        JLabel jphone =new JLabel("");
        jphone.setBounds(550,200,100,40);
        add(jphone);

/*
        try{
          Connect c= new Connect();
          ResultSet res=c.s.executeQuery("select * from customer where meter_no='"+meter+"'");
          jname.setText((res.getString("name")));
          jaddress.setText((res.getString("address")));
          jcity.setText((res.getString("city")));
          jemail.setText((res.getString("email")));
          jphone.setText((res.getString("phone")));
          jstate.setText((res.getString("state")));
          jmeterno.setText((res.getString("meter_no")));


        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
*/
      try{
        Connect c=new Connect();
        ResultSet res=c.s.executeQuery("select * from customer where meter_no='"+meter+"'");
        while(res.next())
        {
          jname.setText(res.getString(("name")));
          jaddress.setText(res.getString("address"));
          jcity.setText(res.getString("city"));
          jstate.setText(res.getString("state"));
          jemail.setText(res.getString("email"));
          jphone.setText(res.getString("phone"));
          jmeterno.setText(res.getString("meter_no"));


        }
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }

         cancel = new JButton("cancel");
        cancel.setBounds(320, 320, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
       cancel.addActionListener(this);
        add(cancel);


        setVisible(true);
    }
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==cancel)
    {
      setVisible(false);
    }
  }
    public static void main(String[] args) {
        new View_Information("");
    }
}
