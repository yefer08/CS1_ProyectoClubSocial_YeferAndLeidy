public class Invoice {
    private String id; // ID de la factura
    private int amount; // Monto de la factura
    private String date; // Fecha de la factura

    public Invoice(String id, int amount, String date, int cost) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
