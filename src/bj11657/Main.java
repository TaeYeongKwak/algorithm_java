package bj11657;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<int[]> edgeList;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new long[N + 1];
        edgeList = new ArrayList<>();
        for (int i = 1; i < N + 1; i++){
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edgeList.add(new int[]{A ,B, C});
        }

        dp[1] = 0;
        boolean flag = false;

        for (int i = 1; i < N + 1; i++){
            for(int[] edge : edgeList) {
                int now = edge[0];
                int next = edge[1];
                int weight = edge[2];

                if (dp[now] == Integer.MAX_VALUE){
                    continue;
                }

                if (dp[next] > dp[now] + weight){
                    dp[next] = dp[now] + weight;
                    if (i == N){
                        flag = true;
                    }
                }
            }
        }

        if (flag){
            System.out.println("-1");
        }else{
            for (int i = 2; i < N + 1; i++){
                if (dp[i] == Integer.MAX_VALUE){
                    System.out.println("-1");
                }else{
                    System.out.println(dp[i]);
                }
            }
        }

    }

}
