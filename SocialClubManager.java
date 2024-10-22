/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 import java.util.Scanner;

 public class SocialClubManager {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         Member member = new Member(); 
         Affiliates affiliates = new Affiliates();
         Invoices invoices = new Invoices(); 
         int option;
 
         do {
             System.out.println("\n==== MENU OPTIONS ====");
             System.out.println("1. Enter Name and ID");
             System.out.println("2. Check Information");
             System.out.println("3. Show Affiliates Information");
             System.out.println("4. Register Costs");
             System.out.println("5. View Pending Invoices");
             System.out.println("6. Show Users");
             System.out.println("7. Pay Invoices");
             System.out.println("8. add expense");
             System.out.println("9. Remove Member or Affiliate");
             System.out.println("0. Exit");
             System.out.print("Select an option: ");
             option = sc.nextInt();
             sc.nextLine(); 
 
             switch (option) {
                 case 1:
                     member.registerMember(sc); 
                     break;
                 case 2:
                     member.checkInformation(sc); 
                     break;
                 case 3:
                     member.showAffiliatesInfo(sc); 
                     break;
                 case 4:
                     invoices.fullCosts(sc); 
                     break;
                 case 5:
                     invoices.showInvoices(sc); 
                     break;
                 case 6:
                     member.showUsers(); 
                     break;
                 case 7:
                     invoices.payInvoices(sc, member); 
                     break;
                 case 8:
                     affiliates.addExpense(sc, member);
                     break;
                 case 9:
                     member.removeMemberOrAffiliate(sc, invoices); 
                     break;
                 case 0:
                     System.out.println("Exiting...");
                     break;
                 default:
                     System.out.println("INVALID OPTION, PLEASE TRY AGAIN.");
                     break;
             }
         } while (option != 0);
         sc.close();
     }
 }
 

