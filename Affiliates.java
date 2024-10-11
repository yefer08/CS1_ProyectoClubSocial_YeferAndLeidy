import java.util.Scanner;
import java.util.HashSet;

public class Affiliates extends Member {
<<<<<<< HEAD
    private HashSet<String> namesOfAssociates;

    public Affiliates() {
        super();
        this.namesOfAssociates = new HashSet<>();
=======
    private String lastName;
    private HashSet<String> namesOfAssociates;

    public Affiliates(String lastName, String name, String id, int availableFunds, String subscription, int pendingInvoices) {
        super(name, id);
        this.lastName = lastName;
        this.namesOfAssociates = new HashSet<>();
    }
    
    public Affiliates() {
        super(" ", " "); 
        this.lastName = "";
        this.namesOfAssociates = new HashSet<>(); 
>>>>>>> origin/code-update
    }

    // Registro de nombres de asociados
    public void listOfPeople(Scanner sc) {
        System.out.println("Enter the names of the associates (max. 10):");
        while (namesOfAssociates.size() < 10) {
            System.out.println("Enter a name (or type 'exit' to finish):");
            String nickname = sc.nextLine();
            if (nickname.equalsIgnoreCase("exit")) {
                break;
            }
            if (namesOfAssociates.contains(nickname)) {
                System.out.println("The name is already registered. Please enter another.");
            } else {
                namesOfAssociates.add(nickname);
            }
        }
        System.out.println("Registered users: " + namesOfAssociates);
    }

    // Eliminar asociados si no hay facturas pendientes
    public void removeAuthorizedPersons(Scanner sc, int pendingInvoices) {
        System.out.println("Enter the name of the associate to remove:");
        String nameToRemove = sc.nextLine();
        if (pendingInvoices != 0) {
            System.out.println("Cannot remove, there are pending invoices.");
        } else if (namesOfAssociates.contains(nameToRemove)) {
            namesOfAssociates.remove(nameToRemove);
            System.out.println("The person " + nameToRemove + " was successfully removed.");
        } else {
            System.out.println("The person to be deleted was not found.");
        }
    }

<<<<<<< HEAD
    // Mostrar información de los afiliados
=======
    public void listOfPeople(Scanner sc) {
        System.out.println("Enter the names of the associates (max. 10):");
        while (namesOfAssociates.size() < 10) {
            System.out.println("Enter a name (or type 'exit' to finish):");
            String nickname = sc.nextLine();
            if (nickname.equalsIgnoreCase("exit")) {
                break; 
            }
            if (namesOfAssociates.contains(nickname)) {
                System.out.println("The name is already registered. Please enter another.");
            } else {
                namesOfAssociates.add(nickname);  
            }
        }
        System.out.println("Registered users: " + namesOfAssociates);
    }

>>>>>>> origin/code-update
    public void showInfoAffiliates() {
        System.out.println("===== Affiliates Info =====");
        System.out.println("Registered Associates: " + (namesOfAssociates.isEmpty() ? "No associates registered" : namesOfAssociates));
    }
}

<<<<<<< HEAD

         
         
    
        
    
=======
>>>>>>> origin/code-update
