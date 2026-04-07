import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrainConsistManagementApp {

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println(" UC2 - Add Passenger Bogies to Train ");
        System.out.println("======================================\n");
        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");
        System.out.println("After Adding Bogies:");
        System.out.println("Passenger Bogies : " + passengerBogies + "\n");
        passengerBogies.remove("AC Chair");
        System.out.println("After Removing 'AC Chair':");
        System.out.println("Passenger Bogies : " + passengerBogies + "\n");
        boolean exists = passengerBogies.contains("Sleeper");
        System.out.println("Checking if 'Sleeper' exists:");
        System.out.println("Contains Sleeper? : " + exists + "\n");
        System.out.println("Final Train Passenger Consist:");
        System.out.println(passengerBogies);
        System.out.println("\nUC2 operations completed successfully ...");

        System.out.println("\n======================================");
        System.out.println(" UC4 - Manage Bogie Order using LinkedList ");
        System.out.println("======================================\n");
        LinkedList<String> bogieOrder = new LinkedList<>();
        bogieOrder.add("General");
        bogieOrder.add("Sleeper");
        bogieOrder.add("AC Chair");
        System.out.println("Initial Bogie Order:");
        System.out.println("Bogies : " + bogieOrder + "\n");
        bogieOrder.addFirst("Engine");
        System.out.println("After Adding 'Engine' at First:");
        System.out.println("Bogies : " + bogieOrder + "\n");
        bogieOrder.addLast("Guard");
        System.out.println("After Adding 'Guard' at Last:");
        System.out.println("Bogies : " + bogieOrder + "\n");
        bogieOrder.remove("AC Chair");
        System.out.println("After Removing 'AC Chair':");
        System.out.println("Bogies : " + bogieOrder + "\n");
        System.out.println("First Bogie : " + bogieOrder.getFirst());
        System.out.println("Last Bogie  : " + bogieOrder.getLast());
        System.out.println("\nFinal Train Consist Order:");
        System.out.println(bogieOrder);
        System.out.println("\nUC4 operations completed successfully ...");
    }
}