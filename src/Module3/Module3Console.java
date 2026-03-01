package Module3;

import java.util.Random;
import java.util.Scanner;

import Module2.SortingAlgorithms;
import Module2.SortingAlgorithms.*;

import Style.Cl;

public class Module3Console {
    Boolean freshLoad = true;

    public Module3Console(Scanner scan) {
        try {
            System.out.println(Cl.YELLOW + "\n-------------------- Algorithm Performance Analyzer --------------------"
                    + Cl.RESET);

            while (true) {

                if (freshLoad) {
                    System.out.println("\n\t1. Run Searching Algorithm (Linear/Binary) ");
                    freshLoad = false;
                } else {
                    System.out.println(Cl.YELLOW + "\n\t-----------------------------------------------");
                    System.out.println(Cl.YELLOW + "\n\tMore Operations");
                    System.out.println(Cl.RESET);
                    System.out.println("\t1. Run Searching Algorithm (Linear/Binary) ");
                }
                System.out.println("\t2. Run Sorting Algorithm (Bubble/Quick/Merge) ");
                System.out.println("\t3. Exit \n");
                System.out.print(Cl.YELLOW + "\tChoose A Operation: " + Cl.RESET);
                int choice = scan.nextInt();
                scan.nextLine();
                System.out.println(Cl.YELLOW + "\n\t-----------------------------------------------");

                switch (choice) {
                    case 1:
                        searchingAlgorithmAnalizer(scan);
                        break;

                    case 2:
                        break;

                    case 3:
                        System.out.println("\n\tExiting Module. Goodbye!");
                        return;

                    default:
                        System.out.println(Cl.RED + "\n\tInvalid Choice. Please Try Again." + Cl.RESET);
                        continue;

                }
            }
        } catch (Exception e) {
            System.out.println(Cl.RED + "\n\tInvalid User Input" + Cl.RESET);
            scan.nextLine(); // removing hte invalid input characters
        }
    }

    // Searching algorithm analizer
    public static void searchingAlgorithmAnalizer(Scanner scan) {
        System.out.println(Cl.YELLOW + "\n\tSearching Algorithm Analizer: " + Cl.RESET);
        System.out.println("\t1. Linear Searching ");
        System.out.println("\t2. Binary searching ");
        System.out.println("\t3. Exit \n");
        System.out.print(Cl.YELLOW + "\tChoose A Operation: " + Cl.RESET);
        int choice = scan.nextInt();
        scan.nextLine();
        System.out.println(Cl.YELLOW + "\n\t-----------------------------------------------");

        switch (choice) {
            case 1:
                searchAlgo(scan, 1);
                break;
            case 2:
                searchAlgo(scan, 2);
                break;
            case 3:
                return;
            default:
                break;
        }
    }

    public static void searchAlgo(Scanner scan, int type) {
        System.out.println(Cl.YELLOW + "\n\tLinear Search Analyzer" + Cl.RESET);
        int[] numberArray1 = generateArray(100);
        int[] numberArray2 = generateArray(500);
        int[] numberArray3 = generateArray(1000);

        if (type == 2) {
            SortingAlgorithms.quickSort(numberArray1, 0, numberArray1.length - 1);
            SortingAlgorithms.quickSort(numberArray2, 0, numberArray2.length - 1);
            SortingAlgorithms.quickSort(numberArray3, 0, numberArray3.length - 1);
        }

        System.out.print("\tEnter Search Value: ");
        int key = scan.nextInt();

        long start1 = System.nanoTime();
        int index1 = type == 1 ? linearSearch(numberArray1, key) : binarySearch(numberArray1, key);
        ;
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        int index2 = type == 1 ? linearSearch(numberArray2, key) : binarySearch(numberArray2, key);
        ;
        long end2 = System.nanoTime();

        long start3 = System.nanoTime();
        int index3 = type == 1 ? linearSearch(numberArray3, key) : binarySearch(numberArray3, key);
        ;
        long end3 = System.nanoTime();

        // finding the time
        long time1 = end1 - start1;
        long time2 = end2 - start2;
        long time3 = end3 - start3;

        System.out.println(
                Cl.YELLOW + "\n\t------------------- Search Performance Results -------------------" + Cl.RESET);

        System.out.println("\tInput Size\tResult\t\t\tIndex\tExecution Time (ns)");
        System.out.println("\t---------------------------------------------------------------");

        // Row 1
        System.out.print("\t100\t\t");

        if (index1 == -1) {
            System.out.print("Not Found\t\t-");
        } else {
            System.out.print("Found\t\t\t" + index1);
        }

        System.out.println("\t" + time1);

        // Row 2
        System.out.print("\t500\t\t");

        if (index2 == -1) {
            System.out.print("Not Found\t\t-");
        } else {
            System.out.print("Found\t\t\t" + index2);
        }

        System.out.println("\t" + time2);

        // Row 3
        System.out.print("\t1000\t\t");

        if (index3 == -1) {
            System.out.print("Not Found\t\t-");
        } else {
            System.out.print("Found\t\t\t" + index3);
        }

        System.out.println("\t" + time3);

        System.out
                .println(Cl.YELLOW + "\t---------------------------------------------------------------\n" + Cl.RESET);
    }

    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] numbers, int key) {
        int left = 0;
        int right = numbers.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (numbers[mid] == key)
                return mid;
            if (numbers[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    public static int[] generateArray(int size) {
        int[] numbers = new int[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            numbers[i] = rand.nextInt(100);
        }
        return numbers;
    }
}
