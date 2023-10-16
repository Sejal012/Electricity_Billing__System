package Billing_System;

import javax.swing.*;

public class  Electricity_Billing_System extends JFrame implements Runnable{
    Thread t;
    Electricity_Billing_System(){
       ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("elect.jpg"));
       JLabel image=new JLabel(i);
       add(image);
        setVisible(true);
        int x=1;
  for(int j=2;j<500;j++,x++)
  {
      setSize(j,j);
      setLocation(400-(j/x),350-(j/2));
      try{
          Thread.sleep(2);
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
  }
//setSize(1300,700);//total size of screen
        t=new Thread(this);
    t.start();
    }
    public void run(){
        try{
            Thread.sleep(5000);
            setVisible(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String []args)
    {

        new Electricity_Billing_System();
    }
}
