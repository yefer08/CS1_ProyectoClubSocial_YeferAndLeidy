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
    private static final int MAX_USERS = 35; // Límite de usuarios totales
    private static final int MAX_VIP = 3; // Límite de usuarios VIP
    private static final int Tvip = 5000000; // Límite de fondos para VIP
    private static int vipCount = 0; // Contador de usuarios VIP
    
    private String name;
    private String id;
    private int availableFunds;
    private String subscription;
    private Invoices invoices; // Instancia de la clase Invoices para manejar facturas
    protected HashSet<String> namesOfAssociates;
    private static ArrayList<Member> userList = new ArrayList<>(); // Lista de usuarios estática

    public Member() {
        this.namesOfAssociates = new HashSet<>();
        this.availableFunds = 0;
        this.invoices = new Invoices(0); // Inicializamos la factura del usuario
    }

    public Member(String name, String id) {
        this.namesOfAssociates = new HashSet<>();
        this.name = name;
        this.id = id;
        this.availableFunds = 0;
        this.invoices = new Invoices(0); // Inicializamos la factura del usuario
    }

    // Getters y Setters
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

    // Método para ingresar nombre e ID
    public void enterNameAndId(Scanner sc) {
        if (userList.size() >= MAX_USERS) {
            System.out.println("The maximum number of users (35) has been reached. Cannot add more users.");
            return;
        }

        System.out.println("Enter the user's name:");
        String name = sc.nextLine();
        System.out.println("Enter the user's ID:");
        String id = sc.nextLine();

        // Validar ID único
        for (Member member : userList) {
            if (member.getId().equals(id)) {
                System.out.println("The ID already exists, please enter another.");
                return;
            }
        }

        Member newUser = new Member(name, id);
        userList.add(newUser);
        System.out.println("User added: " + name);
    }

    // Mostrar los usuarios junto con sus facturas pendientes
    @Override
    public void showUsers() {
        System.out.println("List of Users:");
        for (Member member : userList) {
            System.out.println("Name: " + member.getName() + ", ID: " + member.getId() + ", Subscription: " + member.getSubscription() +
            ", Pending Invoices: " + member.getInvoices().getPendingInvoices());
        }
    }

    // Gestión de fondos y suscripción
    @Override
    public void availableFunds(Scanner sc) {
        System.out.println("ENTER SUBSCRIPTION AMOUNT: ");
        this.availableFunds = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        if (this.availableFunds < 50000) {
            System.out.println("--NO FUNDS--");
            return;
        }

        if (this.availableFunds < Tvip && vipCount < MAX_VIP) {
            this.subscription = "VIP";
            vipCount++; // Aumenta el contador de VIP
            System.out.println("--VIP SUBSCRIPTION--");
        } else if (vipCount >= MAX_VIP) {
            System.out.println("Cannot add more VIP members. The limit of 3 VIP members has been reached.");
            return;
        }

        handleFunds(sc);
    }

    private void handleFunds(Scanner sc) {
        System.out.println("Would you like to add new funds? (yes/no)");
        String response = sc.next();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new amount to add: ");
            int newFunds = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            this.availableFunds += newFunds;
            System.out.println("Funds added. Total funds: " + this.availableFunds);
        } else {
            System.out.println("No additional funds added.");
        }
    }

}
