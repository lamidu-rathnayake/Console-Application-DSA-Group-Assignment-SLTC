package Module2;

import Style.Cl;

public class ComparisonTable {
    //creating the comparison table for each sorting method
    public static void show (long bubble, long merge, long quick){
        System.out.println("\n-------------------------Comparison of Sorting Algorithms-------------------------------");
         System.out.println(Cl.YELLOW + "\n\t--------------------------------------------");
        System.out.printf("\t%-15s %-20s%n", "Algorithm", "Execution Time (ns)");
        System.out.println("\t--------------------------------------------");
        System.out.printf("\t%-15s %-20d%n", "Bubble Sort", bubble);
        System.out.printf("\t%-15s %-20d%n", "Merge Sort", merge);
        System.out.printf("\t%-15s %-20d%n", "Quick Sort", quick);
        System.out.println("\t--------------------------------------------");
    }
}
    

