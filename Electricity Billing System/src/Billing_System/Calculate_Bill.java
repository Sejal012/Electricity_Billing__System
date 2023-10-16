package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class Calculate_Bill extends JFrame implements ActionListener {

    Choice meterno,cmonth;
    JLabel address,jname;
    JTextField unitconsumed;
    JButton next,cancel;
    Calculate_Bill()
    {
        setLocation(400,50);
        setSize(600,460);
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading =new JLabel("Calculate Electricity Bill");
        heading.setBounds(110,10,400,30);
        heading.setFont(new Font("Tahona",Font.PLAIN,24));
        p.add(heading);

        JLabel jmeterno =new JLabel("Meter Number");
        jmeterno.setBounds(50,100,100,20);
        p.add(jmeterno);

        meterno=new Choice();

        try {
            Connect c=new Connect();
            ResultSet res=c.s.executeQuery("select * from customer");
            while(res.next())
            {
               meterno.add(res.getString("meter_no"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        meterno.setBounds(170,100,200,20);
        p.add(meterno);


        JLabel cname =new JLabel("Name");
        cname.setBounds(50,140,100,20);
        p.add(cname);

        jname =new JLabel();
        jname.setBounds(170,140,100,20);
        p.add(jname);

        JLabel jaddress =new JLabel("Address");
        jaddress.setBounds(50,180,100,20);
        p.add(jaddress);

        address =new JLabel();
        address.setBounds(170,180,200,20);
        p.add(address);

        meterno.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Connect c=new Connect();
                    ResultSet res=c.s.executeQuery("select * from customer where meter_no='"+meterno.getSelectedItem()+"'");
                    while(res.next())
                    {
                        jname.setText(res.getString("name"));
                        address.setText(res.getString("address"));
                    }
                }
                catch(Exception E)
                {
                    E.printStackTrace();
                }
            }
        });
        JLabel uConsumed =new JLabel("Unit Consumed");
        uConsumed.setBounds(50,220,100,20);
        p.add(uConsumed);

        unitconsumed=new JTextField();
        unitconsumed.setBounds(170,220,200,20);
        p.add(unitconsumed);

        JLabel mon=new JLabel("Month");
        mon.setBounds(50,260,100,20);
        p.add(mon);

        cmonth =new Choice();
        cmonth.setBounds(170,260,200,20);
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
        p.add(cmonth);



        next =new JButton("Submit");
        next.setBounds(70,320,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        cancel =new JButton("Cancel");
        cancel.setBounds(210,320,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        add(p,"Center");
        ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("businessman-showing-calculator.png"));
        Image i1=i.getImage().getScaledInstance(180,280,Image.SCALE_DEFAULT);
        ImageIcon i2=new ImageIcon(i1);
        JLabel img=new JLabel(i2);
        add(img,"West");
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {

        String meternumber=meterno.getSelectedItem();
        String unit=unitconsumed.getText();
        String month=cmonth.getSelectedItem();

        int totalbill=0;
        int unit_consumed =Integer.parseInt(unit);
        String query="select * from tax";
        try {
            Connect c=new Connect();
            ResultSet res=c.s.executeQuery(query);
            // c.s.executeUpdate(query2);
            while(res.next()) {
              totalbill +=  unit_consumed*Integer.parseInt(res.getString("cost_per_unit"));
              totalbill +=Integer.parseInt(res.getString("meter_rent"));
                totalbill +=Integer.parseInt(res.getString("service_charge"));
                totalbill +=Integer.parseInt(res.getString("service_tax"));
                totalbill +=Integer.parseInt(res.getString("fixed_tax"));
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String query1="insert into totalbill values('"+meternumber+"','"+month+"','"+unit+"','"+totalbill+"','not paid')";
        try {
            Connect c=new Connect();
            c.s.executeUpdate(query1);
            JOptionPane.showMessageDialog(null,"Created Bill Updated Successfully");
            setVisible(false);
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new Calculate_Bill();
    }
}
