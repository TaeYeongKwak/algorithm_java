package bj1094;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int X = sc.nextInt();
        String binaryNum = Integer.toBinaryString(X);
        int count = (int) Arrays.stream(binaryNum.split("")).filter(s -> s.equals("1")).count();
        System.out.println(count);
    }
}
