package Project;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.EventListener;

public class Customer_Details extends JFrame implements ActionListener {

    Choice meternumber,cname;
    JTable table;
    JButton search,print,close;
    Customer_Details()
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
            Connect c=new Connect();
            ResultSet res=c.s.executeQuery(("select * from customer"));
            while(res.next())
            {
                meternumber.add(res.getString("meter_no"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        JLabel name=new JLabel("Search by Name");
        name.setBounds(400,20,150,20);
        add(name);

        cname=new Choice();
        cname.setBounds(550,20,150,20);
        add(cname);
        try{
            Connect c=new Connect();
            ResultSet res=c.s.executeQuery(("select * from customer"));
            while(res.next())
            {
                cname.add(res.getString("name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        table=new JTable();
        try{
            Connect c=new Connect();
            ResultSet res=c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(res));

        }
        catch(Exception E)
        {
            E.printStackTrace();
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
            String query = "select * from customer where meter_no='" + meternumber.getSelectedItem() + "' and name='" + cname.getSelectedItem() + "'";
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
        new Customer_Details();
    }
}
