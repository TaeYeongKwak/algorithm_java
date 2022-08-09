package bj2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1][2];
        for (int n = 1; n < N + 1; n++){
            String[] mapLine = br.readLine().split("");
            for (int m = 1; m < M + 1; m++){
                map[n][m] = Integer.parseInt(mapLine[m - 1]);
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 1, 1, false));
        visited[1][1][0] = true;

        int result = -1;
        while(!queue.isEmpty()){
            Node now = queue.poll();

            if (now.x == M && now.y == N){
                result = now.dist;
                break;
            }

            for (int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 < ny && ny <= N && 0 < nx && nx <= M){
                    int idx = (now.flag)? 1 : 0;
                    if (!visited[ny][nx][idx]){
                        visited[ny][nx][idx] = true;
                        if (map[ny][nx] == 0){
                            queue.offer(new Node(nx, ny, now.dist + 1, now.flag));
                        }
                        if (map[ny][nx] == 1 && !now.flag){
                            queue.offer(new Node(nx, ny, now.dist + 1, true));
                        }
                    }

                }
            }
        }

        System.out.println(result);
    }
}

class Node{
    int x;
    int y;
    int dist;
    boolean flag;

    public Node(int x, int y, int dist, boolean flag) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.flag = flag;
    }

}
