package Module1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LocationManager {
    public static HashMap<String, List<Road>> adjacencyList = new HashMap<>();
    public static LocationTree locationTree = new LocationTree();

    public static int addLocation(String location) {
        // checking the existence and then adding
        // program will insert the location to both adjacency list and location tree if the location does not exist in those places
        if (locationTree.search(location) == null && !(adjacencyList.containsKey(location))) {
            locationTree.insert(location);
            adjacencyList.put(location, new LinkedList<>());
            return 1; // returning 1 if the location was added
        }
        else {
            return 0; // returning 0 if the location was not added (location alreday does exist)
        }
    }

    public static int removeLocation(String location) {
        locationTree.delete(location);
        // removing locations and corresponding roads
        if (adjacencyList.containsKey(location)) {
            adjacencyList.remove(location);
            // removeing each edges those were connect with removing vertex
            for (Map.Entry<String, List<Road>> vertex : adjacencyList.entrySet()) {
                vertex.getValue().removeIf( road -> road.destination.equals(location));
            }
            // removed
            return 1;
        } else {
            // couldn't remove
            return 0;
        }
    }

    public static int addRoad(String location1, String location2, int distance) {
        if (adjacencyList.containsKey(location1) && adjacencyList.containsKey(location2)) {
            adjacencyList.get(location1).add(new Road(location1, distance, location2));
            adjacencyList.get(location2).add(new Road(location2, distance, location1));
            return 1;
        }
        else {
            return 0;
        }

    }

    public static int removeRoad(String location1, String location2) {
        if (adjacencyList.containsKey(location1) && adjacencyList.containsKey(location2)) {
            adjacencyList.get(location1).removeIf(road -> road.destination.equals(location2));
            adjacencyList.get(location2).removeIf(road -> road.destination.equals(location1));
            return 1;
        } else {
            return 0;
        }
    }

    public static void printAdjacencyList() {
        System.out.println("\n\tAdjacency List:");
        System.out.println("\t------------------------------------------------");
        for (Map.Entry<String, List<Road>> entry : adjacencyList.entrySet()) {
            System.out.println("\n\t\tLocation: "+entry.getKey());
            for (Road road : entry.getValue()) {
                System.out.println("\t\t\t S:" + road.source + " D:" + road.destination + " Dest:" + road.distance);
            }
        }
    }
    
    public static void traversal() {
        locationTree.bft();
    }
}
