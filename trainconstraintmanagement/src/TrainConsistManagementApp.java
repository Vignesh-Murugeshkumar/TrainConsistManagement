import java.util.*;
import java.util.stream.*;

public class TrainConsistManagementApp {

    // Simple Bogie model
    static class Bogie {
        String name;
        int capacity;
    // Declare as public so the test class can see it
    public static class Bogie {
        public String name;
        public int capacity;

        public Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println(" UC10 - Count Total Seats in Train ");
        System.out.println("==================================");

        // Create List of bogies
        System.out.println(" UC9 - Group Bogies by Type ");
        System.out.println("==================================");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));

        // Display bogies
        System.out.println("\nBogies in Train:");
        for (Bogie b : bogies) {
            System.out.println(b.name + " -> " + b.capacity);
        }

        // ---- AGGREGATE USING REDUCE ----
        int totalCapacity = bogies.stream()
                .map(b -> b.capacity)   // extract capacity
                .reduce(0, Integer::sum); // sum capacities

        // Display result
        System.out.println("\nTotal Seating Capacity of Train: " + totalCapacity);

        System.out.println("\nUC10 aggregation completed ...");
        bogies.add(new Bogie("AC Chair", 60));

        // Group using the functional method
        Map<String, List<Bogie>> grouped = getGroupedBogies(bogies);
        displayGroupedBogies(grouped);

        System.out.println("\nUC9 grouping completed ...");
    }

    // Returning the Map makes this logic testable
    public static Map<String, List<Bogie>> getGroupedBogies(List<Bogie> bogies) {
        return bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));
    }

    public static void displayGroupedBogies(Map<String, List<Bogie>> groupedBogies) {
        System.out.println("\nGrouped Bogies:");
        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println("\nBogie Type: " + entry.getKey());
            for (Bogie b : entry.getValue()) {
                System.out.println("Capacity -> " + b.capacity);
            }
        }
    }
}
