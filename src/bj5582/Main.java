package bj5582;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        char[] T = br.readLine().toCharArray();

        int[][] dp = new int[S.length + 1][T.length + 1];

        int max = 0;
        for (int s = 1; s < S.length + 1; s++){
            for (int t = 1; t < T.length + 1; t++){
                if (S[s - 1] == T[t - 1]){
                    dp[s][t] = dp[s - 1][t - 1] + 1;
                    max = Math.max(dp[s][t], max);
                }
            }
        }

        System.out.println(max);
    }
}
