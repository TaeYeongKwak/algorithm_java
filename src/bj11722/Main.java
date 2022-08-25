package bj11722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] num = new int[N + 1];
        int[] dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;

        int max = dp[1];
        for (int i = 2; i < N + 1; i++){
            for (int j = 1; j < i; j++){
                if (num[i] < num[j]){
                    dp[i] = Math.max(dp[j], dp[i]);
                }
            }
            dp[i] += 1;
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}
