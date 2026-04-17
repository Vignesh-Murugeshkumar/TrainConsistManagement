import java.util.Scanner;

public class TrainConsistManagementApp {

    // ---- CUSTOM RUNTIME EXCEPTION ----
    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) {
            super(message);
        }
    }

    // ---- GOODS BOGIE MODEL ----
    static class GoodsBogie {
        private String bogieShape;
        private String cargoType;

        public GoodsBogie(String bogieShape, String cargoType) {
            // Safety validation: Petroleum cannot be loaded in Rectangular bogie
            if (bogieShape.equalsIgnoreCase("Rectangular") &&
                    cargoType.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException(
                        "Unsafe cargo assignment: Petroleum cannot be loaded in Rectangular bogie"
                );
            }
            this.bogieShape = bogieShape;
            this.cargoType = cargoType;
        }

        public String getCargoType() {
            return cargoType;
        }

        public void displayDetails() {
            System.out.println("Assigned Cargo: " + cargoType + " -> " + bogieShape);
        }
    }

    // ---- MAIN METHOD ----
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("UC15 - Safe Cargo Assignment Using try-catch-finally");
        System.out.println("----------------------------------------------------");

        try {
            System.out.print("Enter Bogie Shape (Rectangular / Cylindrical): ");
            String shape = scanner.nextLine();

            System.out.print("Enter Cargo Type (Coal / Cement / Petroleum): ");
            String cargo = scanner.nextLine();

            GoodsBogie bogie = new GoodsBogie(shape, cargo);
            bogie.displayDetails();

        } catch (CargoSafetyException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("\nUC15 cargo assignment handling completed ...");
        }

        scanner.close();
    }
}