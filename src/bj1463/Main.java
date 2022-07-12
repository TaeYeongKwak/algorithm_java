package bj1463;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int dp[] = new int[n + 1];
        dp[1] = 0;
        for(int i = 2; i <= n; i++){
            if (i <= 3) dp[i] = 1;
            int x = n;
            if (i % 3 == 0) x = dp[i / 3] + 1;
            if (i % 2 == 0)  x = (dp[i / 2] + 1 < x) ? dp[i / 2] + 1 : x;
            x = (dp[i - 1] + 1 < x) ? dp[i - 1] + 1 : x;

            dp[i] = x;
        }
        System.out.println(dp[n]);
    }
}
