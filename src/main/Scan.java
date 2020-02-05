package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scan {
    public static String nextLine(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static Integer nextInt()throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
