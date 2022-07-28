package bj1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][N];
        int[][] map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];

        for (int i = 1; i < N; i++){
            for (int j = 0; j <= i; j++){
                if (j > 0){
                    dp[i][j] = map[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }else if (j == 0){
                    dp[i][j] = map[i][j] + dp[i - 1][j];
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++){
            if (result < dp[N - 1][i]){
                result = dp[N - 1][i];
            }
        }

        System.out.println(result);

    }
}
