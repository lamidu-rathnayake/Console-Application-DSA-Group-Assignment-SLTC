package Module2;

import java.util.Random;
import java.util.Scanner;

public class Module2Console {
    public Module2Console() {
        System.out.println("this is the module 2 console");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Manual Input");
        System.out.println("2. Random Input");
        int choice = sc.nextInt();

int[] arr;

if (choice == 1) {
    System.out.println( "enter the number of elements: ");
    int size = sc.nextInt();
    arr = new int[size];

    for (int i = 0; i < size; i++) {
        System.out.println( "enter element: ");
        arr[i] = sc.nextInt();

    }
}
else  {
    System.out.println( "enter the number of elements: ");
    int size = sc.nextInt();
    arr = new int[size];
    Random rand = new Random();

    for (int i = 0; i < size; i++) {
        arr[i] = rand.nextInt(1000);

    }
}
}
}