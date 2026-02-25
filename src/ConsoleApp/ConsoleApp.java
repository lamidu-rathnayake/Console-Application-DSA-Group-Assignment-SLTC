package ConsoleApp;
import java.util.Scanner;
import Module1.Module1Manager;
import Module2.Module2Manager;
import Module3.Module3Manager;

public class ConsoleApp {
    public ConsoleApp() {
        Scanner sc = new Scanner(System.in);

        while (true) { 
            System.out.println("\n--- Console Application ---"); 
            System.out.println("1. Module 1"); 
            System.out.println("2. Module 2"); 
            System.out.println("3. Module 3"); 
            System.out.println("4. Exit"); 
            System.out.print("Choose a module: "); 
            int choice = sc.nextInt(); sc.nextLine(); // consume newline 
            
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
                    System.out.println("Exiting application. Goodbye!"); 
                    return; 
                default: 
                    System.out.println("Invalid choice. Please try again."); 
            } 
        }
    }
}
