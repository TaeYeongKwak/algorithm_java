package bj2193;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] dp = new long[N + 1];
        dp[1] = 1;

        if (N > 1){
            dp[2] = 1;
        }

        for (int i = 3; i < N + 1; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[N]);
    }
}
