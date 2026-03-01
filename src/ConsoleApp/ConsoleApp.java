package ConsoleApp;

import java.util.Scanner;
import Module1.Module1Console;
import Module2.Module2Console;
import Module3.Module3Manager;
import Style.Cl;

public class ConsoleApp {

    public ConsoleApp() {
        Scanner scan = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                System.out.println(Cl.YELLOW + "\n------------------------------------------------------------------");
                System.out.println("---------------------- CONSOLE APPLICATION -----------------------");
                System.out.println("------------------------------------------------------------------" + Cl.RESET);
                System.out.println("");
                System.out.println("\t1. Smart City Route Planner");
                System.out.println("\t2. Data Sorter – Sorting Algorithm Comparison Tool");
                System.out.println("\t3. Algorithm Performance Analyzer (Time Complexity)");
                System.out.println("\t4. Exit");
                System.out.println("");
                System.out.print(Cl.YELLOW + "\tChoose A Module: " + Cl.RESET);

                choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        new Module1Console(scan);
                        break;
                    case 2:
                        new Module2Console(scan);
                        break;
                    case 3:
                        new Module3Manager();
                        break;
                    case 4:
                        System.out.println("\n\tExiting From The Application. Goodbye!");
                        scan.close();
                        return;
                    default:
                        System.out.println(Cl.RED + "\tInvalid Choice. Please Try Again." + Cl.RESET);
                }

            } catch (Exception e) {
                System.out.println(Cl.RED + "\n\tInvalid User Input" + Cl.RESET);
                scan.nextLine(); // removing hte invalid input characters
            }
        }
    }
}
