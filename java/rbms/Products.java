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
public class Products {
    
    /*
    This method will display all the products
    */
      public  void displayAllProducts()
    {
        try {
      //Connection to Oracle server.     
      OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
       
            // Call Store function
            try (Connection conn = ds.getConnection("kshukla1", "kshukla1")) {
                // Call Store function
                String sp = "BEGIN ? := retail_pkge.show_products(); END;";
          try (CallableStatement cs = conn.prepareCall(sp)) {
              cs.registerOutParameter(1, OracleTypes.CURSOR);
              cs.execute();
              
              ResultSet rs = (ResultSet) cs.getObject(1);
              
              Formatter formatter = new Formatter();
              
              System.out.println("-----------------------------------------------------------------------------------------");
              
              System.out.println(formatter.format("%10s %10s %10s %10s %10s %10s", "Product id", "Name", "QOH", "QOH Threshold" ,  "Original price" , "Discount category"));
              
              System.out.println("------------------------------------------------------------------------------------------");
              
              
              while (rs.next()) {
                  formatter = new Formatter();
                  System.out.println(formatter.format("%10s %10s %10s %10s %10s %10s",
                          rs.getString(1) ,
                          rs.getString(2) ,
                          rs.getString(3) ,
                          rs.getString(4) ,
                          rs.getString(5) ,
                          rs.getString(6)
                  ));
              }
              System.out.println("------------------------------------------------------------------------------------------");
          }
            }     
        }
            
        
     catch (SQLException ex) 
     { 
         System.out.println ("\n*** Error caught - Cannot view the table ***\n");
     }
     catch (Exception e) 
     {
         System.out.println ("\n*** other Exception caught ***\n");}
    }
}
