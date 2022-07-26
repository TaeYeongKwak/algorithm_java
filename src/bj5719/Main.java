package bj5719;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, S, E;
    static ArrayList<int[]>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while(true){
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0){
                break;
            }

            st = new StringTokenizer(br.readLine());
            dist = new int[N];

            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N];
            for (int i = 0; i < N; i++){
                graph[i] = new ArrayList<>();
                dist[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                graph[U].add(new int[]{V, P});
            }


        }
    }

    static void dijkstra(){
        PriorityQueue<int[]> heap = new PriorityQueue<>();
        heap.offer(new int[]{S, 0});
        dist[S][S] = 0;

        while (!heap.isEmpty()){
            int[] now = heap.poll();

            for (int[] next : graph[now[0]]){
                if (dist[next[0]] > dist[now[0]][] + next[1]){

                }
            }
        }

    }


}
