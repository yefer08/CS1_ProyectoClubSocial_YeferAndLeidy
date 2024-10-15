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
        this.invoices = new Invoices(0); // Inicializar facturas
        this.subscription = "";
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

    // Método para ingresar nombre y ID
    public void enterNameAndId(Scanner sc) {
        try {
            // Validar si se ha alcanzado el número máximo de usuarios
            ErrorHandler.checkMaxUsers(userList.size(), MAX_USERS);

            System.out.println("Enter the user's name:");
            String name = sc.nextLine();
            System.out.println("Enter the user's ID:");
            String id = sc.nextLine();

            // Validar ID duplicado
            ErrorHandler.checkDuplicateID(id, userList);

            // Si no hay errores, agregar el nuevo usuario
            Member newUser = new Member(name, id);
            userList.add(newUser);
            System.out.println("User added: " + name);

        } catch (ErrorHandler.MaxUsersException | ErrorHandler.DuplicateIDException e) {
            // Capturar y mostrar cualquier error
            System.out.println(e.getMessage());
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

    // Manejar fondos y suscripción
    @Override
    public void availableFunds(Scanner sc) {
        System.out.println("ENTER SUBSCRIPTION AMOUNT: ");
        int funds = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        try {
            // Verificar fondos disponibles
            ErrorHandler.checkAvailableFunds(funds);

            // Validar suscripción Regular
            System.out.println("Funds entered: " + funds);
            if (funds < 100000 && funds <= REGULAR_LIMIT) {
                this.subscription = "REGULAR";
                System.out.println("Subscription set to: " + this.subscription);
                System.out.println("--REGULAR SUBSCRIPTION--");
            }
            // Validar suscripción VIP
            else if (funds >= 100000 && funds <= VIP_LIMIT) {
                ErrorHandler.checkVIPLimit(vipCount, MAX_VIP);
                this.subscription = "VIP";
                vipCount++; // Incrementar el contador de VIP
                System.out.println("--VIP SUBSCRIPTION--");
            }
            // Excede el límite para cualquier suscripción
            else if (funds > VIP_LIMIT) {
                System.out.println("Amount exceeds the maximum limit for VIP subscription (5,000,000). Please enter a valid amount.");
                return;
            }

            // Manejo de fondos adicionales dentro de los límites
            handleFunds(sc);
        } catch (ErrorHandler.MaxVIPMembersException | ErrorHandler.InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    // Manejar adición de más fondos
    private void handleFunds(Scanner sc) {
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
                }

                if (this.subscription.equals("VIP")) {
                    ErrorHandler.checkFundsLimit(this.availableFunds + newFunds, VIP_LIMIT, "VIP");
                }

                // Si todo es válido, agregar los nuevos fondos
                this.availableFunds += newFunds;
                System.out.println("Funds added. Total funds: " + this.availableFunds);

            } catch (ErrorHandler.LimitExceededException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("No additional funds added.");
        }
    }
    public void subtractFunds(int amount) {
        if (amount > availableFunds) {
            System.out.println("Error: No se pueden restar más fondos de los disponibles.");
            return;
        }
        this.availableFunds -= amount;
        System.out.println("Fondos restantes: $" + this.availableFunds);
    }

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
}

