package bj11437;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[][] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        depth = new int[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        parent = new int[18][N + 1];

        init();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(lca(a, b) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void init(){
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            for (int next : graph[now]){
                if (visited[next]) continue;
                visited[next] = true;
                // 깊이 저장
                depth[next] = depth[now] + 1;
                // 직계 부모 저장
                parent[0][next] = now;
                queue.offer(next);
            }
        }

        //dp 저장
        for (int i = 1; i < 18; i++){
            for (int j = 1; j < N + 1; j++){
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
    }

    static int lca(int a, int b){
        // a의 깊이가 더 깊다고 가정
        if (depth[a] < depth[b]){
            int temp = b;
            b = a;
            a = temp;
        }

        int gap = depth[a] - depth[b];
        for (int i = 0; i < 18; i++){
            if ((gap & (1 << i)) > 0){
                a = parent[i][a];
            }
        }

        if (a == b) return a;

        for (int i = 17; i >= 0; i--){
            if (parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }
}
