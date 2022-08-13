package bj11057;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[][] dp = new long[N + 1][10];

        Arrays.fill(dp[1], 1);

        for (int i = 2; i < N + 1; i++){
            for (int j = 0; j < 10; j++){
                for (int k = 0; k <= j; k++){
                    dp[i][j] += dp[i - 1][k] % 10007;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++){
            sum += dp[N][i];
            sum %= 10007;
        }

        System.out.println(sum);
    }
}
