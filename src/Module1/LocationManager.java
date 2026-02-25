package Module1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LocationManager {
    public static HashMap<String, List<Road>> adjacencyList = new HashMap<>();
    public static LocationTree locationTree = new LocationTree();

    public LocationManager(String[] locations, Road[][] roads) {
        addLocation(locations, roads);
    }

    public void addLocation(String[] locations, Road[][] roads) {
        for (int i = 0; i < locations.length; i++) {
            // adding the location to the tree
            locationTree.insert(locations[i]);

            // adding the location and roads to the adjacency list
            // creating a tempory list
            List<Road> connectionList = new LinkedList<>();
            for (Road road : roads[i]) {
                connectionList.add(road);
            }
            adjacencyList.put(locations[i], connectionList);
        }
    }


    // console >>> location input kara
    public static void addLocation(String location) {
        if (locationTree.search(location) == null && !(adjacencyList.containsKey(location))) {
            locationTree.insert(location);
            adjacencyList.put(location, new LinkedList<>());
        }
        else {
            System.out.println("\t\tLocation does exist!");
        }
    }


    public void removeLocation(String location) {
        // remove from tree
        locationTree.delete(location);

        // remove locations and corresponding roads
        if (adjacencyList.containsKey(location)) {
            adjacencyList.remove(location);

            // removeing eah edges those were connect with removing vertex
            for (Map.Entry<String, List<Road>> vertex : adjacencyList.entrySet()) {
                for (Road road : vertex.getValue()) {
                    if (road.destination == location) {
                        vertex.getValue().remove(road);
                    }
                }
            }
        } else {
            System.out.println(String.format("Vertex %s not exists.", location));
        }
    }

    public void removeRoad(String location1, String location2) {
        if (adjacencyList.containsKey(location1) && adjacencyList.containsKey(location2)) {
            if (adjacencyList.get(location1).contains(location2)) {
                adjacencyList.get(location1).remove(location2);
            }
            if (adjacencyList.get(location2).contains(location1)) {
                adjacencyList.get(location2).remove(location1);
            }
        } else {
            System.out.println("Vertex is not exist");
        }
    }

    public static void display() {
        System.out.println("");
        System.out.println("Locations And Roads");
        System.out.println("--------------------------------------");
        for (Map.Entry<String, List<Road>> vertexWithAllConnectedRoads : adjacencyList.entrySet()) {
            System.out.println(String.format("Location --> %s",vertexWithAllConnectedRoads.getKey()));
            for (Road road : vertexWithAllConnectedRoads.getValue()) {
                System.out.println("\t Road to --> " + road.destination + " (distance: " + road.distance + ")");
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("");
    }



    // SHORTEST PATH ALGORITHM
    // -----------------------
    // String source, String destication
    public void findShortestPath() { 
        String source = "C"; // sample
        String target = "B"; // sample

        // checking whether the given locations are exist
        if (!(adjacencyList.containsKey(source) && adjacencyList.containsKey(target))) {
            System.out.println("Location does not exist.");
            return;
        }

        int[] visited = new int[adjacencyList.size()];
        int[] shortestDist = new int[adjacencyList.size()];
        int[] previousLocation = new int[adjacencyList.size()];
        PriorityQueue<Road> queue = new PriorityQueue<>(Comparator.comparingInt(r -> r.distance));

        // initializing the location index map
        HashMap<String,Integer> locationIndexMap = new HashMap<>(); 
        int indexseq = 0;
        for (Map.Entry<String, List<Road>> locationEntry : adjacencyList.entrySet()) {
            locationIndexMap.put(locationEntry.getKey(), indexseq++);
        }

        int indexOfSource = locationIndexMap.get(source);
        int indexOfTarget = locationIndexMap.get(target);

        // initialising arrays
        // for () {}

        System.out.println(locationIndexMap);
        System.out.println(indexOfSource);
        System.out.println(indexOfTarget);

    }


}
