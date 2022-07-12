package bj1753;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Node implements Comparable<Node>{
    int v;
    int w;

    Node(int v, int w){
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.w, o.w);
    }
}

public class Main {

    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ve = br.readLine().split(" ");
        int vCnt = Integer.parseInt(ve[0]);
        int e = Integer.parseInt(ve[1]);
        int k = Integer.parseInt(br.readLine());

        graph = new ArrayList[vCnt + 1];
        dist = new int[vCnt + 1];
        visited = new boolean[vCnt + 1];

        for (int i = 1; i < vCnt + 1; i++){
            graph[i] = new ArrayList<Node>();
            dist[i] = Integer.MAX_VALUE;
        }

        int u, v, w = 0;
        for (int i = 0; i < e; i++){
            String[] uvw = br.readLine().split(" ");
            u = Integer.parseInt(uvw[0]);
            v = Integer.parseInt(uvw[1]);
            w = Integer.parseInt(uvw[2]);

            graph[u].add(new Node(v, w));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        dijkstra(k);

        for(int j = 1; j < vCnt + 1; j++){
            if (dist[j] == Integer.MAX_VALUE)
                bw.write( "INF" + "\n");
            else
                bw.write( dist[j] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (visited[now.v])
                continue;

            visited[now.v] = true;
            for (Node next : graph[now.v]) {
                if (dist[next.v] > dist[now.v] + next.w) {
                    dist[next.v] = dist[now.v] + next.w;
                    queue.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}
