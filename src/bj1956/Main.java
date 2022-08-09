package bj1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int v = 1; v < V + 1; v++){
            graph[v] = new ArrayList<>();
        }

        for (int e = 1; e < E + 1; e++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, dist});
        }

        int result = Integer.MAX_VALUE;
        for (int v = 1; v < V + 1; v++){
            result = Math.min(dijkstra(v), result);
        }
        System.out.println((result == Integer.MAX_VALUE)? -1 : result);
    }

    static int dijkstra(int start){
        int[] dist = new int[V + 1];
        for (int v = 1; v < V + 1; v++){
            dist[v] = Integer.MAX_VALUE;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.offer(new int[]{start, 0});

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            if (dist[now[0]] < now[1]) {
                continue;
            }

            for (int[] next : graph[now[0]]){
                if (dist[next[0]] > now[1] + next[1]){
                    dist[next[0]] = now[1] + next[1];
                    queue.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        return dist[start];
    }
}
