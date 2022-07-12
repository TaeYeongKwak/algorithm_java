package bj3009;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] xs = new int[1001];
        int[] ys = new int[1001];

        for (int i = 0; i < 3; i++){
            xs[sc.nextInt()] += 1;
            ys[sc.nextInt()] += 1;
        }

        int[] result = new int[2];
        for(int i = 1; i < 1001; i++){
            if(xs[i] == 1){
                result[0] = i;
            }

            if (ys[i] == 1){
                result[1] = i;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }
}
