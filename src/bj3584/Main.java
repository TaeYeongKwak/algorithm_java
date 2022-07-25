package bj3584;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] dp;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean[] isNotRoot;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());

            graph = new ArrayList[N + 1];
            depth = new int[N + 1];
            dp = new int[15][N + 1];
            isNotRoot = new boolean[N + 1];

            for (int i = 1; i < N + 1; i++){
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < N - 1; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                graph[to].add(from);
                isNotRoot[to] = true;
            }

            int root = 0;
            for(int i = 1; i < N + 1; i++){
                if (!isNotRoot[i]){
                    root = i;
                    break;
                }
            }
            // 초기화
            init(root);
            //dp 값 설정
            for (int i = 1; i < 15; i++){
                for (int j = 1; j < N + 1; j++){
                    dp[i][j] = dp[i - 1][dp[i - 1][j]];
                }
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(lca(a, b) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void init(int root){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        visited = new boolean[N + 1];
        visited[root] = true;

        while (!queue.isEmpty()){
            int now = queue.poll();
            for (int next : graph[now]){
                if (!visited[next]){
                    visited[next] = true;
                    dp[0][next] = now;
                    depth[next] = depth[now] + 1;
                    queue.offer(next);
                }
            }
        }
    }

    static int lca(int a, int b){
        // a, b 깊이 확인 a가 더 깊을 경우 a 와 b를 교환
        if (depth[a] > depth[b]){
            int temp = b;
            b = a;
            a = temp;
        }
        // 깊이 맞춰주기
        int gap = depth[b] - depth[a];
        for (int i = 0; i < 15; i++){
            if ((gap & (1 << i)) > 0){
                b = dp[i][b];
            }
        }
        // 높이가 같아졌을 때 a 와 b가 같다면 return
        if (a == b){
            return a;
        }
        // 다를 경우 둘이 같아질 때까지 올라감
        for (int i = 14; i >= 0; i--){
            if (dp[i][a] != dp[i][b]){
                a = dp[i][a];
                b = dp[i][b];
            }
        }

        return dp[0][a];
    }
}
