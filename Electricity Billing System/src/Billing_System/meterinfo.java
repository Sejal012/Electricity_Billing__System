package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;

public class meterinfo extends JFrame implements ActionListener {
    JTextField tname, taddress, tcity, tstate, temail, tphone;
    JButton submit;
    Choice meterlocation,cmetertype,cphasecode,cbilltype;
String meter;
    meterinfo(String meter) {
        this.meter=meter;
        setLocation(400, 50);
        setSize(600, 530);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(160, 5, 200, 20);
        heading.setFont(new Font("Tahona", Font.PLAIN, 24));
        p.add(heading);


        JLabel jmeterno = new JLabel("Meter Number");
        jmeterno.setBounds(50, 100, 100, 20);
        p.add(jmeterno);

        JLabel autogeneratedmeterno = new JLabel(meter);
        autogeneratedmeterno.setBounds(170, 100, 100, 20);
        p.add(autogeneratedmeterno);

        JLabel jlocation = new JLabel("Meter Location");
        jlocation.setBounds(50, 140, 100, 20);
        p.add(jlocation);

        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(170,140,200,20);
        p.add(meterlocation);

        JLabel jmetertype = new JLabel("Meter Type");
        jmetertype.setBounds(50, 180, 100, 20);
        p.add(jmetertype);

        cmetertype = new Choice();
        cmetertype.add("Electric Meter");
        cmetertype.add("Solar Meter");
        cmetertype.add("Smart Meter");
        cmetertype.setBounds(170,180,200,20);
        p.add(cmetertype);
        JLabel jphase = new JLabel("Phase Code");
        jphase.setBounds(50, 220, 100, 20);
        p.add(jphase);

        cphasecode = new Choice();
        cphasecode.add("011");
        cphasecode.add("022");
        cphasecode.add("033");
        cphasecode.add("044");
        cphasecode.add("055");
        cphasecode.add("066");
        cphasecode.add("077");
        cphasecode.add("088");
        cphasecode.add("099");
        cphasecode.setBounds(170,220,200,20);
        p.add(cphasecode);


        JLabel jbilltype = new JLabel("Bill Type");
        jbilltype.setBounds(50, 260, 100, 20);
        p.add(jbilltype);

        cbilltype = new Choice();
        cbilltype.add("Normal Bill");
        cbilltype.add("Commercial Bill ");
        cbilltype.add("Smart Meter");
        cbilltype.setBounds(170,260,100,20);
        p.add(cbilltype);

        JLabel jdays = new JLabel("Days");
        jdays.setBounds(50, 300, 100, 20);
        p.add(jdays);

        JLabel days = new JLabel("30 Days");
        days.setBounds(170, 300, 100, 20);
        p.add(days);

        JLabel note = new JLabel("Note");
        note.setBounds(50, 340, 100, 20);
        p.add(note);

        JLabel day = new JLabel("By Default Bill is Calculated for 30 days");
        day.setBounds(170, 340, 300, 20);
        p.add(day);


        submit = new JButton("Submit");
        submit.setBounds(170, 410, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        p.add(submit);


        add(p, "Center");
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource("hicon1.jpg"));
        Image i1 = i.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        JLabel img = new JLabel(i2);
        add(img, "West");
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == submit) {
            String meterno=meter;
            String location=meterlocation.getSelectedItem();
            String metertype=cmetertype.getSelectedItem();
            String phasecode=cphasecode.getSelectedItem();
            String billtype=cbilltype.getSelectedItem();
            String day="30";
            String query1="insert into meter_information values ('"+meterno+"','"+location+"','"+metertype+"','"+phasecode+"','"+billtype+"','"+day+"')";
           // String query2="insert into login values ('"+meter+"','','"+name+"','','')";
            try {
                Connect c=new Connect();
                c.s.executeUpdate(query1);
               // c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,"Meter Information added Successfully");
                setVisible(false);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            setVisible(false);
        }
    }

    public static void main(String[] args) {

        new meterinfo("");
    }
}
