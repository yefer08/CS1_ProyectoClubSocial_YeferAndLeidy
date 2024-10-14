import java.util.ArrayList;
public class ErrorHandler {

    // Excepción personalizada para manejo de ID duplicado
    public static class DuplicateIDException extends Exception {
        public DuplicateIDException(String message) {
            super(message);
        }
    }

    // Excepción personalizada para manejar el exceso de límites de fondos
    public static class LimitExceededException extends Exception {
        public LimitExceededException(String message) {
            super(message);
        }
    }
    // Excepción para manejo de límites de usuarios
    public static class MaxUsersException extends Exception {
        public MaxUsersException(String message) {
            super(message);
        }
    }
    // Excepción para manejo de fondos insuficientes
    public static class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
    // Excepción para manejo de límites de VIP
    public static class MaxVIPMembersException extends Exception {
        public MaxVIPMembersException(String message) {
            super(message);
        }
    }




    // Método para manejar errores de ID duplicado
    public static void checkDuplicateID(String id, ArrayList<Member> userList) throws DuplicateIDException {
        for (Member member : userList) {
            if (member.getId().equals(id)) {
                throw new DuplicateIDException("El ID '" + id + "' ya existe. Por favor, ingresa otro.");
            }
        }
    }

     // Método para validar si se ha alcanzado el número máximo de usuarios
     public static void checkMaxUsers(int currentUsers, int maxUsers) throws MaxUsersException {
        if (currentUsers >= maxUsers) {
            throw new MaxUsersException("The maximum number of users (" + maxUsers + ") has been reached. Cannot add more users.");
        }
    }

    // Método para validar si los fondos son suficientes para cualquier suscripción
    public static void checkAvailableFunds(int funds) throws InsufficientFundsException {
        if (funds < 50000) {
            throw new InsufficientFundsException("--NO FUNDS--");
        }
    }
    public static void checkVIPLimit(int currentVIPCount, int maxVIPCount) throws Exception {
        if (currentVIPCount >= maxVIPCount) {
            throw new Exception("Cannot add more VIP members. The limit of " + maxVIPCount + " VIP members has been reached.");
        }
    }

    // Método para manejar el límite de usuarios VIP
    

    // Método para manejar el límite de fondos de una suscripción
    public static void checkFundsLimit(int availableFunds, int maxLimit, String subscriptionType) throws LimitExceededException {
        if (availableFunds > maxLimit) {
            throw new LimitExceededException("El monto excede el límite permitido para la suscripción " + subscriptionType + ". Límite: " + maxLimit);
        }
    }

    // Método para manejar errores de tipo de entrada (InputMismatch)
    public static void handleInputMismatchException() {
        System.out.println("Entrada inválida. Por favor, ingresa un valor numérico.");
    }

    public static void checkPendingInvoices(Member member) throws Exception {
        if (member.getInvoices().getPendingInvoices() > 0) {
            throw new Exception("Error: Cannot remove members with pending invoices.");
        }
    }

    // Método para validar si un miembro es VIP
    public static void checkVIPStatus(Member member) throws Exception {
        if (member.getSubscription().equalsIgnoreCase("VIP")) {
            throw new Exception("Error: Cannot remove VIP members.");
        }
    }

    // Método para validar si un miembro tiene más de un autorizado
    public static void checkMultipleAssociates(Member member) throws Exception {
        if (member.namesOfAssociates.size() > 1) {
            throw new Exception("Error: Cannot remove members with more than one authorized associate.");
        }
    }
    
}