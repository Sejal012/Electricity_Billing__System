package Project;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Class.forName;

public class Connect {
    Connection c;
    Statement s;
         // statement is the interface;
    Connect()
    {
      //  Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill_system", "root", "Sejal@012");
             s=c.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
