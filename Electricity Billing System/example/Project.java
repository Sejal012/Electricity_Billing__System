package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Project extends JFrame  implements ActionListener {
    JMenuItem newcustomer,coustomerdeatil,depositdeatil,calculatebill,updateinfo,viewinfo,paybill,billdetail,generatebill,notpad,cal,exit;
    String atype,meter;
    Project(String atype,String meter)
    {
this.atype=atype;
this.meter=meter;
  ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("sun-setting-silhouette-electricity-pylons.jpg"));
        Image img=i.getImage().getScaledInstance(1270,670,Image.SCALE_DEFAULT);
        ImageIcon i1=new ImageIcon(img);
        JLabel image=new JLabel(i1);
        add(image);

        setLayout(new FlowLayout());
        JMenuBar mb=new JMenuBar();
        setJMenuBar(mb);
        JMenu master=new JMenu("Master");
        master.setForeground(Color.BLUE);
       // mb.add(master);
         newcustomer=new JMenuItem("New Customer");
        master.add(newcustomer);
        newcustomer.setMnemonic('N');
        newcustomer.addActionListener(this);
        newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));


         coustomerdeatil=new JMenuItem("Customer Details");
        master.add(coustomerdeatil);
        coustomerdeatil.setMnemonic('E');
        coustomerdeatil.addActionListener(this);
        coustomerdeatil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));


         depositdeatil=new JMenuItem("Deposit Details");
        master.add(depositdeatil);
        depositdeatil.setMnemonic('D');
        depositdeatil.addActionListener(this);
        depositdeatil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));

        calculatebill=new JMenuItem("Calculate Bill");
        master.add(calculatebill);
        calculatebill.setMnemonic('C');
        calculatebill.addActionListener(this);
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        JMenu info=new JMenu("Information");
        info.setForeground(Color.BLUE);
       // mb.add(info);

         updateinfo=new JMenuItem("Update Information");
        info.add(updateinfo);
        updateinfo.setMnemonic('U');
        updateinfo.addActionListener(this);
        updateinfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));

        viewinfo=new JMenuItem("View Information");
        info.add(viewinfo);
        viewinfo.setMnemonic('V');
        viewinfo.addActionListener(this);
        viewinfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));


        JMenu user=new JMenu("User");
        user.setForeground(Color.BLUE);
      //  mb.add(user);

        paybill=new JMenuItem("Pay Bill");
        user.add(paybill);
        paybill.setMnemonic('P');
        paybill.addActionListener(this);
        paybill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

         billdetail=new JMenuItem("Bill Detail");
        user.add(billdetail);
        billdetail.setMnemonic('B');
        billdetail.addActionListener(this);
        billdetail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));

          JMenu report=new JMenu("Report");
          report.setForeground(Color.BLUE);
        //  mb.add(report);

           generatebill=new JMenuItem("Generate Bill");
          report.add(generatebill);
          generatebill.setMnemonic('G');
        generatebill.addActionListener(this);
          generatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));


          JMenu utility=new JMenu("Utility");
          utility.setForeground(Color.BLUE);
        //  mb.add(utility);

           notpad=new JMenuItem("Notepad");
          utility.add(notpad);
          notpad.setMnemonic('N');
        notpad.addActionListener(this);
        notpad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));


          cal=new JMenuItem("Calculator");
          utility.add(cal);
          cal.setMnemonic('C');
        cal.addActionListener(this);
        cal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));


          JMenu mexit=new JMenu("Exit");
          mexit.setForeground(Color.BLUE);
       //   mb.add(mexit);

           exit=new JMenuItem("Exit");
          exit.setMnemonic('C');
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        mexit.add(exit);

          if(atype.equals("Customer"))
          {
              mb.add(info);
              mb.add(user);
              mb.add(report);
          }
          else
          {
              mb.add(master);
          }
        mb.add(utility);
        mb.add(mexit);



        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae)
    {
        String msg=ae.getActionCommand();
        if(msg=="New Customer")
        {
            new New_Customer();
        }
        else if(msg=="Customer Details")
        {
            new Customer_Details();
        }
        else if(msg=="Calculate Bill")
        {
            new Calculate_Bill();
        }
        else if(msg.equals("View Information"))
        {
            new View_Information(meter);
        }
        else if(msg=="Deposit Details")
        {
            new Deposite_Details();
        }
        else if(msg=="Update Information")
        {
            new UpDate_Information(meter);
        }
        else if(msg=="Bill Detail")
        {
            new billdetails(meter);
        }
        else if(msg=="Notepad")
        {
            try
            {
                Runtime.getRuntime().exec("notepad.exe");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(msg=="Calculator")
        {
            try
            {
                Runtime.getRuntime().exec("calc.exe");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (msg=="Pay Bill"){
          new payBill(meter);
        }
        else if (msg=="Exit") {
            setVisible(false);
            new login();
        }
        else if (msg=="Generate Bill") {
            new Generate_Bill(meter);
        }

    }
    public static void main(String[] args)
    {
        new Project("","");
    }
}
