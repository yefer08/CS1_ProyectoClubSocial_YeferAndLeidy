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
import java.util.List;
import java.util.Scanner;

public class Member extends SocialClub {
    private static final int MAX_USERS = 35; // Límite de usuarios
    private static final int MAX_VIP = 3; // Límite de usuarios VIP
    private static final int REGULAR_LIMIT = 100000; // Límite máximo para Regular (ajustado de 1,000,000 a 100,000)
    private static final int VIP_LIMIT = 5000000; // Límite máximo para VIP
    private static int vipCount = 0; // Contador de usuarios VIP
    private static ArrayList<Member> userList = new ArrayList<>(); // Lista estática de usuarios

    protected HashSet<String> namesOfAssociates; // Nombres de los asociados
    private Invoices invoices; // Facturas del usuario
    private Affiliates affiliates;
    private int availableFunds; // Agregado para mantener fondos disponibles
    private String name; // Agregado para mantener el nombre
    private String id; // Agregado para mantener el ID
    private String subscription; // Agregado para mantener la suscripción

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
        return id; // Asegúrate de que `id` se inicialice correctamente
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

    public HashSet<String> getNamesOfAssociates() {
        return namesOfAssociates; // Retorna el conjunto de nombres de afiliados
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

            // Asignar la suscripción en función de los fondos
            if (funds < REGULAR_LIMIT) {
                newUser.setSubscription("REGULAR");
                System.out.println("Subscription set to REGULAR.");
            } else if (funds >= REGULAR_LIMIT && funds <= VIP_LIMIT) {
                ErrorHandler.checkVIPLimit(vipCount, MAX_VIP);  // Verificar límite VIP
                newUser.setSubscription("VIP");
                vipCount++;
                System.out.println("Subscription set to VIP.");
            } else {
                System.out.println("Amount exceeds the VIP limit of 5,000,000. Please enter a valid amount.");
                return;
            }

            // Registrar los afiliados para el nuevo miembro
            System.out.println("Do you want to add affiliates for this member? (yes/no)");
            String response = sc.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                // Agregar afiliados utilizando el método listOfPeople
                affiliates.listOfPeople(sc, newUser);
            }

            // Agregar el usuario a la lista
            userList.add(newUser);
            System.out.println("User added: " + name);
            newUser.addFunds(sc); // Asegúrate de que este método esté definido en Member

        } catch (ErrorHandler.MaxUsersException | ErrorHandler.DuplicateIDException | ErrorHandler.MaxVIPMembersException | ErrorHandler.InsufficientFundsException e) {
            // Capturar y mostrar cualquier error
            System.out.println(e.getMessage());
        }
    }

    public void checkInformation(Scanner sc) {
        boolean continueEditing = true;

        System.out.print("Enter the ID of the member you want to edit: ");
        String idToEdit = sc.nextLine(); // Pedir el ID del miembro a editar

        // Buscar al miembro en la lista por su ID
        Member memberToEdit = null;
        for (Member member : userList) {
            if (member.getId().equals(idToEdit)) {
                memberToEdit = member;
                break;
            }
        }

        if (memberToEdit == null) {
            System.out.println("Member with ID " + idToEdit + " not found.");
            return;
        }

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
                    memberToEdit.setName(newName); // Cambiar el nombre del miembro en la lista
                    System.out.println("Name updated to: " + memberToEdit.getName());
                    break;
                case 2:
                    affiliates.listOfPeople(sc, memberToEdit); // Agregar afiliados al miembro específico
                    break;
                case 3:
                    memberToEdit.addFunds(sc); // Asegúrate de que este método esté definido en Member
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
        Scanner scanner = new Scanner(System.in); // Necesitamos importar java.util.Scanner
        System.out.print("Ingrese el ID del miembro que desea consultar: ");
        String inputId = scanner.nextLine(); // Lee el ID ingresado

        // Busca el miembro correspondiente en userList
        Member foundMember = null;
        for (Member member : userList) {
            if (member.getId().equals(inputId)) {
                foundMember = member;
                break; // Salimos del bucle si encontramos al miembro
            }
        }

        // Muestra la información del miembro encontrado o un mensaje de error
        if (foundMember != null) {
            System.out.println("Nombre: " + foundMember.getName() +
                    ", ID: " + foundMember.getId() +
                    ", Suscripción: " + foundMember.getSubscription() +
                    ", Facturas Pendientes: " + foundMember.getInvoices().getPendingInvoices() +
                    ", Fondos Disponibles: " + foundMember.getAvailableFunds());
        } else {
            System.out.println("ID de miembro no válido. No se encontró ningún miembro con ese ID.");
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
            System.out.println("No member found with ID: " + remove);
            return false;
        }

        // Si el miembro se encuentra, eliminarlo de la lista
        userList.remove(memberToRemove);
        System.out.println("Member with ID " + remove + " has been removed successfully.");
        return true;
    }
}
