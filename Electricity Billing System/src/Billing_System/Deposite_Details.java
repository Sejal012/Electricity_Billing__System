package Project;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Deposite_Details extends JFrame implements ActionListener {
    Choice meternumber,cmonth;
    JTable table;
    JButton search,print,close;

    Deposite_Details()
    {
        super("Deposit Details");
        setSize(750,600);
        setLocation(300,50 );
        setLayout(null);
        getContentPane().setBackground(new Color(170, 170, 232));

        JLabel meterno=new JLabel("Search by Meter Number");
        meterno.setBounds(30,20,150,20);
        add(meterno);

        meternumber=new Choice();
        meternumber.setBounds(200,20,150,20);
        add(meternumber);

        try{
            Connect c= new Connect();
            ResultSet res=c.s.executeQuery("Select * from customer");
            while(res.next())
            {
                meternumber.add(res.getString("meter_no"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        JLabel month=new JLabel("Search by Month");
        month.setBounds(400,20,150,20);
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
        cmonth.setBounds(550,20,150,20);
        add(cmonth);

        table= new JTable();
        try {
            Connect c=new Connect();
            ResultSet res=c.s.executeQuery("select * from totalbill");

            table.setModel(DbUtils.resultSetToTableModel(res));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        JScrollPane schroll=new JScrollPane(table);
        schroll.setBounds(10,100,700,450);
        schroll.setBackground(Color.WHITE);
        add(schroll);

        search=new JButton("Search");
        search.setBackground(Color.WHITE);
        search.setBounds(30,60,100,25);
        search.addActionListener(this);
        add(search);

        print=new JButton("Print");
        print.setBackground(Color.WHITE);
        print.setBounds(310,60,100,25);
        print.addActionListener(this);
        add(print);

        close=new JButton("Close");
        close.setBackground(Color.WHITE);
        close.setBounds(600,60,100,25);
        close.addActionListener(this);
        add(close);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search) {
            String query = "select * from totalbill where meter_no='" + meternumber.getSelectedItem() + "' and month='" + cmonth.getSelectedItem() + "'";
            try {
                Connect c = new Connect();
                ResultSet r = c.s.executeQuery(query);
                table.setModel((DbUtils.resultSetToTableModel(r)));
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
        else if(ae.getSource()==print){
            try{
                table.print();
            }
            catch(Exception b){
                b.printStackTrace();
            }
        }
        else if(ae.getSource()==close){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Deposite_Details();
    }

}
