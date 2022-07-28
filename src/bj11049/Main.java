package bj11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] matrix = new int[N + 1];
        int[][] dp = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        matrix[0] = Integer.parseInt(st.nextToken());
        matrix[1] = Integer.parseInt(st.nextToken());

        for (int i = 2; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i - 1] = Integer.parseInt(st.nextToken());
            matrix[i] = Integer.parseInt(st.nextToken());

        }

        for (int i = 1; i < N + 1; i++){
            for (int j = i - 1; j > 0; j--){
                int min = Integer.MAX_VALUE;
                for (int k = j; k < i; k++){
                    min = Math.min(min, dp[j][k] + dp[k + 1][i] + matrix[i] * matrix[k] * matrix[j - 1]);
                }
                dp[j][i] = min;
            }
        }
        System.out.println(dp[1][N]);

    }
}
