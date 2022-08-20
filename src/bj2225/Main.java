package bj2225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    final static long DIVIDE = 1000000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N + 1][K + 1];

        for (int i = 0; i < N + 1; i++){
            dp[i][1] = 1;
        }

        for (int k = 2; k < K + 1; k++){
            for (int n = 0; n < N + 1; n++){
                for (int x = 0; x <= n; x++){
                    dp[n][k] += dp[n - x][k - 1] % DIVIDE;
                }
            }
        }

        System.out.println(dp[N][K] % DIVIDE);
    }
}
