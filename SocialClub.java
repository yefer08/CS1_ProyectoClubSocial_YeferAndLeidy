import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class SocialClub {
    private static final int REGULAR_LIMIT = 1000000;
    private static final int VIP_LIMIT = 5000000;

    protected String name;
    protected String id;
    protected int availableFunds;
    protected String subscription;
    protected List<Member> memberList; // Lista de miembros

    // Constructor
    public SocialClub() {
        this.availableFunds = 0;
        this.subscription = "";
        this.memberList = new ArrayList<>(); // Inicializa la lista de miembros
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

    // Métodos abstractos que deben ser implementados por las subclases
    public abstract void showUsers(); // Mostrar los usuarios registrados

    public abstract void registerMember(Scanner sc);

    public abstract boolean removeMember(Scanner sc, String id);

    // Método para establecer fondos disponibles (se puede usar en subclases)
    public void setAvailableFunds(int amount) {
        if (amount < 0) {
            System.out.println("El monto no puede ser negativo.");
            return;
        }
        this.availableFunds = amount;
        System.out.println("Fondos disponibles actualizados a: " + this.availableFunds);
    }

    // Método para agregar fondos
    public void addFunds(Scanner sc) {
        System.out.println("¿Le gustaría agregar nuevos fondos? (yes/no)");
        String response = sc.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Ingrese el nuevo monto a agregar: ");
            int newFunds = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            try {
                // Validar límites según la suscripción
                if (this.subscription.equals("REGULAR")) {
                    ErrorHandler.checkFundsLimit(this.availableFunds + newFunds, REGULAR_LIMIT, "REGULAR");
                } else if (this.subscription.equals("VIP")) {
                    ErrorHandler.checkFundsLimit(this.availableFunds + newFunds, VIP_LIMIT, "VIP");
                }

                // Si todo es válido, agregar los nuevos fondos
                this.availableFunds += newFunds;
                System.out.println("Fondos añadidos. Fondos totales: $" + this.availableFunds);

            } catch (ErrorHandler.LimitExceededException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("No se añadieron fondos adicionales.");
        }
    }
}


