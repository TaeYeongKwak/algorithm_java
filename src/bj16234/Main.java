package bj16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> queue;
    static Queue<int[]> moveQ;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue = new LinkedList<>();
        moveQ = new LinkedList<>();

        int count = 0;
        flag = true;
        while(flag){
            flag = false;
            move();
            if (flag){
                count++;
            }
        }

        System.out.println(count);
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void move(){
        queue.clear();
        for (int i = 0; i < N; i++){
            Arrays.fill(visited[i], false);
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (!visited[i][j]){
                    bfs(new int[]{i, j});
                }
            }
        }
    }

    static void bfs(int[] start){
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        moveQ.clear();
        int sum = map[start[0]][start[1]];
        moveQ.offer(start);

        while (!queue.isEmpty()){
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N){
                    if (visited[ny][nx]) continue;

                    int gap = Math.abs(map[now[0]][now[1]] - map[ny][nx]);
                    if (L <= gap && gap <= R){
                        flag = true;
                        int[] saveP = {ny, nx};
                        queue.offer(saveP);
                        moveQ.offer(saveP);
                        visited[ny][nx] = true;
                        sum += map[ny][nx];
                    }
                }
            }
        }

        int size = moveQ.size();

        if (size > 0){
            int population = sum / size;

            while(!moveQ.isEmpty()){
                int[] p = moveQ.poll();
                map[p[0]][p[1]] = population;
            }
        }
    }
}
