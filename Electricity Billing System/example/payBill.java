package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.*;

public class payBill extends JFrame implements ActionListener {
    String meter;
    Choice cmonth;
    JButton pay,back;
    payBill(String meter){
        this.meter=meter;
        setSize(700,550);
        setLocation(300,50);
        setLayout(null);
        JLabel heading=new JLabel("Pay Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);

        JLabel meterno=new JLabel("Meter Number");
        meterno.setBounds(35,80,200,20);
        add(meterno);

        JLabel jmeterno=new JLabel("");
        jmeterno.setBounds(300,80,200,20);
        add(jmeterno);

        JLabel name=new JLabel("Name");
        name.setBounds(35,140,200,20);
        add(name);

        JLabel jname=new JLabel("");
        jname.setBounds(300,140,200,20);
        add(jname);

        JLabel month=new JLabel("Month");
        month.setBounds(35,200,200,20);
        add(month);

        cmonth=new Choice();
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        cmonth.setBounds(300,200,200,20);
        add(cmonth);

        JLabel unit=new JLabel("Unit");
        unit.setBounds(35,260,200,20);
        add(unit);

        JLabel junit=new JLabel("");
        junit.setBounds(300,260,200,20);
        add(junit);


        JLabel totalbill=new JLabel("Total Bill ");
        totalbill.setBounds(35,320,200,20);
        add(totalbill);

        JLabel jtotalbill=new JLabel("");
        jtotalbill.setBounds(300,320,200,20);
        add(jtotalbill);

        JLabel status=new JLabel("Status");
        status.setBounds(35,380,200,20);
        add(status);

        JLabel jstatus=new JLabel("");
        jstatus.setBounds(300,380,200,20);
        add(jstatus);

         try{
             Connect c=new Connect();
             ResultSet r= c.s.executeQuery("select * from customer where meter_no='"+meter+"'");
              while(r.next()){
                  jmeterno.setText(meter);
                  jname.setText(r.getString("name"));
              }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }

        cmonth.addItemListener((new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Connect c=new Connect();
                try{
                  ResultSet rs=c.s.executeQuery("select * from totalbill where meter_no ='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");
                while (rs.next()){
                    junit.setText(rs.getString("unit"));
                    jtotalbill.setText(rs.getString("totalbill"));
                    jstatus.setText(rs.getString("status"));
                }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }));


         pay=new JButton("Pay");
         pay.setBackground(Color.BLACK);
         pay.setForeground(Color.WHITE);
         pay.setBounds(200,460,100,25);
         pay.addActionListener(this);
         add(pay);

        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(400,460,100,25);
        back.addActionListener(this);
        add(back);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
       if(e.getSource()==pay){
           try{
               Connect c=new Connect();
               c.s.executeUpdate("update totalbill set status ='Paid' where meter_no='"+meter+"' and month='"+cmonth.getSelectedItem()+"'");
           }
           catch(Exception E){
              E.printStackTrace();
           }
           setVisible(false);
           new Payment_Bill(meter);
       }
       else{
           setVisible(false);

       }
    }
    public static void main(String[] args) {
      new payBill("");
    }
}
