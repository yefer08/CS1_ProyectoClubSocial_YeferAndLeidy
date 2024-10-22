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
    private static final int REGULAR_LIMIT = 1000000; // Límite máximo para Regular
    private static final int VIP_LIMIT = 5000000; // Límite máximo para VIP
    private static int vipCount = 0; // Contador de usuarios VIP
    private static int[] costsArray = new int[20];
    private int iterate = 0;

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

    
    public Member(String name, String id) {
        super();
        this.namesOfAssociates = new HashSet<>();
        this.name = name;
        this.id = id;
        this.availableFunds = 0;
        this.invoices = new Invoices(0); 
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

    public void setNamesOfAssociates(HashSet<String> namesOfAssociates) {
        this.namesOfAssociates = namesOfAssociates;
    }

    public HashSet<String> getNamesOfAssociates() {
        return namesOfAssociates; 
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
    
            // Registrar los afiliados para el nuevo miembro
            System.out.println("Do you want to add affiliates for this member? (yes/no)");
            String response = sc.nextLine().trim().toLowerCase();
    
            if (response.equals("yes")) {
                // Agregar afiliados utilizando el método listOfPeople
                Affiliates affiliates = new Affiliates();
                affiliates.listOfPeople(sc, newUser);
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
            sc.nextLine(); 
    
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
                    memberToEdit.addFunds(sc); // Agregar más fondos al miembro
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

    public boolean removeAffiliate(Scanner sc, int pendingInvoices, HashSet<String> namesOfAssociates2) {
        // Pedir el ID del miembro cuyo afiliado se desea eliminar
        System.out.print("Enter the ID of the member to remove an affiliate from: ");
        String memberId = sc.nextLine().trim();
    
        // Verificar si el miembro existe en la lista de usuarios
        Member member = null;
        for (Member m : userList) {
            if (m.getId().equals(memberId)) {
                member = m;
                break;
            }
        }
    
        if (member == null) {
            System.out.println("No member found with ID " + memberId + ".");
            return false;
        }
    
        // Verificar si hay facturas pendientes
        if (pendingInvoices != 0) {
            System.out.println("Cannot remove affiliate from member " + memberId + ", there are pending invoices.");
            return false;
        }
    
        // Pedir el nombre del afiliado a eliminar
        System.out.print("Enter the name of the affiliate to remove: ");
        String nameToRemove = sc.nextLine().trim();
    
        // Verificar si el afiliado existe en la lista de afiliados del miembro
        if (member.getNamesOfAssociates().contains(nameToRemove)) {
            member.getNamesOfAssociates().remove(nameToRemove);
            System.out.println("The affiliate '" + nameToRemove + "' was successfully removed from member " + memberId + ".");
            return true;
        } else {
            System.out.println("The affiliate '" + nameToRemove + "' was not found in the list of member " + memberId + ".");
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

    public void showAffiliatesInfo(Scanner sc) {
        // Pedir el ID del miembro
        System.out.print("Enter the ID of the member to view their affiliates: ");
        String idToView = sc.nextLine().trim(); // Leer el ID proporcionado por el usuario
    
        // Buscar al miembro en la lista por su ID
        Member memberToView = null;
        for (Member member : userList) {
            if (member.getId().equals(idToView)) {
                memberToView = member;
                break;
            }
        }
    
        // Manejo de error: si el socio no existe
        if (memberToView == null) {
            System.out.println("Member with ID " + idToView + " not found.");
            return;
        }
    
        // Mostrar la información de los afiliados
        HashSet<String> affiliates = memberToView.getNamesOfAssociates(); // Obtener los nombres de los afiliados
    
        System.out.println("=== Affiliates for Member: " + memberToView.getName() + " ===");
        if (affiliates.isEmpty()) {
            System.out.println("No affiliates registered for this member.");
        } else {
            for (String affiliate : affiliates) {
                System.out.println("Affiliate: " + affiliate);
            }
        }
    }
}