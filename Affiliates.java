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
    public void listOfPeople(Scanner sc, Member member) {
        System.out.println("Enter the names of the associates (max. 10). Type 'exit' to finish:");
        
        // Acceder al conjunto de asociados del miembro específico
        HashSet<String> namesOfAssociates = member.namesOfAssociates;
    
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
                namesOfAssociates.add(nickname); // Agregar el nuevo asociado al miembro específico
                System.out.println("Added associate: " + nickname);
            }
        }
        
        // Mostrar la lista de asociados registrados para ese miembro
        System.out.println("Registered associates: " + (namesOfAssociates.isEmpty() ? "No associates registered." : namesOfAssociates));
    }
<<<<<<< HEAD
=======

<<<<<<< HEAD
=======
    // Método para agregar gastos a nombre del socio principal
    public void addExpense(Scanner sc, Member member) {
        System.out.print("Enter the name of the associate: ");
        String affiliateName = sc.nextLine().trim(); // Lee el nombre del asociado y elimina espacios

        if (!namesOfAssociates.contains(affiliateName)) {
            System.out.println("Error: The affiliate '" + affiliateName + "' is not registered.");
            return;
        }

        System.out.print("Enter the expense amount for affiliate " + affiliateName + ": ");
        int amount;

        try {
            amount = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            // Validar que el monto sea positivo
            if (amount <= 0) {
                System.out.println("Error: The expense amount must be positive.");
                return;
            }

            // Validar que el miembro tenga suficientes fondos antes de registrar el gasto
            if (amount > member.getAvailableFunds()) {
                System.out.println("Error: Insufficient funds to cover the expense of $" + amount);
                return;
            }

            // Registrar el gasto
            expenses.put(affiliateName, expenses.getOrDefault(affiliateName, 0) + amount);
            System.out.println("Expense of $" + amount + " recorded for the associate: " + affiliateName);
            
            // Actualizar los fondos del miembro
            member.setAvailableFunds(member.getAvailableFunds() - amount); // Restar el gasto
            System.out.println("Expense of $" + amount + " deducted from member " + member.getName() + "'s account.");

        } catch (Exception e) {
            System.out.println("Error: Please enter a valid amount.");
            sc.nextLine(); // Limpiar el buffer en caso de error
        }
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

>>>>>>> origin/code-update
>>>>>>> origin/code-update
    // Eliminar asociados si no hay facturas pendientes
    public void removeAuthorizedPersons(Scanner sc, int pendingInvoices) {
        System.out.print("Enter the name of the associate to remove: ");
        String nameToRemove = sc.nextLine().trim(); // Elimina espacios en blanco

        // Verificar si hay facturas pendientes
        if (pendingInvoices != 0) {
            System.out.println("Cannot remove " + nameToRemove + ", there are pending invoices.");
        } else if (namesOfAssociates.remove(nameToRemove)) {
            System.out.println("The person '" + nameToRemove + "' was successfully removed.");
        } else {
            System.out.println("The person '" + nameToRemove + "' was not found in the associates list.");
        }
    }
    

    // Implementaciones de los métodos abstractos
    @Override
    public void showUsers() {
        System.out.println("===== Affiliates Users =====");
        for (String associate : namesOfAssociates) {
            System.out.println("Associate: " + associate);
        }
    }

    @Override
    public boolean removeMember(Scanner sc, String id) {
<<<<<<< HEAD
        // Puedes dejarlo sin implementar o definirlo si es necesario
=======
<<<<<<< HEAD
        // Puedes dejarlo sin implementar o definirlo si es necesario
=======
>>>>>>> origin/code-update
>>>>>>> origin/code-update
        throw new UnsupportedOperationException("Unimplemented method 'removeMember'");
    }

    @Override
    public void registerMember(Scanner sc) {
<<<<<<< HEAD
        listOfPeople(sc, null);
=======
<<<<<<< HEAD
        listOfPeople(sc);
>>>>>>> origin/code-update
    }

    public void addExpense(Scanner sc, Member member,HashSet<String> namesOfAssociates2) {
        // Validar si el ID del miembro es correcto
        System.out.print("Enter your member ID: ");
        String memberId = sc.nextLine().trim();  // Leer y eliminar espacios
        
        if (!member.getId().equals(memberId)) {
            System.out.println("Error: Invalid member ID.");
            return;
        }
        
        // Leer el nombre del afiliado
        System.out.print("Enter the name of the associate: ");
        String affiliateName = sc.nextLine().trim(); // Lee el nombre del asociado y elimina espacios
    
        // Verificar si el afiliado está registrado en la lista de asociados del miembro
        if (!member.getNamesOfAssociates().contains(affiliateName)) {
            System.out.println("Error: The affiliate '" + affiliateName + "' is not registered under this member.");
            return;
        }
    
        // Leer el monto del gasto
        System.out.print("Enter the expense amount for affiliate " + affiliateName + ": ");
        int amount;
    
        try {
            amount = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer de entrada
    
            // Validar que el monto sea positivo
            if (amount <= 0) {
                System.out.println("Error: The expense amount must be positive.");
                return;
            }
    
            // Verificar si el miembro tiene suficientes fondos
            if (amount > member.getAvailableFunds()) {
                System.out.println("Error: Insufficient funds to cover the expense of $" + amount);
                return;
            }
    
            // Registrar el gasto para el afiliado
            expenses.put(affiliateName, expenses.getOrDefault(affiliateName, 0) + amount);
            System.out.println("Expense of $" + amount + " recorded for the associate: " + affiliateName);
    
            // Actualizar los fondos del miembro
            member.setAvailableFunds(member.getAvailableFunds() - amount); // Restar el gasto del saldo del miembro
            System.out.println("Expense of $" + amount + " deducted from member " + member.getName() + "'s account.");
    
        } catch (Exception e) {
            System.out.println("Error: Please enter a valid amount.");
            sc.nextLine(); // Limpiar el buffer en caso de error
<<<<<<< HEAD
        }
=======

       }
=======
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerMember'");
>>>>>>> origin/code-update
>>>>>>> origin/code-update
    }

   
}    




