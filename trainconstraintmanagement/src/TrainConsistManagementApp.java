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
