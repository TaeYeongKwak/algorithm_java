package bj11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[N + 1];
        for (int n = 1; n < N + 1; n++){
            num[n] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N + 1];

        for (int i = 1; i < N + 1; i++){
            int temp = 0;
            for (int j = i - 1; j > 0; j--){
                if (num[j] < num[i]){
                    temp = Math.max(dp[j], temp);
                }
            }
            dp[i] = temp + 1;
        }

        int result = 0;
        for (int i = 1; i < N + 1; i++){
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
