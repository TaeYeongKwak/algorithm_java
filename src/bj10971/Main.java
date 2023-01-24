package bj10971;

import java.util.StringTokenizer;
import java.io.*;
import java.util.Arrays;

public class Main{

    static int N;
    static int[][] dp;
    static int[][] graph;
    static int bit_max;
    static int INF = 16 * 1000000;

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        bit_max = 1 << N;
        graph = new int[N][N];
        dp = new int[N][bit_max];

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i], INF);
        }

        System.out.println(dfs(0, 1));
    }

    static int dfs(int now, int visit){
        if(visit == bit_max - 1){
            if(graph[now][0]!=0)
                return graph[now][0];
            else
                return INF;
        }

        if(dp[now][visit] != INF){
            return dp[now][visit];
        }

        for(int i = 0; i < N; i++){
            int next = visit | (1 << i);
            if(graph[now][i] !=0 && (visit & (1 << i)) == 0){
                dp[now][visit] = Math.min(dp[now][visit], dfs(i,next) + graph[now][i]);
            }
        }
        return dp[now][visit];
    }
}
