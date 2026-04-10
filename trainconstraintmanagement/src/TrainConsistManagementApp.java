import java.util.*;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {
    public static class Bogie {
        public String name;
        public int capacity;
        public Bogie(String name, int capacity) { this.name = name; this.capacity = capacity; }
    }

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println(" UC13 - Performance: Loops vs Streams ");
        System.out.println("==========================================\n");

        List<Bogie> bogies = new ArrayList<>();
        for (int i = 0; i < 10000; i++) { // Larger dataset for meaningful measurement
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 56));
        }

    // Goods Bogie model
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
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
        System.out.println(" UC12 - Safety Compliance Check for Goods Bogies ");
        System.out.println("==================================");

        // Create goods bogie List
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal"));

        // Display bogies
        System.out.println("\nGoods Bogies in Train:");
        for (GoodsBogie gb : goodsBogies) {
            System.out.println(gb.type + " -> " + gb.cargo);
        }

        // ---- SAFETY VALIDATION RULE ----
        // Rule: Cylindrical bogies must only carry Petroleum
        boolean complianceStatus = goodsBogies.stream()
                .allMatch(gb -> !(gb.type.equals("Cylindrical") && !gb.cargo.equals("Petroleum")));

        // Display compliance result
        System.out.println("\nSafety Compliance Status: " + complianceStatus);
        if (complianceStatus) {
            System.out.println("Train formation is SAFE.");
        } else {
            System.out.println("Train formation is NOT SAFE.");
        }

        System.out.println("\nUC12 safety validation completed ...");
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

        // 1. Loop-based Processing
        long startTimeLoop = System.nanoTime();
        List<Bogie> loopFiltered = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > 60) {
                loopFiltered.add(b);
            }
        }
        long endTimeLoop = System.nanoTime();
        System.out.println("Loop Filtering Time: " + (endTimeLoop - startTimeLoop) + " ns");

        // 2. Stream-based Processing
        long startTimeStream = System.nanoTime();
        List<Bogie> streamFiltered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long endTimeStream = System.nanoTime();
        System.out.println("Stream Filtering Time: " + (endTimeStream - startTimeStream) + " ns");
    }

    // Helper methods for Testing
    public static List<Bogie> filterWithLoop(List<Bogie> list) {
        List<Bogie> filtered = new ArrayList<>();
        for (Bogie b : list) if (b.capacity > 60) filtered.add(b);
        return filtered;
    }

    public static List<Bogie> filterWithStream(List<Bogie> list) {
        return list.stream().filter(b -> b.capacity > 60).collect(Collectors.toList());
    }
}
