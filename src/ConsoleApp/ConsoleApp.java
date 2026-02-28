package ConsoleApp;
import java.util.Scanner;
import Module1.Module1Console;
import Module2.Module2Manager;
import Module3.Module3Manager;

public class ConsoleApp {
    public static final String RESET = "\u001B[0m"; 
    public static final String BLACK = "\u001B[30m"; 
    public static final String RED = "\u001B[31m"; 
    public static final String GREEN = "\u001B[32m"; 
    public static final String YELLOW = "\u001B[33m"; 
    public static final String BLUE = "\u001B[34m"; 
    public static final String PURPLE = "\u001B[35m"; 
    public static final String CYAN = "\u001B[36m"; 
    public static final String WHITE = "\u001B[37m";

    public ConsoleApp() {
        Scanner sc = new Scanner(System.in);
        int choice;        

        while (true) {
            try {
                System.out.println(YELLOW + "\n------------------------------------------------------------------");
                System.out.println("---------------------- CONSOLE APPLICATION -----------------------"); 
                System.out.println("------------------------------------------------------------------" + RESET);
                System.out.println("");
                System.out.println("\t1. Smart City Route Planner"); 
                System.out.println("\t2. Data Sorter – Sorting Algorithm Comparison Tool"); 
                System.out.println("\t3. Algorithm Performance Analyzer (Time Complexity)"); 
                System.out.println("\t4. Exit"); 
                System.out.println("");
                System.out.print(YELLOW + "\tChoose A Module: " + RESET); 

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) { 
                    case 1: 
                        new Module1Console(sc);
                        break; 
                    case 2: 
                        new Module2Manager();
                        break; 
                    case 3: 
                        new Module3Manager();
                        break; 
                    case 4: 
                        System.out.println("\tExiting From The Application. Goodbye!"); 
                        sc.close();
                        return; 
                    default: 
                        System.out.println(RED + "\tInvalid Choice. Please Try Again." + RESET); 
                } 
                
            } catch (Exception e) {
                System.out.println(RED + "\tInvalid User Input" + RESET);
                sc.nextLine(); // removing hte invalid input characters
            }   
        }
    }
}
