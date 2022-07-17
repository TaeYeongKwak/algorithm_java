package bj1912;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numList = new int[n];
        int[] dp = new int[n];

        String[] numListStr = br.readLine().split(" ");
        for (int i = 0; i < n; i++){
            numList[i] = Integer.parseInt(numListStr[i]);
        }

        dp[0] = numList[0];
        int max = dp[0];

        for (int i = 1; i < n; i++){
            dp[i] = Math.max(numList[i] + dp[i-1], numList[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
