import java.util.Scanner;

public class Invoices {
    private int invoiceAmount;
    private boolean isPaid;
    private int pendingInvoices;

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

    public void fullcosts(Scanner sc) {
        System.out.println("Enter the cost of incurred expenses: ");
        int costs = sc.nextInt();
        sc.nextLine(); 
        pendingInvoices += costs;
    }

    public void displayInvoiceStatus() {
        if (isPaid) {
            System.out.println("Invoice of " + invoiceAmount + " is PAID.");
        } else {
            System.out.println("Invoice of " + invoiceAmount + " is PENDING.");
        }
    }

    public void pendingInvoices() {
        if (pendingInvoices == 0) {
            System.out.println("--YOUR ACCOUNT HAS NO PENDING INVOICES--");
        } else {
            System.out.println("Pending invoices: " + pendingInvoices);
        }
    }
}
