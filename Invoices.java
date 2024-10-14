import java.util.Scanner;

public class Invoices {
    private int invoiceAmount; // Monto de la factura
    private boolean isPaid; // Estado de pago
    private int pendingInvoices; // Facturas pendientes
    private static int[] costsArray = new int[20]; // Arreglo para almacenar costos
    private int iterate = 0; // Contador para el número de gastos registrados
    private int availableFunds;

    public Invoices(int invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
        this.isPaid = false;
        this.pendingInvoices = 0;
    }

    // Getters y Setters
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

    // Registro de costos
    public void fullCosts(Scanner sc) {
        while (true) { // Bucle para ingresar múltiples costos
            if (iterate >= costsArray.length) {
                System.out.println("You have reached the limit of 20 expenses. No more expenses can be added.");
                return;
            }
    
            System.out.println("Enter the cost of incurred expenses (or type 'stop' to finish): ");
            
            String input = sc.nextLine(); // Leer entrada como texto
    
            // Verificar si el usuario desea detenerse
            if (input.equalsIgnoreCase("stop")) {
                System.out.println("Expense entry finished.");
                break;
            }
    
            // Intentar convertir la entrada en un número
            int costs;
            try {
                costs = Integer.parseInt(input); // Convertir la entrada a número
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'stop' to finish.");
                continue; // Pedir un nuevo valor
            }
    
            // Validar que el costo sea positivo
            if (costs <= 0) {
                System.out.println("The cost must be a positive value.");
                continue; // Pedir un nuevo valor
            }
    
            costsArray[iterate] = costs;
            pendingInvoices += costs; // Actualizar facturas pendientes
            iterate++; // Incrementar contador
    
            System.out.println("Expense added. Current pending invoices: " + pendingInvoices);
        }
    }
    

    // Mostrar gastos
    public void displayExpenses() {
        if (iterate == 0) {
            System.out.println("No expenses registered.");
        } else {
            System.out.println("===== List of Incurred Expenses =====");
            for (int i = 0; i < iterate; i++) {
                System.out.println("Expense " + (i + 1) + ": " + costsArray[i]);
            }
            System.out.println("Total pending invoices: " + pendingInvoices);
        }
    }

    // Pagar facturas
    public void payInvoices(Scanner sc) {
        System.out.println("Ingrese el valor a pagar: ");
        int pay = sc.nextInt();

        // Verifica si el monto ingresado es válido
        if (pay <= 0) {
            System.out.println("El monto a pagar debe ser un valor positivo.");
            return;
            //aplicar manejo de erroes
        }
        // Verifica si el pago es mayor que las facturas pendientes
        if (pay >= pendingInvoices) {
            this.pendingInvoices-=pay;
            // Paga todas las facturas pendientes
            this.availableFunds -= pay; // Deduct payment from available funds
            pendingInvoices = 0; // Establecer facturas pendientes a cero
            isPaid = true; // Establecer estado de pago
            System.out.println("Todas las facturas han sido pagadas.");
        } else {
            // Realiza un pago parcial
            this.availableFunds -= pay; // Deduct payment from available funds
            pendingInvoices -= pay; // Reduce the pending invoices by the payment amount
            System.out.println("Pago parcial realizado. Facturas restantes: " + pendingInvoices);
        }
    }

    
}

