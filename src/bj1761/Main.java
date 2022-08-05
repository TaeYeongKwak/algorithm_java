package bj1761;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<int[]>[] graph;
    static int[] depth, dist;
    static int[][] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, dist});
            graph[to].add(new int[]{from, dist});
        }

        depth = new int[N + 1];
        dist = new int[N + 1];
        parent = new int[17][N + 1];

        init();

        for (int i = 1; i < 17; i++){
            for (int j = 1; j < N + 1; j++){
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int result = dist[a] + dist[b] - 2 * dist[lca(a, b)];
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void init(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            for (int[] next : graph[now]){
                if (!visited[next[0]]){
                    visited[next[0]] = true;
                    parent[0][next[0]] = now;
                    depth[next[0]] = depth[now] + 1;
                    dist[next[0]] = dist[now] + next[1];
                    queue.offer(next[0]);
                }
            }
        }
    }

    static int lca(int a, int b){
        if (depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        int gap = depth[a] - depth[b];
        for (int i = 0; i < 17; i++){
            if ((gap & (1 << i)) > 0){
                a = parent[i][a];
            }
        }

        if (a == b){
            return a;
        }

        for (int i = 16; i >= 0; i--){
            if (parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }
}
