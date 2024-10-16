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
    private static ArrayList<Member> userList = new ArrayList<>(); // Lista estática de usuarios

    // Constructor por defecto
    public Member() {
        super();
        this.namesOfAssociates = new HashSet<>();
        this.availableFunds = 0;
        this.invoices = new Invoices(); // Inicializar facturas
        this.subscription = "";
    }

    // Constructor que recibe nombre y ID
    public Member(String name, String id) {
        super();
        this.namesOfAssociates = new HashSet<>();
        this.name = name;
        this.id = id;
        this.availableFunds = 0;
        this.invoices = new Invoices(); // Inicializar las facturas
        this.subscription = "";
    }
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

        } catch (ErrorHandler.MaxUsersException | ErrorHandler.DuplicateIDException | ErrorHandler.MaxVIPMembersException | ErrorHandler.InsufficientFundsException e) {
            // Capturar y mostrar cualquier error
            System.out.println(e.getMessage());
        }
    }

    // Mostrar usuarios junto con sus facturas pendientes
    @Override
    public void showUsers() {
        if (userList.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            System.out.println("List of Users:");
            for (Member member : userList) {
                System.out.println("Name: " + member.getName() + ", ID: " + member.getId() +
                        ", Subscription: " + member.getSubscription() +
                        ", Available Funds: $" + member.getAvailableFunds() +
                        ", Pending Invoices: $" + member.getInvoices().getPendingInvoices());
            }
        }
    }

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

    // Método para eliminar un miembro basado en su ID
    @Override
    public boolean removeMember(Scanner sc, String id) {
        System.out.println("Enter the ID of the member to remove:");
        String remove = sc.nextLine();

        Member memberToRemove = null;

        // Buscar al miembro por su ID
        for (Member member : userList) {
            if (member.getId().equals(remove)) {
                memberToRemove = member;
                break;
            }
        }

        if (memberToRemove == null) {
            System.out.println("Error: Member with ID " + remove + " does not exist.");
            return false;
        }

        try {
            // Validar condiciones antes de eliminar el miembro
            ErrorHandler.checkVIPStatus(memberToRemove);
            ErrorHandler.checkPendingInvoices(memberToRemove);
            ErrorHandler.checkMultipleAssociates(memberToRemove);

            // Si pasa todas las validaciones, eliminar al miembro
            userList.remove(memberToRemove);
            System.out.println("Member with ID " + remove + " has been successfully removed.");
            return true;

        } catch (ErrorHandler.VIPMemberException | ErrorHandler.PendingInvoicesException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}



