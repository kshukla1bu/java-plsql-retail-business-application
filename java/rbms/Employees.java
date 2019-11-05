/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbms;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Kaushal
 */
public class Employees {
    
    /*
    This method will display all the employees
    */
      public  void displayAllEmployees()
    {
        try {
      //Connection to Oracle server.     
      OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
            // Call Store procedure
            try ( // Call Store procedure
                    Connection conn = ds.getConnection("kshukla1", "kshukla1")) {
                // Call Store procedure
                String sp = "BEGIN ? := retail_pkge.show_employees(); END;";
          try (CallableStatement cs = conn.prepareCall(sp)) {
              cs.registerOutParameter(1, OracleTypes.CURSOR);
              cs.execute();
              
              ResultSet rs = (ResultSet) cs.getObject(1);
              
              Formatter formatter = new Formatter();
              
              System.out.println("-----------------------------------------------------------------------------------------");
              
              System.out.println(formatter.format("%10s %10s %18s %12s", "Employee id", "Name", "Telephone number", "Email" ));
              
              System.out.println("------------------------------------------------------------------------------------------");
              
              
              while (rs.next()) {
                  formatter = new Formatter();
                  System.out.println(formatter.format("%10s %10s %18s %18s",
                          rs.getString(1) ,
                          rs.getString(2) ,
                          rs.getString(3) ,
                          rs.getString(4)
                  ));
              }
              System.out.println("------------------------------------------------------------------------------------------");
          }
            }  
        }
            
        
     catch (SQLException ex) 
     { 
         System.out.println ("\n*** SQLException caught ***\n");
     }
     catch (Exception e) 
     {
         System.out.println ("\n*** other Exception caught ***\n");}
    }
      
    /*
     This method will give the employee montly sale      
    */ 
    public void EmployeeMonthlySale(String empid)
    {
         try 
         {
             if (empid != null)
             {
      //Connection to Oracle server.     
      OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");

                 //get the user input
                 try (Connection conn = ds.getConnection("kshukla1", "kshukla1"); //get the user input
                         CallableStatement cs = conn.prepareCall("{call retail_pkge.show_emp_name(?,?)}")) {
                     
                     cs.setString(1, empid);
                     cs.registerOutParameter(2, OracleTypes.VARCHAR);
                     cs.execute();
                     
                     String result = cs.getString(2);
                     
                     System.out.println("---------------------------------------------------------------------------------------------------------------------");
                     
                     if(!"none".equals(result))
                     {
                         // Call function
                         String sp = "BEGIN ? := retail_pkge.monthly_sale_activities(?); END;";
                         CallableStatement _cs = conn.prepareCall(sp);
                         _cs.registerOutParameter(1, OracleTypes.CURSOR);
                         _cs.setString(2, empid);
                         _cs.execute();
                         
                         ResultSet rs = (ResultSet) _cs.getObject(1);
                         
                         Formatter formatter = new Formatter();
                         
                         System.out.println("Monthly sale activity for employee - " + empid + " name -" + result);
                         
                         System.out.println("---------------------------------------------------------------------------------------------------------------------");
                         
                         System.out.println(formatter.format("%10s %12s %20s %20s %15s", "Month", "Year", "Total sales made", "Total quantity sold", "Total amount"));
                         while (rs.next()) {
                             formatter = new Formatter();
                             System.out.println(formatter.format("%10s %12s %15s %15s %20s",
                                     rs.getString(1) ,
                                     rs.getString(2) ,
                                     rs.getString(3) ,
                                     rs.getString(4) ,
                                     rs.getString(5)
                             ));
                         }
                         
                     }
                     else
                     {
                         System.out.println("Employee ID does not exist");
                     }
                     System.out.println("---------------------------------------------------------------------------------------------------------------------");
                     
                 }
    
         }
     else
        {
          System.out.println("Please enter employee id"); 
        }
     }
          catch (Exception ex) 
        {
            
         System.out.println ("\n*** Error caught - Employee monthly sale cannot found ***\n");
         System.out.println (ex.getMessage());
        }
        
    }
}
