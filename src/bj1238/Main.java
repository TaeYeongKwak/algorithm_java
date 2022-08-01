package bj1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, X;
    static ArrayList<int[]>[] graph;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        dp = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, time});
        }


        for (int n = 1; n < N + 1; n++){
            dijkstra(n);
        }

        int result = 0;
        for (int n = 1; n < N + 1; n++){
            if (n == X) continue;

            result = Math.max(result, dp[n][X] + dp[X][n]);
        }

        System.out.println(result);
    }

    static void dijkstra(int start){
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        heap.offer(new int[]{start, 0});
        dp[start][start] = 0;

        while (!heap.isEmpty()){
            int[] now = heap.poll();

            if (dp[start][now[0]] < now[1]) continue;

            for (int[] next : graph[now[0]]){
                if (dp[start][next[0]] > dp[start][now[0]] + next[1]){
                    dp[start][next[0]] = dp[start][now[0]] + next[1];
                    heap.offer(new int[]{next[0], dp[start][next[0]]});
                }
            }
        }
    }
}
