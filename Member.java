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
    private static final int MAX_USERS = 35; // Limit of total users
    private static final int MAX_VIP = 3; // Limit of VIP users
    private static final int REGULAR_LIMIT = 1000000; // Max funds for Regular
    private static final int VIP_LIMIT = 5000000; // Max funds for VIP
    private static int vipCount = 0; // VIP user counter


    protected HashSet<String> namesOfAssociates;
    private Invoices invoices;
    private static ArrayList<Member> userList = new ArrayList<>(); // Static user list

    public Member() {
        this.namesOfAssociates = new HashSet<>();
        this.availableFunds = 0;
        
    }

    public Member(String name, String id) {
        this.namesOfAssociates = new HashSet<>();
        this.name = name;
        this.id = id;
        this.availableFunds = 0;
        this.invoices = new Invoices(0); // Initialize the user's invoice
    }

    // Getters and Setters
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

    // Method to enter name and ID
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

    // Show users along with their pending invoices
    @Override
    public void showUsers() {
        System.out.println("List of Users:");
        for (Member member : userList) {
            System.out.println("Name: " + member.getName() + ", ID: " + member.getId() + ", Subscription: " + this.subscription +
            ", Pending Invoices: " + member.getInvoices().getPendingInvoices() +  "remaining value: " + this.getAvailableFunds());
        }
    }

    // Manage funds and subscription
    @Override
    public void availableFunds(Scanner sc) {
        System.out.println("ENTER SUBSCRIPTION AMOUNT: ");
        this.availableFunds = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer
    
        // Verificar si el monto es menor que el mínimo requerido para cualquier suscripción
        if (this.availableFunds < 50000) {
            System.out.println("--NO FUNDS--");
            return;
        }
    
        if (this.availableFunds < 100000 &&  this.availableFunds <= REGULAR_LIMIT) {
            if (this.availableFunds == REGULAR_LIMIT) {
                System.out.println("You have reached the maximum limit for REGULAR subscription: 1,000,000");
            }
            this.subscription = "REGULAR";
            System.out.println("--REGULAR SUBSCRIPTION--");
        }
        // Suscripción VIP: monto entre 100,000 y 5,000,000
        else if (this.availableFunds >= 100000 && this.availableFunds <= VIP_LIMIT) {
            try {
                // Validar si se puede añadir más miembros VIP
                ErrorHandler.checkVIPLimit(vipCount, MAX_VIP);
                if (this.availableFunds == VIP_LIMIT) {
                    System.out.println("You have reached the maximum limit for VIP subscription: 5,000,000");
                }
                this.subscription = "VIP";
                vipCount++; // Incrementar el contador de VIP
                System.out.println("--VIP SUBSCRIPTION--");
            } catch (Exception e) {
                // Manejo del error si el límite de VIPs es alcanzado
                System.out.println(e.getMessage());
                return;  // Salir del método si ocurre un error
            }
        }
        // Excede el límite para cualquier suscripción
        else if (this.availableFunds > VIP_LIMIT) {
            System.out.println("Amount exceeds the maximum limit for VIP subscription (5,000,000). Please enter a valid amount.");
            return;
        }
    
        // Manejo de fondos adicionales dentro de los límites
        handleFunds(sc);
    }
    

    
    // Handle adding more funds
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
                    if (this.availableFunds + newFunds > REGULAR_LIMIT) {
                        System.out.println("Cannot exceed $1,000,000 for REGULAR members.");
                        return;
                    }
                }
    
                if (this.subscription.equals("VIP")) {
                    if (this.availableFunds + newFunds > VIP_LIMIT) {
                        System.out.println("Cannot exceed $5,000,000 for VIP members.");
                        return;
                    }
                }
    
                // Si todo es válido, agregar los nuevos fondos
                this.availableFunds += newFunds;
                System.out.println("Funds added. Total funds: " + this.availableFunds);
    
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    
        } else {
            System.out.println("No additional funds added.");
        }
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
    
        } catch (Exception e) {
            // Capturar cualquier error y mostrar el mensaje de error
            System.out.println(e.getMessage());
            return false;
        }
    }
    

}
