package bj11062;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAXN = 1000;
    static int T, N;
    static int[] A;
    static int[][][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        A = new int[MAXN+1];
        dp = new int[2][MAXN+1][MAXN+1];
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[0][i], 0);
                Arrays.fill(dp[1][i], 0);
            }

            //j = 구간 시작점
            //i = 구간 종료점
            for(int e = 1; e <= N; e++){
                for(int s = e; s > 0; s--){
                    for(int flag = 0; flag < 2; flag++) {
                        //마지막 근우차례로 끝날 때만 A[s] 더해줌.
                        if(s == e) {
                            dp[flag][s][e] = (flag == 0 ? A[s] : 0);
                            continue;
                        }
                        if(flag == 0) {
                            dp[flag][s][e] = Math.max(dp[1][s+1][e] + A[s], dp[1][s][e-1] + A[e]);
                        } else {
                            dp[flag][s][e] = Math.min(dp[0][s+1][e], dp[0][s][e-1]);
                        }
                    }
                }
            }
            int ans = dp[0][1][N];
            //int ans = dfs(1, N, 0);
            bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int start, int end, int flag) {
        if(start == end) return dp[flag][start][end] = (flag == 0 ? A[start] : 0);
        if(dp[flag][start][end] != 0) return dp[flag][start][end];
        if (flag == 0)
            dp[flag][start][end] = Math.max(dfs(start + 1, end, 1) + A[start], dfs(start, end - 1, 1) + A[end]);
        else
            dp[flag][start][end] = Math.min(dfs(start + 1, end, 0), dfs(start, end - 1, 0));
        return dp[flag][start][end];
    }
}
