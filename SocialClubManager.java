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
            System.out.println("1. Register Member Info (Name and Funds)");
            System.out.println("2. Enter Associate Names");
            System.out.println("3. View Pending Invoices");
            System.out.println("4. Show Affiliates Information");
            System.out.println("5. Show Users");
            System.out.println("6. Pay Invoices");
            System.out.println("7. Register Costs");
            System.out.println("8. Remove Associate");
            System.out.println("9. Remove Member");
            System.out.println("10. Add Expense");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                    member.registerMember(sc); // Método combinado
                    break;
                case 2:
                    affiliates.listOfPeople(sc); 
                    break;
                case 3:
                    invoices.displayExpenses();  // Cambiado a displayExpenses
                    break;
                case 4:
                    affiliates.showInfoAffiliates(); 
                    break;
                case 5:
                    member.showUsers(); 
                    break;
                case 6:
                    invoices.payInvoices(sc, member); 
                    break;
                case 7:
                    invoices.fullCosts(sc); 
                    break;
                case 8:
                    affiliates.removeAuthorizedPersons(sc, invoices.getPendingInvoices()); 
                    break;
                case 9:
                    member.removeMember(sc, null); // Asegúrate de que 'null' sea adecuado aquí
                    break;
                case 10:
                    affiliates.addExpense(sc, member);
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

 
 
