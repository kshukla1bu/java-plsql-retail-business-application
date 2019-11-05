package rbms;

import java.util.Scanner;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RBMS {

   public static void main (String args []) throws SQLException {
   
    /**
     * This method will take the menu option from 
     * user and display the necessary data
     */
        
       Scanner scanner = new Scanner(System.in);
       displayOptions();
       try
       {
             while(true)
             {
             int option = scanner.nextInt();
             switch (option) {
                    case 1:
                        System.out.println("Employee table");
                        Employees objEmployees = new Employees();
                        objEmployees.displayAllEmployees();
                         displayOptions();
                        break;
                        
                    case 2:
                        System.out.println("Customer table");
                        Customers objCustomers = new Customers();
                        objCustomers.displayAllCustomers();
                         displayOptions();
                        break;
                        
                    case 3:
                        System.out.println("Discount table");
                        Discount objDiscount = new Discount();
                        objDiscount.displayAllDiscounts();
                         displayOptions();
                        break;
                        
                    case 4:
                        System.out.println("Products table");
                        Products objProducts = new Products();
                        objProducts.displayAllProducts();
                         displayOptions();
			break;
                        
                    case 5:
                        System.out.println("Supplier table");
                        Suppliers objSuppliers = new Suppliers();
                        objSuppliers.displayAllSuppliers();
                         displayOptions();
			break;
                        
                    case 6:
                        System.out.println("Supplies table");
                        Supplies objSupplies = new Supplies();
                        objSupplies.displayAllSupplies();
                         displayOptions();
			break;   
                        
                    case 7:
                        System.out.println("Logs table");
                        Logs objLogs = new Logs();
                        objLogs.displayAllLogs();
                         displayOptions();
                        break;
                        
                    case 8:
                        System.out.println("Purchases table");
                        Purchases objPurchases = new Purchases();
                        objPurchases.displayAllPurchases();
                         displayOptions();
                        break;
                    
                    case 9:
                        Customers objCust = new Customers();
                        boolean isPhonevalid = false;
                        String telephone = "";
                        System.out.println("Enter customer id");
                        String cid = scanner.next();
                        System.out.println("Enter customer name");
                        String name = scanner.next();
                        System.out.println("Enter telephone number");
                        telephone = scanner.next();
                        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
                        Matcher matcher = pattern.matcher(telephone); 
                        isPhonevalid = matcher.matches();
                        if(isPhonevalid == false || Character.toLowerCase(cid.charAt(0)) != 'c')
                        {
                            if (isPhonevalid == false)
                            {   
                            System.out.println("-----------------------------------------------------------------------------------------");
                            
                            System.out.println("Please enter valid phone number");
                             
                            System.out.println("-----------------------------------------------------------------------------------------");
            
                            }
                             if (Character.toLowerCase(cid.charAt(0)) != 'c')
                            {
                            System.out.println("-----------------------------------------------------------------------------------------");
                            
                            System.out.println("Please enter customer id starting from 'c' ");
                             
                            System.out.println("-----------------------------------------------------------------------------------------");
            
                            }
                        }                       
                       
                        else
                        {
                            objCust.addCustomers(cid, name, telephone);
                        }
                         displayOptions();
                        break;
                    
                    case 10: 
                        Purchases objp = new Purchases();
                        System.out.println("Enter the purchase id");
                        String purid = scanner.next();
                        objp.deletePurchase(purid);
                         displayOptions();
                        break;
                        
                    case 11:
                        Purchases objpu = new Purchases();
                         System.out.println("Enter the purchase id");
                        String purrid = scanner.next();
                        objpu.calculatePurchaseSaving(purrid);
                         displayOptions();
                        break;
                        
                    case 12:
                        Purchases objAddPurchase = new Purchases();
                         System.out.println("Enter the employee id");
                         String eid = scanner.next();
                         System.out.println("Enter the product id");
                         String pid = scanner.next();
                         System.out.println("Enter the customers id");
                         String c_id = scanner.next();
                         System.out.println("Enter the purchase quantity");
                         String pur_qty = scanner.next();
                         
                         
                        if (Character.toLowerCase(eid.charAt(0)) != 'e' || Character.toLowerCase(pid.charAt(0)) != 'p' || Character.toLowerCase(c_id.charAt(0)) != 'c')
                         {
                              if (Character.toLowerCase(eid.charAt(0)) != 'c')
                            {
                            System.out.println("-----------------------------------------------------------------------------------------");
                            
                            System.out.println("Please enter employee id starting from 'e' ");
                             
                            System.out.println("-----------------------------------------------------------------------------------------");
            
                            }
                               if (Character.toLowerCase(pid.charAt(0)) != 'p')
                            {
                            System.out.println("-----------------------------------------------------------------------------------------");
                            
                            System.out.println("Please enter products id starting from 'p' ");
                             
                            System.out.println("-----------------------------------------------------------------------------------------");
            
                            }
                                if (Character.toLowerCase(c_id.charAt(0)) != 'c')
                            {
                            System.out.println("-----------------------------------------------------------------------------------------");
                            
                            System.out.println("Please enter customer id starting from 'c' ");
                             
                            System.out.println("-----------------------------------------------------------------------------------------");
            
                            }                          
                         }
                              
                         else
                        {
                         objAddPurchase.AddPurchase(eid, pid, c_id, pur_qty);
                        }     
                              
                          displayOptions();
                        break;
                        
                    case 13:
                          Employees objEmp  = new Employees();
                         System.out.println("Enter the employee id");
                         String empid = scanner.next();
                         objEmp.EmployeeMonthlySale(empid);
                          displayOptions();
                         break;
                        
                    case 14:
                        System.out.println("Exit");
			scanner.close();
			System.exit(0);
                        
                    default:
                       System.out.println("Please enter the correct option.");
                        break;
                }    
              }
                 
        }
       catch (Exception ex)
       {
          System.out.println("\n");        
       }
    }
    
   /*
   Display the menu option
   */
      private static void displayOptions() {
        try {
              
              System.out.println("*************************************************************************************");
             
                System.out.println(" Enter the below options to get the retail buisness management system details \n");
                System.out.println("\t\t1) Show Employees ");
                System.out.println("\t\t2) Show Customers ");
                System.out.println("\t\t3) Show Discount ");
                System.out.println("\t\t4) Show Products ");
                System.out.println("\t\t5) Show Suppliers ");
                System.out.println("\t\t6) Show Supplies ");
                System.out.println("\t\t7) Show Logs ");
                System.out.println("\t\t8) Show Purchases ");
                System.out.println("\t\t9) Add Customers ");
                System.out.println("\t\t10) Delete Purchases ");
                System.out.println("\t\t11) Calculate purchase saving ");
                System.out.println("\t\t12) Add purchase");
                System.out.println("\t\t13) Calculate employee monthly sale");
                System.out.println("\t\t14) Exit program");
             
        } catch (Exception e) {
            System.out.println("\n");  
        }
}
}