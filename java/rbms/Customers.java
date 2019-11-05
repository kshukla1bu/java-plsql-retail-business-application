/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbms;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.CallableStatement;
import oracle.jdbc.OracleTypes;


/**
 * @author Kaushal
 */
public class Customers {
    
    /*
    This method will display all the customers
    */
    public  void displayAllCustomers()
    {
        try {
      //Connection to Oracle server.     
      OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
    
            try (Connection conn = ds.getConnection("kshukla1", "kshukla1")) {
                // Call function
                String sp = "BEGIN ? := retail_pkge.show_customers(); END;";
          try (CallableStatement cs = conn.prepareCall(sp)) {
              cs.registerOutParameter(1, OracleTypes.CURSOR);
              cs.execute();
              
              ResultSet rs = (ResultSet) cs.getObject(1);
              
              Formatter formatter = new Formatter();
              
              System.out.println("-----------------------------------------------------------------------------------------");
              
              System.out.println(formatter.format("%10s %10s %18s %12s %20s", "Customer id", "Name", "Telephone number", "Visits made" ,  "Last visit date"));
              
              System.out.println("------------------------------------------------------------------------------------------");
              
              
              while (rs.next()) {
                  formatter = new Formatter();
                  System.out.println(formatter.format("%10s %10s %18s %10s %25s",
                          rs.getString(1) ,
                          rs.getString(2) ,
                          rs.getString(3) ,
                          rs.getString(4) ,
                          rs.getString(5)
                  ));
              }
              System.out.println("------------------------------------------------------------------------------------------");
          }
            }     
        }
            
        
     catch (SQLException ex) 
     { 
         System.out.println ("\n*** Error caught - Cannot view the table ***\n");
        // System.out.println ("\n*** SQLException caught ***\n");
     }
     catch (Exception e) 
     {
           System.out.println (e.getMessage());
    }
    }
    
    /*
    Add the customer
    cid is customer id
    name is customer name
    telephone 
    */
    public void addCustomers(String cid, String name, String telephone)
    {
         try 
         {
      //Connection to Oracle server.     
      OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
             //get the user input
             try ( // Call Store procedure
                     Connection conn = ds.getConnection("kshukla1", "kshukla1"); //get the user input
                     CallableStatement cs = conn.prepareCall("{call retail_pkge.add_customer(?,?,?,?)}")) {
                 
                 cs.setString(1, cid);
                 cs.setString(2, name);
                 cs.setString(3, telephone);
                 cs.registerOutParameter(4, OracleTypes.VARCHAR);
                 cs.execute();
                 
                 String result = cs.getString(4);
                 
                 System.out.println("---------------------------------------------------------------------------------------------------------------------");
                 
                 System.out.println(result);
                 
                 System.out.println("---------------------------------------------------------------------------------------------------------------------");
                 
             }
         }
       
         catch (Exception ex) 
        {
         System.out.println (ex.getMessage());
        }
        
    }
   
}
