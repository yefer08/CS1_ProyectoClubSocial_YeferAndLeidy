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

    // Método para manejar errores de ID duplicado
    public static void checkDuplicateID(String id, ArrayList<Member> userList) throws DuplicateIDException {
        for (Member member : userList) {
            if (member.getId().equals(id)) {
                throw new DuplicateIDException("El ID '" + id + "' ya existe. Por favor, ingresa otro.");
            }
        }
    }

    // Método para manejar el límite de usuarios VIP
    public static void checkVipLimit(int vipCount, int maxVip) throws LimitExceededException {
        if (vipCount >= maxVip) {
            throw new LimitExceededException("Se ha alcanzado el límite máximo de usuarios VIP (" + maxVip + ").");
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
}