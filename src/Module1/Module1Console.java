package Module1;

import java.util.ArrayList;
import java.util.Scanner;
import Style.Cl;

public class Module1Console {

    boolean freshLoad = true;

    public Module1Console(Scanner scan) {
        try {
            System.out.println(Cl.YELLOW + "\n-------------------- Smart City Route Planner --------------------" + Cl.RESET);
    
            while (true) {
    
                if (freshLoad) {
                    System.out.println("\n\t1. Add Location ");
                    freshLoad = false;
                } else {
                    System.out.println(Cl.YELLOW + "\n\t-----------------------------------------------");
                    System.out.println(Cl.YELLOW + "\n\tMore Operations");
                    System.out.println(Cl.RESET);
                    System.out.println("\t1. Add Location ");
                }
                System.out.println("\t2. Remove Location ");
                System.out.println("\t3. Add Road ");
                System.out.println("\t4. Remove Road ");
                System.out.println("\t5. Traversal ");
                System.out.println("\t6. Exit \n");
                System.out.print(Cl.YELLOW + "\tChoose A Operation: " + Cl.RESET);
                int choice = scan.nextInt();
                scan.nextLine();
                System.out.println(Cl.YELLOW + "\n\t-----------------------------------------------");
    
                switch (choice) {
                    case 1:
                        addLocation(scan);
                        break;
    
                    case 2:
                        removeLocation(scan);
                        break;
    
                    case 3:
                        addRoad(scan);
                        break;
    
                    case 4:
                        removeRoad(scan);
                        break;
    
                    case 5:
                        traversal(scan);
                        break;
    
                    case 6:
                        System.out.println("\n\tExiting Module. Goodbye!");
                        return;
    
                    default:
                        System.out.println(Cl.RED + "\n\tInvalid Choice. Please Try Again." + Cl.RESET);
    
                }
            }
        } catch (Exception e) {
            System.out.println(Cl.RED + "\n\tInvalid User Input" + Cl.RESET);
            scan.nextLine(); // removing hte invalid input characters
        }
    }

    public void addLocation(Scanner scan) {
        System.out.println(Cl.YELLOW + "\n\tAdding Locations (Press 'dn' to quit)");
        System.out.println(Cl.RESET);

        int addedLocationCount = 0;
        while (true) {
            try {
                // inputting the location:
                System.out.print("\tLocation Name: ");
                String locationName = scan.next().trim();

                // consuming the \n
                scan.nextLine();

                // exit from location adding process if the user enter dn or DN
                if (locationName.toUpperCase().equals("DN"))
                    break;

                // if location manager returned 0, it means it did do the task (location is
                // already in the tree and adj.list)
                // simply ignore the rest of the process and re start the location adding
                // process wit new location
                if (LocationManager.addLocation(locationName) == 0) {
                    System.out.println(Cl.RED + "\n\tLocation Does Exist. Try Another!\n" + Cl.RESET);
                    continue;
                } else {
                    // if the locationManager returned 1, it means it added the location. now we
                    // have to increment the addedLocationCount by 1
                    // then the program will continue with a new location
                    addedLocationCount++;
                    continue;
                }

            } catch (Exception e) {
                // if the process encounts a error during the runtime, this catch block will
                // ignore the problem and re-try with a new location adding process
                System.out.println(String.format(
                        Cl.RED + "\n\tSomething Happened! (%s Locations Were Added)\n" + Cl.RESET, addedLocationCount));
                continue;
            }
        }

        // finally we have this line to show how much of locations we added during the
        // current location adding process
        System.out.println(String.format(Cl.GREEN + "\n\t%s Locations Were Added" + Cl.RESET, addedLocationCount));
    }

    public void removeLocation(Scanner scan) {
        System.out.println(Cl.YELLOW + "\n\tRemoving Locations (Press 'dn' to quit)");
        System.out.println(Cl.RESET);

        int removedLocationCount = 0;
        while (true) {
            try {
                System.out.print("\tLocation Name: ");
                String locationName = scan.next().trim();
                scan.nextLine();

                if (locationName.toUpperCase().equals("DN"))
                    break;

                if (LocationManager.removeLocation(locationName) == 0) {
                    System.out.println(Cl.RED + "\n\tLocation Does Not Exist. Try Another!\n" + Cl.RESET);
                    continue;
                } else {
                    removedLocationCount++;
                    continue;
                }

            } catch (Exception e) {
                System.out.println(
                        String.format(Cl.RED + "\n\tSomething Happened! (%s Locations Were Deleted)\n" + Cl.RESET,
                                removedLocationCount));
                scan.nextLine();
                continue;
            }
        }

        System.out.println(String.format(Cl.GREEN + "\n\t%s Locations Were deleted" + Cl.RESET, removedLocationCount));
    }

    public void addRoad(Scanner scan) {
        System.out.println(Cl.YELLOW + "\n\tAdding Roads (Press 'dn' to quit)");
        System.out.println(Cl.RESET);

        // while --> try catch
        int addedRoadCount = 0;
        while (true) {
            try {
                // asking the source location
                System.out.print("\tSource Location: ");
                String sourceLocation = scan.next().trim();
                scan.nextLine();
                if (sourceLocation.toUpperCase().equals("DN"))
                    break;
                if (!LocationManager.adjacencyList.containsKey(sourceLocation)) {
                    System.out.println(Cl.RED + "\n\tLocation Does Not Exist. Try Another!\n" + Cl.RESET);
                    continue;
                }

                // asking the destination
                System.out.print("\tDestination location: ");
                String destinationLocation = scan.next().trim();
                scan.nextLine();
                if (destinationLocation.toUpperCase().equals("DN"))
                    break;
                if (!LocationManager.adjacencyList.containsKey(destinationLocation)) {
                    System.out.println(Cl.RED + "\n\tLocation Does Not Exist. Try Another!\n" + Cl.RESET);
                    continue;
                }

                // asking the distance
                System.out.print("\tdistance: ");
                String distance = scan.nextLine().trim();
                int distanceInt = 0;
                if (distance.toUpperCase().equals("DN")) {
                    break;
                } else {
                    distanceInt = Integer.parseInt(distance);
                    if (distanceInt < 0) {
                        System.out.println(Cl.RED + "\n\tDistance cannot be less than 0. Try Another!\n" + Cl.RESET);
                        continue;
                    }
                }

                LocationManager.addRoad(sourceLocation, destinationLocation, distanceInt);
                addedRoadCount++;
                System.out.println(); // putting space after each set of road insertions

            } catch (Exception e) {
                System.out.println(String.format(Cl.RED + "\n\tSomething Happened! (%s Roads Were Added)\n" + Cl.RESET,
                        addedRoadCount));
                scan.nextLine();
                continue;
            }
        }

        // printing the list and number of added roads at the end
        LocationManager.printAdjacencyList();
        System.out.println(String.format(Cl.GREEN + "\n\t%s Roads Were Added" + Cl.RESET, addedRoadCount));
    }

    public void removeRoad(Scanner scan) {
        System.out.println(Cl.YELLOW + "\n\tRemoving Roads (Press 'dn' to quit)");
        System.out.println(Cl.RESET);

        // while --> try catch
        int removedRoadsCount = 0;
        while (true) {
            try {
                System.out.print("\tSource Location Name: ");
                String sourceLocationName = scan.next().trim();
                scan.nextLine();
                if (sourceLocationName.toUpperCase().equals("DN"))
                    break;

                System.out.print("\tDestination Location Name: ");
                String destinationLocationName = scan.next().trim();
                scan.nextLine();
                if (destinationLocationName.toUpperCase().equals("DN"))
                    break;

                if (LocationManager.removeRoad(sourceLocationName, destinationLocationName) == 0) {
                    System.out.println(Cl.RED + "\n\tLocation Does Not Exist. Try Another!\n" + Cl.RESET);
                    continue;
                } else {
                    removedRoadsCount++;
                    continue;
                }

            } catch (Exception e) {
                System.out.println(String.format(
                        Cl.RED + "\n\tSomething Happened! (%s Roads Were Deleted)\n" + Cl.RESET, removedRoadsCount));
                scan.nextLine();
                continue;
            }
        }

        System.out.println(String.format(Cl.GREEN + "\n\t%s Roads Were deleted" + Cl.RESET, removedRoadsCount));
    }

    public void traversal(Scanner scan) {
        System.out.println(Cl.YELLOW + "\n\tTraveling through the map\n" + Cl.RESET);
        System.out.print("\tEnter the Starting Location: ");
        String startLocation = scan.next().trim();
        ArrayList<String> traveledLocations = LocationManager.traversal(startLocation);

        // printing all the traveled location
        System.out.print(Cl.GREEN + "\n\tTraversal:  ");
        for (String location : traveledLocations) {
            System.out.print(String.format(" -> %s", location));
        }
        System.out.println(Cl.RESET);
    }

}
