package bj5719;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, S, E;
    static int[][] graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0){
                break;
            }

            st = new StringTokenizer(br.readLine());
            dist = new int[N];
            visited = new boolean[N];

            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new int[N][N];

            for (int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                graph[U][V] = P;
            }

            dijkstra();
            removeEdge();
            dijkstra();

            bw.write(((dist[E] == 100000000)? "-1" : dist[E]) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void removeEdge(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(E);

        while(!queue.isEmpty()){
            int now = queue.poll();

            for (int i = 0; i < N; i++){
                if (graph[i][now] != 0 && dist[i] == dist[now] - graph[i][now]){
                    graph[i][now] = 0;
                    queue.add(i);
                }
            }
        }

    }

    static void dijkstra(){
        for (int i = 0; i < N; i++){
            dist[i] = 100000000;
        }

        PriorityQueue<Node> heap = new PriorityQueue<Node>(Comparator.comparingInt(o -> o.w));
        heap.offer(new Node(S, 0));
        dist[S] = 0;

        while (!heap.isEmpty()){
            Node now = heap.poll();
            if (dist[now.v] < now.w)
                continue;

            int[] next = graph[now.v];
            for (int i = 0; i < N; i++){
                if (graph[now.v][i] != 0){
                    if (dist[i] > dist[now.v] + next[i]){
                        dist[i] = dist[now.v] + next[i];
                        heap.offer(new Node(i, dist[now.v] + next[i]));
                    }
                }
            }
        }
    }

}

class Node{
    int v;
    int w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}