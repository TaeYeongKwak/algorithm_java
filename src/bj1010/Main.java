package bj1010;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        long[][] dp = new long[30][30];

        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i < 30; i++){
            for (int j = 0; j <= i; j++){
                if (j == 0 || j == i){
                    dp[i][j] = 1;
                }
                else{
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }
            }
        }
        StringTokenizer st;
        for (int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            bw.write(dp[M][N] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
