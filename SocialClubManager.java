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
            System.out.println("2. Enter Funds");
            System.out.println("3. Enter Associate Names");
            System.out.println("4. View Pending Invoices");
            System.out.println("5. Show Affiliates Information");
            System.out.println("6. Show Users");
            System.out.println("7. pays");
            System.out.println("8. Register Costs");
            System.out.println("9. Remove Partners");
            System.out.println("10.remove Member");
            System.out.println("11.addExpense");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                    member.enterNameAndId(sc); 
                    break;
                case 2:
                    member.availableFunds(sc); 
                    break;
                case 3:
                    affiliates.listOfPeople(sc); 
                    break;
                case 4:
                    invoice.displayExpenses(); 
                    break;
                case 5:
                    affiliates.showInfoAffiliates(); 
                    break;
                case 6:
                    member.showUsers(); 
                    break;
                case 7:
                    invoice.payInvoices(sc, member); 
                    break;
                case 8:
                    invoice.fullCosts(sc); 
                    break;
                case 9:
                    affiliates.removeAuthorizedPersons(sc, invoice.getPendingInvoices()); 
                    break;
                case 10:
                    member.removeMember(sc, null);
                case 11:
                affiliates.addExpense(sc, member);
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
 
