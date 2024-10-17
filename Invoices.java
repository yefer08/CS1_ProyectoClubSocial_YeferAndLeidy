import java.util.Scanner;
 
public class Invoices {
    private int invoiceAmount; // Monto de la factura
    private boolean isPaid; // Estado de pago
    private int pendingInvoices; // Facturas pendientes
    private static int[] costsArray = new int[20]; // Arreglo para almacenar costos
    private int iterate = 0; // Contador para el número de gastos registrados
   
    public Invoices(int invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
        this.pendingInvoices = 0;
    }
 
    public Invoices() {
        this.pendingInvoices = 0;
    }
 
    public int getInvoiceAmount() {
        return invoiceAmount;
    }
 
    public void setInvoiceAmount(int invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }
 
    public boolean isPaid() {
        return isPaid;
    }
 
    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
 
    public int getPendingInvoices() {
        return pendingInvoices;
    }
 
    public void setPendingInvoices(int pendingInvoices) {
        this.pendingInvoices = pendingInvoices;
    }
 
    public void registerCosts(Scanner sc, Affiliates affiliates, Member member) {
        System.out.println("¿Eres el titular (t) o un afiliado (a)?");
        String tipo = sc.nextLine().toLowerCase();
       
        if (tipo.equals("t")) {
            fullCosts(sc);
        } else if (tipo.equals("a")) {
            affiliates.addExpense(sc, member);
        } else {
            System.out.println("Opción no válida, por favor elige 't' o 'a'.");
        }
    }
 
    public void fullCosts(Scanner sc) {
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
   
            // Verificar si el usuario desea detenerse
            if (input.equalsIgnoreCase("stop")) {
                System.out.println("Registro de gastos finalizado.");
                break;
            }
   
            // Intentar convertir la entrada en un número
            int costs;
            try {
                costs = Integer.parseInt(input); // Convertir la entrada a número
            } catch (NumberFormatException e) {
                ErrorHandler.handleInputMismatchException(); // Llama a la función para manejar el error de entrada
                continue; // Pedir un nuevo valor
            }
   
            // Validar que el costo sea positivo
            if (costs <= 0) {
                System.out.println("El costo debe ser un valor positivo.");
                continue; // Pedir un nuevo valor
            }
   
            costsArray[iterate] = costs;
            pendingInvoices += costs; // Actualizar facturas pendientes
            iterate++; // Incrementar contador
   
            System.out.println("Gasto añadido. Facturas pendientes actuales: " + pendingInvoices);
        }
    }
   
    // Mostrar gastos
    public void displayExpenses() {
        if (iterate == 0) {
            System.out.println("No hay gastos registrados.");
        } else {
            System.out.println("===== Lista de gastos incurridos =====");
            for (int i = 0; i < iterate; i++) {
                System.out.println("Gasto " + (i + 1) + ": " + costsArray[i]);
            }
            System.out.println("Total de facturas pendientes: " + pendingInvoices);
        }
    }
 
    // Pagar facturas
    public void payInvoices(Scanner sc, Member member) {
        System.out.println("Ingrese el valor a pagar: ");
       
        int pay;
        try {
            pay = sc.nextInt();
        } catch (Exception e) {
            ErrorHandler.handleInputMismatchException(); // Manejo de error de tipo de entrada
            sc.next(); // Limpiar el buffer
            return;
        }
   
        // Verifica si el monto ingresado es válido
        if (pay <= 0) {
            System.out.println("El monto a pagar debe ser un valor positivo.");
            return;
        }
   
        // Verifica si el pago es mayor que las facturas pendientes
        if (pay > pendingInvoices) {
            System.out.println("El monto ingresado excede las facturas pendientes.");
            return;
        }
   
        // Verifica si el miembro tiene suficientes fondos
        if (pay > member.getAvailableFunds()) {
            System.out.println("Fondos disponibles: $" + member.getAvailableFunds());
            System.out.println("Error: Fondos insuficientes para realizar el pago.");
            return;
        }
   
        // Realiza el pago
        if (pay == pendingInvoices) {
            pendingInvoices = 0; // Establecer facturas pendientes a cero
            isPaid = true; // Establecer estado de pago
            System.out.println("Todas las facturas han sido pagadas.");
        } else {
            pendingInvoices -= pay; // Reducir las facturas pendientes por el monto del pago
            System.out.println("Pago parcial realizado. Facturas restantes: " + pendingInvoices);
        }
   
        // Actualiza los fondos disponibles del miembro
        member.setAvailableFunds(member.getAvailableFunds() - pay); // Restar el monto pagado
        System.out.println("Fondos restantes después del pago: $" + member.getAvailableFunds());
    }
}

