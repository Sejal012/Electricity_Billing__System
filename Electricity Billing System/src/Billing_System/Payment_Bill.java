package Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment_Bill extends JFrame implements ActionListener {
   JButton back;
   String meter;
    Payment_Bill(String meter){
        this.meter=meter;
        JEditorPane j=new JEditorPane();
        j.setEditable(false);

        try{
            j.setPage("https://paytm.com/online-payments");
            j.setBounds(400,150,800,600);
        }
        catch(Exception E){
            E.printStackTrace();
            j.setContentType("text/html");
            j.setText("<html>Error! Error! Error! Error! Error! Error!</html>");

        }

        JScrollPane pane=new JScrollPane(j);
        add(pane);

        back=new JButton("Back");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        j.add(back);

      setSize(1290,800);
      setLocation(0,0);
      setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
      setVisible(false);
      new payBill(meter);
    }
    public static void main(String[] args) {
        new Payment_Bill("");
    }
}
