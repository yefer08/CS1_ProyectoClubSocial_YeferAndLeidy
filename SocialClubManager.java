/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 import java.util.Scanner;

public class SocialClubManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Member member = new Member();
        Affiliates affiliates = new Affiliates();
        Invoices invoice = new Invoices(); 
        int option;

        do {
            System.out.println("\n==== MENU OPTIONS ====");
            System.out.println("1. Enter Name and ID");
            System.out.println("2. check information");
            System.out.println("3. Enter Associate Names");
            System.out.println("4. View Pending Invoices");
            System.out.println("5. Show Affiliates Information");
            System.out.println("6. Show Users");
            System.out.println("7. Pay Invoices");
            System.out.println("8. Register Costs");
            System.out.println("9. Remove Member or Affiliate");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            option = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    member.registerMember(sc); 
                    break;
                case 2:
                    member.checkInformation(sc); 
                    break;
                case 3:
                    affiliates.registerMember(sc); 
                    break;
                case 4:
                    invoice.displayExpenses(); 
                    break;
                case 5:
                    affiliates.showUsers(); 
                    break;
                case 6:
                    member.showUsers(); 
                    break;
                case 7:
                    invoice.payInvoices(sc, member); 
                    break;
                case 8:
                    invoice.registerCosts(sc, affiliates, member);
                    break;
                case 9:
                    member.removeMemberOrAffiliate(sc, invoice); 
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

 
