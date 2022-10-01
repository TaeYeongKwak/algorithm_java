package bj18405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, S;
    static int X, Y;
    static int[][] map;
    static boolean[][] visited;

    static PriorityQueue<Node> pq;
    static Queue<Node> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        pq = new PriorityQueue<>();
        queue = new LinkedList<>();

        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0){
                    pq.offer(new Node(j, i, map[i][j]));
                    visited[i][j] = true;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        for (int s = 0; s < S; s++){
            spread();
        }

        System.out.println(map[X][Y]);

    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void spread(){
        while(!pq.isEmpty()){
            Node now = pq.poll();

            for (int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 < nx && nx < N + 1 && 0 < ny && ny < N + 1){
                    if (visited[ny][nx] || map[ny][nx] != 0) continue;
                    visited[ny][nx] = true;
                    map[ny][nx] = now.k;
                    queue.offer(new Node(nx, ny, now.k));
                }
            }
        }

        while(!queue.isEmpty()){
            pq.offer(queue.poll());
        }
    }
}

class Node implements Comparable<Node>{

    int x, y;
    int k;

    public Node(int x, int y, int k) {
        this.x = x;
        this.y = y;
        this.k = k;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(k, o.k);
    }
}
