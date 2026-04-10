import java.util.*;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {

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
        System.out.println(" UC9 - Group Bogies by Type ");
        System.out.println("==================================");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));
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
