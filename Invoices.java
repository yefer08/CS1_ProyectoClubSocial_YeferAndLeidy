import java.util.Scanner;

public class Invoices {
    private int invoiceAmount;
    private boolean isPaid;
    private int pendingInvoices;
    private static int[] costsArray = new int[20];
    private int iterate = 0;

    public Invoices(int invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
        this.isPaid = false;
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

    // Registro de costos
    public void fullcosts(Scanner sc) {
        if (iterate >= costsArray.length) {
            System.out.println("You have reached the limit of 20 expenses. No more expenses can be added.");
            return;
        }

        System.out.println("Enter the cost of incurred expenses: ");
        int costs = sc.nextInt();
        sc.nextLine();

        costsArray[iterate] = costs;
        pendingInvoices += costs;
        iterate++;

        System.out.println("Expense added. Current pending invoices: " + pendingInvoices);
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

    // Verificar facturas pendientes
}