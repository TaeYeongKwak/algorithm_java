package bj15681;

import java.io.*;
import java.util.*;

public class Main {

    static int N, R, Q;
    static List<Integer>[] graph;
    static int[] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        dp = new int[N + 1];
        for (int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        visited = new boolean[N + 1];
        visited[R] = true;
        dfs(R);

        for (int q = 0; q < Q; q++){
            int u = Integer.parseInt(br.readLine());
            bw.write(dp[u] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int now){
        dp[now] = 1;

        for (int next : graph[now]){
            if (visited[next]) continue;
            visited[next] = true;
            dp[now] += dfs(next);
        }

        return dp[now];
    }
}
