import java.util.HashSet;
import java.util.Scanner;
/**
 *
 * @author yanca
 */

public class Affiliates extends Member {
    private String lastName;
    private HashSet<String> namesOfAssociates;
 
    public Affiliates(String lastName, String name, String id, int availableFunds, String subscription, int pendingInvoices) {
        super(name, id, availableFunds, subscription, pendingInvoices);
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
    public void showInfoAffiliates() {
        System.out.println("===== Affiliates Info =====");
        System.out.println("Registered Associates: " + (namesOfAssociates.isEmpty() ? "No associates registered" : namesOfAssociates));
    }
}
         
         
    
        
    
