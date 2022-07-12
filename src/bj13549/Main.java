package bj13549;

import java.util.PriorityQueue;
import java.util.Scanner;

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

    static int n, k;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        int size = Math.max(n, k);
        dist = new int[(2 * size) + 1];
        visited = new boolean[(2 * size) + 1];

        for (int i = 0; i < dist.length; i++){
            dist[i] = Integer.MAX_VALUE;
        }

        dijkstra(n);

        System.out.println(dist[k]);
    }

    static void dijkstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()){
            Node tempNode = queue.poll();

            if (visited[tempNode.v])
                continue;

            visited[tempNode.v] = true;

            Node[] nextList = {new Node(2 * tempNode.v, tempNode.w), new Node(tempNode.v-1, tempNode.w + 1), new Node(tempNode.v + 1, tempNode.w + 1)};
            for (Node next : nextList){
                if(next.v >= 0 && next.v < dist.length){
                    if (dist[next.v] > next.w){
                        dist[next.v] = next.w;
                        queue.offer(next);
                    }
                }
            }
        }

    }
}
