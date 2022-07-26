package bj17435;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] func;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int M = Integer.parseInt(br.readLine());

        func = new int[M + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < M + 1; i++){
            func[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[20][M + 1];
        for (int i = 1; i < M + 1; i++){
            dp[0][i] = func[i];
        }

        for (int i = 1; i < 20; i++){
            for (int j = 1; j < M + 1; j++){
                dp[i][j] = dp[i-1][dp[i-1][j]];
            }
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 20; j++){
                if ((n & (1 << j)) > 0){
                    x = dp[j][x];
                }
            }
            bw.write(x + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
