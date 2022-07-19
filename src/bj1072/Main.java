package bj1072;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        long y = sc.nextLong();
        long z = 100 * y / x;
        long result = -1;

        long start = 0;
        long end = x;


        while(true){
            long middle = (start + end) / 2;
            long rate = 100 * (y + middle) / (x + middle);
            long target = rate - z;
            if (target > 0){
                result = middle;
                end = middle - 1;
            }
            else{
                start = middle + 1;
            }

            if (start > end){
                break;
            }
        }

        System.out.println(result);
    }
}
