package bj1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<int[]>[] graph;
    static int[][] dist;
    static int[] must;
    static final int INF = 200000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        dist = new int[N + 1][N + 1];
        for(int n = 1; n < N + 1; n++){
            graph[n] = new ArrayList<>();
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, cost});
            graph[to].add(new int[]{from, cost});
        }

        must = new int[2];
        st = new StringTokenizer(br.readLine());
        must[0] = Integer.parseInt(st.nextToken());
        must[1] = Integer.parseInt(st.nextToken());

        solution(1);
        solution(must[0]);
        solution(must[1]);

        int x = dist[1][must[0]] + dist[must[0]][must[1]] + dist[must[1]][N];
        int y = dist[1][must[1]] + dist[must[1]][must[0]] + dist[must[0]][N];
        int answer = Math.min(x, y);
        System.out.println(answer >= INF? -1 : answer);
    }

    static void solution(int start){
        Arrays.fill(dist[start], INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start][start] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if (dist[start][now.v] < now.weight){
                continue;
            }

            for(int[] next : graph[now.v]){
                if (now.weight + next[1] < dist[start][next[0]]) {
                    dist[start][next[0]] = now.weight + next[1];
                    pq.offer(new Edge(next[0], now.weight + next[1]));
                }
            }
        }
    }
}

class Edge implements Comparable<Edge>{
    int v;
    int weight;

    public Edge(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}
