import java.util.Scanner;
import java.util.HashSet;

public class Affiliates extends Member {
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
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public void removeAuthorizedPersons(Scanner sc) {
        System.out.println("Enter the name of the associate to remove:");
        String nameToRemove = sc.nextLine(); 
        if (namesOfAssociates.contains(nameToRemove)) {
            namesOfAssociates.remove(nameToRemove);
            System.out.println("The person " + nameToRemove + " was successfully removed.");
        } else {
            System.out.println("The person to be deleted was not found.");
        }
    }

    public void showInfoAffiliates() {
        System.out.println("===== Affiliates Info =====");
        System.out.println("Registered Associates: " + (namesOfAssociates.isEmpty() ? "No associates registered" : namesOfAssociates));
    }
}


         
         
    
        
    
