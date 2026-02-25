package ConsoleApp;
import java.util.Scanner;
import Module1.Module1Manager;
import Module2.Module2Manager;
import Module3.Module3Manager;

public class ConsoleApp {
    public ConsoleApp() {
        while (true) {
            try {
                while (true) { 
                    Scanner sc = new Scanner(System.in);
                    System.out.println("\n------------------------------------------------------------------");
                    System.out.println("---------------------- CONSOLE APPLICATION -----------------------"); 
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("\t1. Smart City Route Planner"); 
                    System.out.println("\t2. Data Sorter – Sorting Algorithm Comparison Tool"); 
                    System.out.println("\t3. Algorithm Performance Analyzer (Time Complexity)"); 
                    System.out.println("\t4. Exit"); 
                    System.out.println("");
                    System.out.print("\tChoose a module: "); 
                    int choice = sc.nextInt();
                    sc.nextLine();
                    
                    switch (choice) { 
                        case 1: 
                            new Module1Manager();
                            break; 
                        case 2: 
                            new Module2Manager();
                            break; 
                        case 3: 
                            new Module3Manager();
                            break; 
                        case 4: 
                            System.out.println("\tExiting application. Goodbye!"); 
                            return; 
                        default: 
                            System.out.println("\tInvalid choice. Please try again."); 
                    } 
                }
                
            } catch (Exception e) {
                System.out.println("\tInvalid user input");
                System.out.println(e.getLocalizedMessage());
                System.out.println(e.getMessage());
            }   
        }
    }
}
