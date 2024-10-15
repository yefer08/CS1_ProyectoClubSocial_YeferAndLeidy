import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class Affiliates extends SocialClub {
    private HashSet<String> namesOfAssociates; // Colección para almacenar los nombres de los asociados
    private Map<String, Integer> expenses; // Mapa para almacenar los gastos de los afiliados

    public Affiliates() {
        super(); // Llama al constructor de la clase base
        this.namesOfAssociates = new HashSet<>();
        this.expenses = new HashMap<>(); // Inicializa el mapa de gastos
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

    // Método para agregar gastos a nombre del socio principal
    public void addExpense(Scanner sc, Member member) {
        System.out.print("Ingresa el nombre del asociado: ");
        String affiliateName = sc.nextLine().trim(); // Lee el nombre del asociado y elimina espacios
    
        if (!namesOfAssociates.contains(affiliateName)) {
            System.out.println("Error: El afiliado '" + affiliateName + "' no está registrado.");
            return;
        }
    
        System.out.print("Ingresa el monto del gasto a generar por parte del afiliado " + affiliateName + ": ");
        int amount = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
    
        // Validar que el miembro tenga suficientes fondos antes de registrar el gasto
        if (amount > member.getAvailableFunds()) {
            System.out.println("Error: Fondos insuficientes para cubrir el gasto de $" + amount);
            return;
        }
    
        // Registrar el gasto
        expenses.put(affiliateName, expenses.getOrDefault(affiliateName, 0) + amount);
        System.out.println("Gasto de $" + amount + " registrado para el asociado: " + affiliateName);
        
        // Actualizar los fondos del miembro
        member.setAvailableFunds(member.getAvailableFunds() - amount); // Restar el gasto
        System.out.println("Gasto de $" + amount + " restado de la cuenta del miembro " + member.getName());
    }

    // Mostrar gastos registrados
    public void showExpenses() {
        System.out.println("===== Registered Expenses =====");
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            for (Map.Entry<String, Integer> entry : expenses.entrySet()) {
                System.out.println("Affiliate: " + entry.getKey() + ", Total Expense: $" + entry.getValue());
            }
        }
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

    @Override
    public boolean removeMember(Scanner sc, String id) {
        throw new UnsupportedOperationException("Unimplemented method 'removeMember'");
    }
}




