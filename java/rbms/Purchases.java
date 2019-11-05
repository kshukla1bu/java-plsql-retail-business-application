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
 *
 * @author Kaushal
 */
public class Purchases {
    
    /*
    This method will display all the purchases
    */
    public  void displayAllPurchases()
    {
        try {
      //Connection to Oracle server.     
      OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
            // Call function
            try (Connection conn = ds.getConnection("kshukla1", "kshukla1")) {
                // Call function
                String sp = "BEGIN ? := retail_pkge.show_purchases(); END;";
          try (CallableStatement cs = conn.prepareCall(sp)) {
              cs.registerOutParameter(1, OracleTypes.CURSOR);
              cs.execute();
              
              ResultSet rs = (ResultSet) cs.getObject(1);
              
              Formatter formatter = new Formatter();
              
              System.out.println("-------------------------------------------------------------------------------------------------------------------");
              
              System.out.println(formatter.format("%10s %12s %12s %12s %12s %25s %20s", "Purchase id", "Employee id", "Product id", "Customer id", "Quantity", "Purchase time", "Total price"));
              
              System.out.println("---------------------------------------------------------------------------------------------------------------------");
              
              
              while (rs.next()) {
                  formatter = new Formatter();
                  System.out.println(formatter.format("%10s %12s %12s %12s %12s %30s %12s",
                          rs.getString(1) ,
                          rs.getString(2) ,
                          rs.getString(3) ,
                          rs.getString(4) ,
                          rs.getString(5) ,
                          rs.getString(6),
                          rs.getString(7)
                  ));
              }
              
              
              System.out.println("---------------------------------------------------------------------------------------------------------------------");
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
    
    /*
    This method will delete the purchase
    purid is purchase id
    */
    public void deletePurchase(String purid)
    {
         try 
         {
      //Connection to Oracle server.     
      OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
             //get the user input
             try ( // Call Store procedure
                     Connection conn = ds.getConnection("kshukla1", "kshukla1"); //get the user input
                     CallableStatement cs = conn.prepareCall("{call retail_pkge.delete_purchase(?,?)}")) {
                 
                 cs.setString(1, purid);
                 cs.registerOutParameter(2, OracleTypes.VARCHAR);
                 cs.execute();
                 
                 String result = cs.getString(2);
                 
                 System.out.println("---------------------------------------------------------------------------------------------------------------------");
                 
                 if("success".equals(result))
                 {
                     System.out.println("Purchase deleted successfully");
                 }
                 else
                 {
                     System.out.println(result);
                 }
                 
                 System.out.println("---------------------------------------------------------------------------------------------------------------------");
                 
             }
   }
          catch (Exception ex) 
        {
         System.out.println ("\n*** Error caught - Please check the purchase id ***\n");
        }
        
    }
    
  
    /*
    This method will calculate the purchase saving
    purid is purchase id
    */
    public void calculatePurchaseSaving(String purid)
    {
         try 
         {
      //Connection to Oracle server.     
      OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
             //get the user input
             try ( // Call Store procedure
                     Connection conn = ds.getConnection("kshukla1", "kshukla1")) {
                 //get the user input
                 
                 String sp = "BEGIN ? := retail_pkge.purchase_saving(?); END;";
          try (CallableStatement cs = conn.prepareCall(sp)) {
              cs.registerOutParameter(1, OracleTypes.FLOAT);
              cs.setString(2, purid);
              cs.execute();
              
              
              String result = cs.getString(1);
              
              Formatter formatter = new Formatter();
              
              System.out.println("---------------------------------------------------------------------------------------------------------------------");
              
              
              System.out.println(formatter.format("%10s", "Purchase saving"));
              
              System.out.println("---------------------------------------------------------------------------------------------------------------------");
              
              
              if(result != null)
              {
                  System.out.println(result);
              }
              
              System.out.println("---------------------------------------------------------------------------------------------------------------------");
          }
             }
    
      
         }
        catch (SQLException e) 
        { 
         System.out.println ("\n*** Error caught - Please check the purchase id ***\n");
        }
       catch (Exception e) 
         {
           System.out.println (e.getMessage());
         }
       
    }
    
    
    
      
    
    //This method will add the purchase
    public void AddPurchase(String eid, String pid, String cid, String pur_qty)
    {
         try 
         {
      //Connection to Oracle server.     
      OracleDataSource ds = new oracle.jdbc.pool.OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
             //get the user input
             try ( // Call Store procedure
                     Connection conn = ds.getConnection("kshukla1", "kshukla1"); //get the user input
                     CallableStatement cs = conn.prepareCall("{call retail_pkge.add_purchase(?,?,?,?,?)}")) {
                 
                 cs.setString(1, eid);
                 cs.setString(2, pid);
                 cs.setString(3, cid);
                 cs.setString(4, pur_qty);
                 cs.registerOutParameter(5, OracleTypes.VARCHAR);
                 cs.execute();
                 
                 String result = cs.getString(5);
                 
                 System.out.println("---------------------------------------------------------------------------------------------------------------------");
                 
                 if(   null != result)
                     switch (result) {
                         case "1":
                             System.out.println("Purchase inserted Successfully");
                             break;
                         case "2":
                             System.out.println("Purchase inserted Successfully");
                             
                             System.out.println("The product quantity on hand is below it's threshold");
                             
                             System.out.println("Congrats ! Required product order placed succesfully");
                             break;
                         case "3" :
                             System.out.println("Insufficient quantity in stock");
                             break;
                         default:
                             System.out.println(result);
                             break;
                     }
                 
                 System.out.println("---------------------------------------------------------------------------------------------------------------------");
                 
             }
    
         }
          catch (Exception ex) 
        {
         System.out.println("Error occured - please check your input values");
        }
        
    }
}
