import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // --- UC2 Logic ---
        System.out.println("======================================");
        System.out.println(" UC2 - Add Passenger Bogies to Train ");
        System.out.println("======================================\n");
        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");
        // ... (remaining UC2 print statements)

        // --- UC3 Logic ---
        System.out.println("\n ======================================= ");
        System.out.println(" UC3 - Track Unique Bogie IDs ");
        System.out.println(" ======================================= \n");
        Set<String> bogies = new HashSet<>();
        bogies.add("BG101");
        // ... (remaining UC3 print statements)
    }
}