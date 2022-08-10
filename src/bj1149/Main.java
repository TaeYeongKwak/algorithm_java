package bj1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][3];
        StringTokenizer st;
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (i == 1){
                dp[i][0] = r;
                dp[i][1] = g;
                dp[i][2] = b;
            }
            else{
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + r;
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + g;
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + b;
            }
        }

        int result = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
        System.out.println(result);
    }
}
