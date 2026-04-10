import java.util.*;
import java.util.stream.Collectors;

// Custom Exception for UC14
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

public class TrainConsistManagementApp {

    public static class Bogie {
        public String name;
        public int capacity;

        // Constructor with Fail-Fast Validation (UC14)
        public Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.name = name;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println(" UC13 & UC14 - Performance & Exceptions ");
        System.out.println("==========================================\n");

        List<Bogie> bogies = new ArrayList<>();

        try {
            // Adding valid bogies
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 56));
            bogies.add(new Bogie("First Class", 24));
            bogies.add(new Bogie("General", 90));

            // Performance Benchmark (UC13)
            System.out.println("--- UC13: Performance Comparison ---");
            long startStream = System.nanoTime();
            List<Bogie> filtered = bogies.stream()
                    .filter(b -> b.capacity > 60)
                    .collect(Collectors.toList());
            long endStream = System.nanoTime();

            System.out.println("Stream Filter Duration: " + (endStream - startStream) + " ns");
            System.out.println("Filtered Count: " + filtered.size());

            // Custom Exception Trigger (UC14)
            System.out.println("\n--- UC14: Exception Handling ---");
            System.out.println("Attempting to add a bogie with -10 capacity...");
            bogies.add(new Bogie("Invalid Bogie", -10));

        } catch (InvalidCapacityException e) {
            System.err.println("Caught Expected Error: " + e.getMessage());
        }

        System.out.println("\nExecution completed successfully.");
    }
}
