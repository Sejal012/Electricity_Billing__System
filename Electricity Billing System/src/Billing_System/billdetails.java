package Project;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class billdetails extends JFrame {
    String meter;
    billdetails(String meter)
    {

        setBounds(370,50,600,550);
        getContentPane().setBackground(Color.WHITE);

        JTable table =new JTable();
        try{
            Connect c=new Connect();
            String query="select * from totalbill where meter_no='"+meter+"'";
            ResultSet res=c.s.executeQuery((query));

            table.setModel(DbUtils.resultSetToTableModel((res)));
        }
        catch(Exception e)
        {
        }
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,0,600,550);
        add(sp);
        setVisible(true);
    }

    public static void main(String[] args) {
        new billdetails("");
    }
}
