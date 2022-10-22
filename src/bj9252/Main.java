package bj9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();
        int[][] dp = new int[s1.length + 1][s2.length + 1];
        for (int i = 1; i < s1.length + 1; i++){
            for (int j = 1; j < s2.length + 1; j++){
                if (s1[i - 1] == s2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = s1.length;
        int j = s2.length;
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j > 0){
            if (dp[i][j] == dp[i - 1][j]){
                i -= 1;
            }
            else if (dp[i][j] == dp[i][j - 1]){
                j -= 1;
            }
            else{
                sb.append(s1[i - 1]);
                i -= 1;
                j -= 1;
            }
        }

        System.out.println(dp[s1.length][s2.length]);
        System.out.println(sb.reverse());
    }
}
