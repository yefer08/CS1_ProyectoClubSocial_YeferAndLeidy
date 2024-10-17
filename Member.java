/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author yanca
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Member extends SocialClub {
    private static final int MAX_USERS = 35; // Límite de usuarios
    private static final int MAX_VIP = 3; // Límite de usuarios VIP
    private static final int REGULAR_LIMIT = 1000000; // Límite máximo para Regular
    private static final int VIP_LIMIT = 5000000; // Límite máximo para VIP
    private static int vipCount = 0; // Contador de usuarios VIP

    protected HashSet<String> namesOfAssociates; // Nombres de los asociados
    private Invoices invoices; // Facturas del usuario
    private Affiliates affiliates;
    private static ArrayList<Member> userList = new ArrayList<>(); // Lista estática de usuarios

    // Constructor por defecto
    public Member() {
        super();
        this.namesOfAssociates = new HashSet<>();
        this.availableFunds = 0;
        this.invoices = new Invoices(0); // Inicializar facturas
        this.subscription = "";
        this.affiliates = new Affiliates();
    }

    // Constructor que recibe nombre y ID
    public Member(String name, String id) {
        super();
        this.namesOfAssociates = new HashSet<>();
        this.name = name;
        this.id = id;
        this.availableFunds = 0;
        this.invoices = new Invoices(0); // Inicializar las facturas
        this.subscription = "";
        this.affiliates = new Affiliates();
    }

    // Métodos de acceso (getters y setters)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(int availableFunds) {
        this.availableFunds = availableFunds;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public Invoices getInvoices() {
        return invoices;
    }

    public void setInvoices(Invoices invoices) {
        this.invoices = invoices;
    }

    // Método unificado para ingresar nombre, ID y suscripción
    public void registerMember(Scanner sc) {
        try {
            // Validar si se ha alcanzado el número máximo de usuarios
            ErrorHandler.checkMaxUsers(userList.size(), MAX_USERS);

            // Pedir nombre y ID
            System.out.println("Enter the user's name:");
            String name = sc.nextLine();
            System.out.println("Enter the user's ID:");
            String id = sc.nextLine();

            // Validar ID duplicado
            ErrorHandler.checkDuplicateID(id, userList);

            // Solicitar y validar fondos disponibles para determinar suscripción
            System.out.println("Enter the subscription amount:");
            int funds = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            ErrorHandler.checkAvailableFunds(funds);  // Verificar fondos

            // Crear nuevo miembro y asignar suscripción
            Member newUser = new Member(name, id);
            newUser.setAvailableFunds(funds);

            if (funds < 100000 && funds <= REGULAR_LIMIT) {
                newUser.setSubscription("REGULAR");
                System.out.println("Subscription set to REGULAR.");
            } else if (funds >= 100000 && funds <= VIP_LIMIT) {
                ErrorHandler.checkVIPLimit(vipCount, MAX_VIP);  // Verificar límite VIP
                newUser.setSubscription("VIP");
                vipCount++;
                System.out.println("Subscription set to VIP.");
            } else {
                System.out.println("Amount exceeds the VIP limit of 5,000,000. Please enter a valid amount.");
                return;
            }

            // Agregar el usuario a la lista
            userList.add(newUser);
            System.out.println("User added: " + name);
            newUser.addFunds(sc);

        } catch (ErrorHandler.MaxUsersException | ErrorHandler.DuplicateIDException | ErrorHandler.MaxVIPMembersException | ErrorHandler.InsufficientFundsException e) {
            // Capturar y mostrar cualquier error
            System.out.println(e.getMessage());
        }
    }
    public void checkInformation(Scanner sc) {
        boolean continueEditing = true;
        Affiliates affiliates = new Affiliates();

        while (continueEditing) {
            System.out.println("\n=== Edit Member Information ===");
            System.out.println("1. Change Name");
            System.out.println("2. Add Affiliates");
            System.out.println("3. Add Funds");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            int option = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    System.out.println("Enter new name:");
                    String newName = sc.nextLine();
                    this.name = newName; // Cambiar el nombre
                    System.out.println("Name updated to: " + this.name);
                    break;
                case 2:
                    affiliates.listOfPeople(sc); // Agregar más afiliados
                    break;
                case 3:
                    addFunds(sc); // Agregar más fondos
                    break;
                case 0:
                    continueEditing = false; // Salir del bucle
                    break;
                default:
                    System.out.println("INVALID OPTION, PLEASE TRY AGAIN.");
                    break;
            }
        }
    }

    // Mostrar usuarios junto con sus facturas pendientes
    @Override
    public void showUsers() {
        System.out.println("List of Users:");
        for (Member member : userList) {
            System.out.println("Name: " + member.getName() + ", ID: " + member.getId() +
                    ", Subscription: " + member.getSubscription() +
                    ", Pending Invoices: " + member.getInvoices().getPendingInvoices() +
                    ", Available Funds: " + member.getAvailableFunds());
        }
    }

<<<<<<< HEAD
    
=======
    // Método para manejar adición de más fondos
    public void addFunds(Scanner sc) {
        System.out.println("Would you like to add new funds? (yes/no)");
        String response = sc.next();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new amount to add: ");
            int newFunds = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            try {
                // Validar límites según la suscripción
                if (this.subscription.equals("REGULAR")) {
                    ErrorHandler.checkFundsLimit(this.availableFunds + newFunds, REGULAR_LIMIT, "REGULAR");
                } else if (this.subscription.equals("VIP")) {
                    ErrorHandler.checkFundsLimit(this.availableFunds + newFunds, VIP_LIMIT, "VIP");
                }

                // Si todo es válido, agregar los nuevos fondos
                this.availableFunds += newFunds;
                System.out.println("Funds added. Total funds: $" + this.availableFunds);

            } catch (ErrorHandler.LimitExceededException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("No additional funds added.");
        }
    }
>>>>>>> origin/code-update
    @Override
    public boolean removeMember(Scanner sc, String id) {
        // Imprimir mensaje para pedir el ID del socio
        System.out.println("Enter the ID of the member to remove: ");
        String remove = sc.nextLine();  // Leer el ID proporcionado por el usuario

        Member memberToRemove = null;
        // Buscar al miembro por su ID
        for (Member member : userList) {
            if (member.getId().equals(remove)) {  // Comparar con el ID ingresado
                memberToRemove = member;
                break;
            }
        }

        // Manejo de error: si el socio no existe
        if (memberToRemove == null) {
            System.out.println("Error: Member with ID " + remove + " does not exist.");
            return false;
        }
        try {
            // Usar el ErrorHandler para validar condiciones
            ErrorHandler.checkVIPStatus(memberToRemove); // No puede ser VIP
            ErrorHandler.checkPendingInvoices(memberToRemove); // No puede tener facturas pendientes
            ErrorHandler.checkMultipleAssociates(memberToRemove); // No puede tener más de un autorizado

            // Si pasa todas las validaciones, eliminar el miembro
            userList.remove(memberToRemove);
            System.out.println("Member with ID " + remove + " has been successfully removed.");
            return true;

        } catch (ErrorHandler.VIPMemberException | ErrorHandler.PendingInvoicesException e) {
            // Capturar cualquier error y mostrar el mensaje de error
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean removeAffiliate(Scanner sc, int pendingInvoices, HashSet<String> affiliatedNames) {
        System.out.print("Enter the name of the affiliate to remove: ");
        String nameToRemove = sc.nextLine().trim();

        // Verificar si hay facturas pendientes
        if (pendingInvoices != 0) {
            System.out.println("Cannot remove " + nameToRemove + ", there are pending invoices.");
            return false;
        }

        // Verificar si el afiliado existe
        if (affiliatedNames.contains(nameToRemove)) {
            affiliatedNames.remove(nameToRemove);
            System.out.println("The affiliate '" + nameToRemove + "' was successfully removed.");
            return true;
        } else {
            System.out.println("The affiliate '" + nameToRemove + "' was not found in the list.");
            return false;
        }
    }

    // Método que combina la eliminación de miembros y afiliados
    public void removeMemberOrAffiliate(Scanner sc, Invoices invoice) {
        System.out.print("Do you want to remove a Member or an Affiliate? (M/A): ");
        String choice = sc.nextLine().trim().toUpperCase();

        if (choice.equals("M")) {
            this.removeMember(sc, null); // Llama al método de eliminación de miembro
        } else if (choice.equals("A")) {
            // Llama al método de eliminación de afiliado
            removeAffiliate(sc, invoice.getPendingInvoices(), namesOfAssociates);
        } else {
            System.out.println("Invalid choice. Please enter 'M' for Member or 'A' for Affiliate.");
        }
    }
}


