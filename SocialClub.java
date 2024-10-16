import java.util.Scanner;

public abstract class SocialClub {
    protected String name;
    protected String id;
    protected int availableFunds;
    protected String subscription;

    // Constructor
    public SocialClub() {
        this.availableFunds = 0;  // Sin fondos iniciales hasta que el usuario los ingrese
        this.subscription = "";
    }

    // Método abstracto para registrar miembros (nuevo método unificado)
    public abstract void registerMember(Scanner sc); // Manejar el registro de nuevos miembros

    // Métodos abstractos que deben ser implementados por las subclases
    public abstract void showUsers();  // Mostrar los usuarios registrados

    public abstract boolean removeMember(Scanner sc, String id);  // Eliminar un miembro por su ID

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



