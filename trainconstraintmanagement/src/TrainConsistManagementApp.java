import java.util.*;
import java.util.regex.*;

public class TrainConsistManagementApp {

    // Simple Bogie model (if needed for consistency with earlier UCs)
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println(" UC11 - Validate Train ID and Cargo Code ");
        System.out.println("==================================");

        Scanner scanner = new Scanner(System.in);

        // Accept input
        System.out.print("Enter Train ID (Format: TRN-1234): ");
        String trainId = scanner.nextLine();

        System.out.print("Enter Cargo Code (Format: PET-AB): ");
        String cargoCode = scanner.nextLine();

        // ---- DEFINE REGEX RULES ----
        Pattern trainIdPattern = Pattern.compile("^TRN-\\d{4}$");
        Pattern cargoCodePattern = Pattern.compile("^[A-Z]{3}-[A-Z]{2}$");

        boolean trainIdValid = trainIdPattern.matcher(trainId).matches();
        boolean cargoCodeValid = cargoCodePattern.matcher(cargoCode).matches();

        // Display validation results
        System.out.println("\nValidation Results:");
        System.out.println("Train ID Valid: " + trainIdValid);
        System.out.println("Cargo Code Valid: " + cargoCodeValid);

        System.out.println("\nUC11 validation completed ...");
    }
}