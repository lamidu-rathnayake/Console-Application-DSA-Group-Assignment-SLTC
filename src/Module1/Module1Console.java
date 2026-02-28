package Module1;
import java.util.Scanner;

public class Module1Console{
    public static final String RESET = "\u001B[0m"; 
    public static final String BLACK = "\u001B[30m"; 
    public static final String RED = "\u001B[31m"; 
    public static final String GREEN = "\u001B[32m"; 
    public static final String YELLOW = "\u001B[33m"; 
    public static final String BLUE = "\u001B[34m"; 
    public static final String PURPLE = "\u001B[35m"; 
    public static final String CYAN = "\u001B[36m"; 
    public static final String WHITE = "\u001B[37m";

    boolean freshLoad = true;

    public Module1Console(Scanner sc) {
        System.out.println(YELLOW + "\n-------------------- Smart City Route Planner --------------------" + RESET);
        
        while (true) {
            
            if (freshLoad) {
                System.out.println("\n\t1. Add Location ");
                freshLoad = false;
            }
            else { 
                System.out.println(YELLOW + "\n\t-----------------------------------------------");
                System.out.println(YELLOW + "\n\tMore Operations");
                System.out.println(RESET);
                System.out.println("\t1. Add Location ");
            }
            System.out.println("\t2. Remove Location ");
            System.out.println("\t3. Add Road ");
            System.out.println("\t4. Remove Road ");
            System.out.println("\t5. Find Best Route ");
            System.out.println("\t6. Exit \n");
            System.out.print(YELLOW + "\tChoose A Operation: " + RESET); 
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println(YELLOW + "\n\t-----------------------------------------------");
            
            switch (choice) { 
                case 1: 
                    addLocation(sc);
                    break;

                case 2: 
                    removeLocation(sc);
                    break; 

                case 3: 
                    addRoad(sc);
                    break; 

                case 4: 
                    removeRoad(sc);
                    break; 

                case 5: 
                    traversal();
                    break; 

                case 6: 
                    System.out.println("\tExiting Module. Goodbye!"); 
                    return; 

                default: 
                    System.out.println(RED + "\tInvalid Choice. Please Try Again." + RESET); 
            
            }
        }
    }
    

    public void addLocation(Scanner scan) {
        System.out.println(YELLOW + "\n\tAdding Locations (Press 'dn' to quit)");
        System.out.println(RESET);
                    
        int addedLocationCount = 0;
        while (true) {
            try {
                // inputting the location: 
                System.out.print("\tLocation Name: ");
                String locationName = scan.next();
                
                // consuming the \n
                scan.nextLine();

                // exit from location adding process if the user enter dn or DN
                if (locationName.toUpperCase().equals("DN")) break;

                // if location manager returned 0, it means it did do the task (location is already in the tree and adj.list)
                // simply ignore the rest of the process and re start the location adding process wit new location
                if (LocationManager.addLocation(locationName) == 0) {
                    System.out.println(RED + "\t\tLocation Does Exist. Try Another!" + RESET);
                    continue;
                }
                else {
                    // if the locationManager returned 1, it means it added the location. now we have to increment the addedLocationCount by 1 
                    // then the program will continue with a new location 
                    addedLocationCount++;
                    continue;
                }
                    
            } catch (Exception e) {
                // if the process encounts a error during the runtime, this catch block will ignore the problem and re-try with a new location adding process
                System.out.println(String.format(RED +"\t\tSomething Happened! (%s Locations Were Added)" + RESET, addedLocationCount));
                continue;
            }
        }

        // finally we have this line to show how much of locations we added during the current location adding process
        System.out.println(String.format(GREEN + "\n\t%s Locations Were Added" + RESET, addedLocationCount));
    }

    public void removeLocation(Scanner scan) {
        System.out.println(YELLOW + "\n\tRemoving Locations (Press 'dn' to quit)");
        System.out.println(RESET);
                    
        int removedLocationCount = 0;
        while (true) {
            try {
                System.out.print("\tLocation Name: ");
                String locationName = scan.next();
                scan.nextLine();

                if (locationName.toUpperCase().equals("DN")) 
                    break;

                if (LocationManager.removeLocation(locationName) == 0) {
                    System.out.println(RED + "\t\tLocation Does Not Exist. Try Another!" + RESET);
                    continue;
                }
                else {
                    removedLocationCount++;
                    continue;
                }

                    
            } catch (Exception e) {
                System.out.println(String.format(RED + "\t\tSomething Happened! (%s Locations Were Deleted)" + RESET, removedLocationCount));
                scan.nextLine();
                continue;
            }
        }

        System.out.println(String.format(GREEN + "\n\t%s Locations Were deleted" + RESET, removedLocationCount));
    }


    public void addRoad(Scanner scan) {
        System.out.println(YELLOW + "\n\tAdding Roads (Press 'dn' to quit)");
        System.out.println(RESET);

        // while --> try catch
        int addedRoadCount = 0;
        while (true) {
            try {
                // asking the source location
                System.out.print("\tSource Location: ");
                String sourceLocation = scan.next();
                scan.nextLine();
                if (sourceLocation.toUpperCase().equals("DN")) break;
                
                // asking the destination
                System.out.print("\tDestination location: ");
                String destinationLocation = scan.next();
                scan.nextLine();
                if (destinationLocation.toUpperCase().equals("DN")) break;

                // asking the distance 
                System.out.print("\tdistance: ");
                int distance = scan.nextInt();
                scan.nextLine();
                if (distance == -1) break;
                
                LocationManager.addRoad(sourceLocation, destinationLocation, distance);
                addedRoadCount ++;
                System.out.println(); // putting space after each set of road insertions
                
            } catch (Exception e) {
                System.out.println(String.format(RED + "\t\tSomething Happened! (%s Roads Were Added)" + RESET, addedRoadCount));
                scan.nextLine();
                continue;
            }
        }

        // printing the list and number of added roads at the end
        LocationManager.printAdjacencyList();
        System.out.println(String.format(GREEN + "\n\t%s Roads Were Added" + RESET, addedRoadCount));
    }

    public void removeRoad(Scanner scan) {
        System.out.println(YELLOW + "\n\tRemoving Locations (Press 'dn' to quit)");
        System.out.println(RESET);

        // while --> try catch
        int removedRoadsCount = 0;
        while (true) {
            try {
                System.out.print("\tSource Location Name: ");
                String sourceLocationName = scan.next();
                scan.nextLine();
                if (sourceLocationName.toUpperCase().equals("DN")) break;

                System.out.print("\tDestination Location Name: ");
                String destinationLocationName = scan.next();
                scan.nextLine();
                if (destinationLocationName.toUpperCase().equals("DN")) break;
                
                if (LocationManager.removeRoad(sourceLocationName,destinationLocationName) == 0) {
                    System.out.println(RED + "\t\tLocation Does Not Exist. Try Another!" + RESET);
                    continue;
                }
                else {
                    removedRoadsCount++;
                    continue;
                }

            } catch (Exception e) {
                System.out.println(String.format(RED + "\t\tSomething Happened! (%s Roads Were Deleted)" + RESET, removedRoadsCount));
                scan.nextLine();
                continue;
            }
        }
        
        System.out.println(String.format(GREEN + "\n\t%s Roads Were deleted" + RESET, removedRoadsCount));
    }


    public void traversal() {
        System.out.println("\n\t Traveling through the map");
        LocationManager.traversal();
        System.out.println();
    }

}
