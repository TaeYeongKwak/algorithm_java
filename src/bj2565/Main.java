package bj2565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] wire = new int[N][2];
        int[] dp = new int[N];

        StringTokenizer st;
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wire[n] = new int[]{a, b};
        }

        Arrays.sort(wire, Comparator.comparing(o -> o[0]));

        int max = 1;
        for (int i = 0; i < N; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (wire[i][1] > wire[j][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);
    }
}
