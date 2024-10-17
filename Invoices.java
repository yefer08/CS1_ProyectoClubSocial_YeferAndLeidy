import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Invoices {
    private int invoiceAmount; // Monto de la factura
    private boolean isPaid; // Estado de pago
    private int pendingInvoices; // Facturas pendientes
    private static int[] costsArray = new int[20]; // Arreglo para almacenar costos
    private int iterate = 0; // Contador para el número de gastos registrados
    private static ArrayList<Member> userList = new ArrayList<>(); // Lista de usuarios
    private List<Invoice> invoiceList; // Lista para almacenar facturas

    Affiliates affiliates = new Affiliates();

    public Invoices(int invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
        this.pendingInvoices = 0;
        this.invoiceList = new ArrayList<>();
    }

    public Invoices() {
        this.pendingInvoices = 0;
        this.invoiceList = new ArrayList<>(); // Asegúrate de inicializar la lista
    }

    public int getPendingInvoices() {
        return this.pendingInvoices;
    }



    // Método para registrar costos y crear facturas
    public void fullCosts(Scanner sc) {
        System.out.println("Ingrese el ID de usuario:");
        String userId = sc.nextLine(); // Leer el ID de usuario

        // Validar que el ID ingresado no esté vacío
        if (userId == null || userId.trim().isEmpty()) {
            System.out.println("El ID ingresado no puede estar vacío.");
            return;
        }

        // Verificar si el ID de usuario existe en la lista 
        Member foundMember = null;
        for (Member member : userList) {
            if (member.getId() != null && member.getId().equals(userId)) {
                foundMember = member;
                break; // Salir del bucle si se encuentra el miembro
            }
        }

        if (foundMember == null) {
            System.out.println("ID ingresado: " + userId);
            System.out.println("El ID de usuario no es válido.");
            return;
        }

        // Asumimos que ya tienes el `Member` encontrado, puedes trabajar con él.
        System.out.println("Registro de gastos para el usuario: " + foundMember.getName() + " (ID: " + foundMember.getId() + ")");

        // Continuar con la lógica de registro de gastos
        while (true) {
            if (iterate >= costsArray.length) {
                try {
                    throw new ErrorHandler.LimitExceededException("Se alcanzó el límite de 20 gastos.");
                } catch (ErrorHandler.LimitExceededException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            System.out.println("Ingrese el costo de los gastos incurridos (o escriba 'stop' para finalizar): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("stop")) {
                System.out.println("Registro de gastos finalizado.");
                break;
            }

            int costs;
            try {
                costs = Integer.parseInt(input); // Convertir la entrada a número
            } catch (NumberFormatException e) {
                ErrorHandler.handleInputMismatchException(); // Llama a la función para manejar el error de entrada
                System.out.println("Error: El valor ingresado no es un número válido.");
                continue; // Pedir un nuevo valor
            }

            if (costs <= 0) {
                System.out.println("El costo debe ser un valor positivo.");
                continue; // Pedir un nuevo valor
            }

            // Crear una nueva factura
            Invoice newInvoice = new Invoice(foundMember.getId(), costs, input, costs); // Asegúrate de que el constructor de Invoice acepte ID y monto
            invoiceList.add(newInvoice); // Añadir la factura a la lista de facturas

            // Registrar el costo en el arreglo
            costsArray[iterate] = costs;
            pendingInvoices += costs; // Actualizar facturas pendientes
            iterate++; // Incrementar contador

            System.out.println("Gasto añadido. Factura registrada con ID: " + newInvoice.getId() + 
                               " por valor: " + costs + ". Total de facturas pendientes: " + pendingInvoices);
        }
    }

    // Método para mostrar las facturas
    public void showInvoices(Scanner sc) {
        System.out.println("Ingrese el ID de usuario:");
        String userId = sc.nextLine(); // Leer el ID de usuario

        // Validar que el ID ingresado no esté vacío
        if (userId == null || userId.trim().isEmpty()) {
            System.out.println("El ID ingresado no puede estar vacío.");
            return;
        }

        // Verificar si el ID de usuario existe en la lista 
        Member foundMember = null;
        for (Member member : userList) {
            if (member.getId() != null && member.getId().equals(userId)) {
                foundMember = member;
                break; // Salir del bucle si se encuentra el miembro
            }
        }

        if (foundMember == null) {
            System.out.println("ID ingresado: " + userId);
            System.out.println("El ID de usuario no es válido.");
            return;
        }

        // Mostrar las facturas del miembro encontrado
        System.out.println("Facturas para el usuario: " + foundMember.getName() + " (ID: " + foundMember.getId() + ")");

        // Comprobar si hay facturas pendientes
        if (invoiceList.isEmpty()) {
            System.out.println("No hay facturas pendientes para este usuario.");
        } else {
            // Obtener la lista de facturas desde la instancia de Invoices
            for (Invoice invoice : invoiceList) {
                System.out.println("Factura ID: " + invoice.getId() + 
                                   ", Monto: " + invoice.getAmount() + 
                                   ", Fecha: " + invoice.getDate());
            }
        }
    }

   

    public void payInvoices(Scanner sc, Member member) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'payInvoices'");
    }

    
}

