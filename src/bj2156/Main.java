package bj2156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i < N + 1; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = num[1];

        if (N > 1){
            dp[2] = num[1] + num[2];
        }

        for (int i = 3; i < N + 1; i++){
            dp[i] = Math.max(dp[i - 3] + num[i - 1] + num[i], Math.max(dp[i - 2] + num[i], dp[i - 1]));
        }

        System.out.println(dp[N]);
    }
}
