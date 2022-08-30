package bj9084;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int t = 1; t < T + 1; t++){
            N = Integer.parseInt(br.readLine());
            coins = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());

            dp = new int[M + 1];

            for (int c = 1; c < N + 1; c++){
                if (coins[c] > M) continue;
                dp[coins[c]] += 1;
                for (int m = coins[c] + 1; m < M + 1; m++){
                    if (dp[m - coins[c]] != 0){
                        dp[m] += dp[m - coins[c]];
                    }
                }
            }

            bw.write(dp[M] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
