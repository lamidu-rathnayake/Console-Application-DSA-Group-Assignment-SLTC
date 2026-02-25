package Module1;

import java.util.Scanner;

public class Module1Manager {
    public Module1Manager() {
        System.out.println("\n-------------------- Smart City Route Planner --------------------");
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("");
            System.out.println("\t1. Add Location ");
            System.out.println("\t2. Remove Location ");
            System.out.println("\t3. Add Road ");
            System.out.println("\t4. Remove Road ");
            System.out.println("\t5. Find Best Route ");
            System.out.println("\t6. Display Location Summery ");
            System.out.println("\t7. Exist ");
            System.out.println("");
            System.out.print("\tChoose a operation: "); 
            int choice = sc.nextInt();
            sc.nextLine();
            
            
            switch (choice) { 
                case 1: 
                System.out.println("");
                System.out.println("");
                System.out.println("\tAdding location ");
                System.out.println("\t------------------------------------------ ");
                    addLocation();
                    break; 
                case 2: 
                    // remove location
                    break; 
                case 3: 
                    // add road
                    break; 
                case 4: 
                    // remove road
                    break; 
                case 5: 
                    // find road
                    break; 
                case 6: 
                    // summery display
                    break; 
                case 7: 
                    System.out.println("\tExiting application. Goodbye!"); 
                    return; 
                default: 
                    System.out.println("\tInvalid choice. Please try again."); 
            } 
        }

    }


    // adding location
    public void addLocation() {

        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("\tLocation Name: ");
                String locationName = scan.next();
                scan.nextLine();
                if (locationName.equals("Q") || locationName.equals("q")) return;
                LocationManager.addLocation(locationName);
            } catch (Exception e) {
                continue;
            }
        }
    }
}
