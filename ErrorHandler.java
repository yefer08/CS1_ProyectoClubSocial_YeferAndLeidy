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

    // Excepción para manejo de miembros VIP
    public static class VIPMemberException extends Exception {
        public VIPMemberException(String message) {
            super(message);
        }
    }

    // Excepción para manejar facturas pendientes
    public static class PendingInvoicesException extends Exception {
        public PendingInvoicesException(String message) {
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
            throw new MaxUsersException("El número máximo de usuarios (" + maxUsers + ") ha sido alcanzado. No se pueden agregar más usuarios.");
        }
    }

    // Método para validar si los fondos son suficientes para cualquier suscripción
    public static void checkAvailableFunds(int funds) throws InsufficientFundsException {
        if (funds < 50000) {
            throw new InsufficientFundsException("Los fondos son insuficientes. Debes ingresar al menos $50,000.");
        }
    }

    // Método para validar si se ha alcanzado el límite de VIPs
    public static void checkVIPLimit(int currentVIPCount, int maxVIPCount) throws MaxVIPMembersException {
        if (currentVIPCount >= maxVIPCount) {
            throw new MaxVIPMembersException("No se pueden agregar más miembros VIP. Se ha alcanzado el límite de " + maxVIPCount + " miembros VIP.");
        }
    }

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

    // Método para verificar si hay facturas pendientes de un miembro
    public static void checkPendingInvoices(Member member) throws PendingInvoicesException {
        if (member.getInvoices().getPendingInvoices() > 0) {
            throw new PendingInvoicesException("Error: No se pueden eliminar miembros con facturas pendientes.");
        }
    }

    // Método para validar si un miembro es VIP
    public static void checkVIPStatus(Member member) throws VIPMemberException {
        if (member.getSubscription().equalsIgnoreCase("VIP")) {
            throw new VIPMemberException("Error: No se pueden eliminar miembros VIP.");
        }
    }

    // Método para validar si un miembro tiene más de un autorizado
    public static void checkMultipleAssociates(Member member) throws PendingInvoicesException {
        if (member.namesOfAssociates.size() > 1) {
            throw new PendingInvoicesException("Error: No se pueden eliminar miembros con más de un autorizado.");
        }
    }
}

