package bj2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coin = new int[N];
        int[] dp = new int[K + 1];

        Arrays.fill(dp, 10001);

        for (int n = 0; n < N; n++){
            coin[n] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;
        for (int n = 0; n < N; n++){
            for (int k = coin[n]; k < K + 1; k++){
                dp[k] = Math.min(dp[k - coin[n]] + 1, dp[k]);
            }
        }

        System.out.println((dp[K] >= 10001)? -1 : dp[K]);
    }
}
