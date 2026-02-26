package Module1;

import java.util.Scanner;

public class Module1Console {
    boolean freshLoad = true;
    public Module1Console() {
        System.out.println("\n-------------------- Smart City Route Planner --------------------");
        while (true) {
            Scanner sc = new Scanner(System.in);
            
            if (freshLoad) {
                System.out.println("\n\t1. Add Location ");
                freshLoad = false;
            }
            else { 
                System.out.println("\t1. Add Location ");
            }
            System.out.println("\t2. Remove Location ");
            System.out.println("\t3. Add Road ");
            System.out.println("\t4. Remove Road ");
            System.out.println("\t5. Find Best Route ");
            System.out.println("\t6. Display Location Summery ");
            System.out.println("\t7. Exit \n");
            System.out.print("\tChoose A Operation: "); 
            int choice = sc.nextInt();
            sc.nextLine();
            
            
            switch (choice) { 
                case 1: 
                    System.out.println("\n\tAdding Location (Press 'dn' to quit)");
                    System.out.println("\t------------------------------------------------");
                    
                    int addedLocation = addLocation();
                    System.out.println(String.format("\n\t%s Locations Were Added", addedLocation));

                    System.out.println("\n\tMore Operations");
                    System.out.println("\t------------------------------------------------");

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
                    System.out.println("\tExiting Module. Goodbye!"); 
                    return; 
                default: 
                    System.out.println("\tInvalid Choice. Please Try Again."); 
            
            }
        }
    }
    

    // adding location
    public int addLocation() {
        int addedLocationCount = 0;
        while (true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("\tLocation Name: ");
                String locationName = scan.next();
                
                scan.nextLine();

                if (locationName.toUpperCase().equals("DN")) 
                    return addedLocationCount;

                if (LocationManager.addLocation(locationName) == 0)
                    System.out.println("\t\tLocation Does Exist. Try Another!");
                
                addedLocationCount++;
                    
            } catch (Exception e) {
                System.out.println(String.format("\t\tSomething Happened! (%s Locations Were Added)", addedLocationCount));
                continue;
            }
        }
    }
}
