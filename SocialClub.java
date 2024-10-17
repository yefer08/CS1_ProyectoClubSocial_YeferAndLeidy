import java.util.Scanner;

public abstract class SocialClub {
    private static final int REGULAR_LIMIT = 1000000;
    private static final int VIP_LIMIT = 5000000;
    protected String name;
    protected String id;
    protected int availableFunds;
    protected String subscription;
    

    // Constructor
    public SocialClub() {
        this.availableFunds = 0;
        this.subscription = "";
    }

<<<<<<< HEAD
=======
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
>>>>>>> origin/code-update

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


    public void removeMemberOrAffiliate(Scanner sc, Invoices invoice) {
        System.out.print("Do you want to remove a Member or an Affiliate? (M/A): ");
        String choice = sc.nextLine().trim().toUpperCase();

        if (choice.equals("M")) {
            this.removeMember(sc, null); // Llama al método de eliminación de miembro
        } else if (choice.equals("A")) {
            if (this instanceof Affiliates) {
                ((Affiliates) this).removeAuthorizedPersons(sc, invoice.getPendingInvoices());
            } else {
                System.out.println("Invalid choice. Please enter 'M' for Member or 'A' for Affiliate.");
            }
        } else {
            System.out.println("Invalid choice. Please enter 'M' for Member or 'A' for Affiliate.");
        }
    }


     // Método para agregar fondos
    public void addFunds(Scanner sc) {
        System.out.println("Would you like to add new funds? (yes/no)");
        String response = sc.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new amount to add: ");
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
                this.availableFunds += newFunds; // Cambia 'funds' a 'newFunds'
                System.out.println("Funds added. Total funds: $" + this.availableFunds);

            } catch (ErrorHandler.LimitExceededException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("No additional funds added.");
        }
    }

}

