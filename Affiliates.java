import java.util.Scanner;
import java.util.HashSet;

public class Affiliates extends SocialClub {
    private HashSet<String> namesOfAssociates; // Colección para almacenar los nombres de los asociados

    public Affiliates() {
        super(); // Llama al constructor de la clase base
        this.namesOfAssociates = new HashSet<>();
    }

    // Registro de nombres de asociados
    public void listOfPeople(Scanner sc) {
        System.out.println("Enter the names of the associates (max. 10). Type 'exit' to finish:");
        
        while (namesOfAssociates.size() < 10) {
            System.out.print("Enter a name: ");
            String nickname = sc.nextLine().trim(); // Elimina espacios en blanco al inicio y al final
            
            // Salir del bucle si se ingresa "exit"
            if (nickname.equalsIgnoreCase("exit")) {
                break;
            }

            // Validar que el nombre no esté vacío
            if (nickname.isEmpty()) {
                System.out.println("The name cannot be empty. Please enter a valid name.");
                continue;
            }

            // Verificar si el nombre ya está registrado
            if (namesOfAssociates.contains(nickname)) {
                System.out.println("The name '" + nickname + "' is already registered. Please enter another.");
            } else {
                namesOfAssociates.add(nickname); // Agregar el nuevo asociado
                System.out.println("Added associate: " + nickname);
            }
        }
        System.out.println("Registered associates: " + (namesOfAssociates.isEmpty() ? "No associates registered." : namesOfAssociates));
    }

    // Eliminar asociados si no hay facturas pendientes
    public void removeAuthorizedPersons(Scanner sc, int pendingInvoices) {
        System.out.print("Enter the name of the associate to remove: ");
        String nameToRemove = sc.nextLine().trim(); // Elimina espacios en blanco

        // Verificar si hay facturas pendientes
        if (pendingInvoices != 0) {
            System.out.println("Cannot remove " + nameToRemove + ", there are pending invoices.");
        } else if (namesOfAssociates.contains(nameToRemove)) {
            namesOfAssociates.remove(nameToRemove);
            System.out.println("The person '" + nameToRemove + "' was successfully removed.");
        } else {
            System.out.println("The person '" + nameToRemove + "' was not found in the associates list.");
        }
    }

    // Mostrar información de los afiliados
    public void showInfoAffiliates() {
        System.out.println("===== Affiliates Info =====");
        if (namesOfAssociates.isEmpty()) {
            System.out.println("No associates registered.");
        } else {
            System.out.println("Registered Associates: " + namesOfAssociates);
        }
    }

    @Override
    public void showUsers() {
        System.out.println("===== Affiliates Users =====");
        for (String associate : namesOfAssociates) {
            System.out.println("Associate: " + associate);
        }
    }

    @Override
    public void availableFunds(Scanner sc) {
        System.out.println("This method is currently not implemented for affiliates.");
    }
}



