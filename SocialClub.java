import java.util.Scanner;

public abstract class SocialClub {
    protected String name;
    protected String id;
    protected int availableFunds;
    protected String subscription;

    // Constructor
    public SocialClub() {
        this.availableFunds = 0;
        this.subscription = "";
    }

    // Métodos abstractos que deben ser implementados por las subclases
    public abstract void showUsers(); // Mostrar los usuarios registrados

    public abstract void registerMember(Scanner sc);
    
    public abstract boolean removeMember(Scanner sc,String id);

    // Método para establecer fondos disponibles (se puede usar en subclases)
    public void setAvailableFunds(int amount) {
        if (amount < 0) {
            System.out.println("El monto no puede ser negativo.");
            return;
        }
        this.availableFunds = amount;
        System.out.println("Fondos disponibles actualizados a: " + this.availableFunds);
    }

    public int getAvailableFunds() {
        return availableFunds;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }


}

