package Module2;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import Style.Cl;

public class Module2Console {

    boolean freshLoad = true;

    public Module2Console(Scanner scan) {

        System.out.println(
                Cl.YELLOW + "\n-------------------- Sorting Algorithm Comparison Tool --------------------" + Cl.RESET);

        while (true) {

            if (freshLoad) {
                System.out.println("\n\t1. Enter Numbers Manually ");
                freshLoad = false;
            } else {
                System.out.println(Cl.YELLOW + "\n\t-----------------------------------------------");
                System.out.println(Cl.YELLOW + "\n\tMore Operations");
                System.out.println(Cl.RESET);
                System.out.println("\t1. Enter Numbers Manually ");
            }
            System.out.println("\t2. Generate Random Datasets ");
            System.out.println("\t3. Exit \n");
            System.out.print(Cl.YELLOW + "\tChoose A Operation: " + Cl.RESET);
            int choice = scan.nextInt();
            scan.nextLine();
            System.out.println(Cl.YELLOW + "\n\t-----------------------------------------------");

            switch (choice) {
                case 1:
                    EnterNumbersManually(scan);
                    break;

                case 2:
                    GenerateRandomDatasets(scan);
                    break;

                case 3:
                    System.out.println("\n\tExiting Module. Goodbye!");
                    return;

                default:
                    System.out.println(Cl.RED + "\n\tInvalid Choice. Please Try Again." + Cl.RESET);

            }
        }
    }

    public void EnterNumbersManually(Scanner scan) {
        // determine the size of the array
        System.out.print(Cl.YELLOW +"\n\tenter the number of elements: ");
        int size = scan.nextInt();
        int[] numbers = new int[size];

        // getting the numbers manually from the user
        for (int i = 0; i < size; i++) {
            System.out.print(Cl.YELLOW +"\n\tenter element: ");
            numbers[i] = scan.nextInt();

        }
        sorting(numbers);
    }

    public void GenerateRandomDatasets(Scanner scan) {
        System.out.print(Cl.YELLOW +"\n\tenter the number of elements: ");
        int size = scan.nextInt();
        int[] numbers = new int[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            numbers[i] = rand.nextInt(100);
        }
          sorting(numbers);
    }

    static void sorting(int[] original) {
        int[] bubblesort = original.clone();
        int[] quickSort = original.clone();
        int[] mergesort = original.clone();

        long start, end;
        // bubblesorting
        start = System.nanoTime();
        SortingAlgorithms.bubblesort(bubblesort);
        end = System.nanoTime();
        long bubbleTime = end - start;

        // mergesorting
        start = System.nanoTime();
        SortingAlgorithms.mergeSort(mergesort, 0, mergesort.length - 1);
        end = System.nanoTime();
        long mergeTime = end - start;

        // quicksorting
        start = System.nanoTime();
        SortingAlgorithms.quickSort(quickSort, 0, quickSort.length - 1);
        end = System.nanoTime();
        long quickTime = end - start;

        System.out.println(Cl.YELLOW + "\n\t-----------------------------------------------");
        System.out.print(Cl.GREEN + "\n\tSorted Output:");
        System.out.print(Arrays.toString(bubblesort));
        System.out.println(Cl.YELLOW + "\n\t-----------------------------------------------");

        ComparisonTable.show(bubbleTime, mergeTime, quickTime);
    }

}