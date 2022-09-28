package bj2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> bfsQ, cheeseBorder;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        bfsQ = new LinkedList<>();
        cheeseBorder = new LinkedList<>();

        visited = new boolean[N][M];
        bfsQ.offer(new int[]{0, 0});
        visited[0][0] = true;

        int time = 0;
        int cheese = 0;
        while(true){
            if (getCheeseBorder() == 0){
                break;
            }
            cheese = meltCheese();
            time++;
        }

        System.out.println(time);
        System.out.println(cheese);
    }

    static int getCheeseBorder(){
        while (!bfsQ.isEmpty()){
            int[] now = bfsQ.poll();

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];
                if (0 <= nx && nx < M && 0 <= ny && ny < N){
                    if (visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    int[] next = new int[]{ny, nx};
                    if (map[ny][nx] == 1){
                        cheeseBorder.offer(next);
                    }
                    else if (map[ny][nx] == 0){
                        bfsQ.offer(next);
                    }
                }
            }
        }

        return cheeseBorder.size();
    }

    static int meltCheese(){
        while (!cheeseBorder.isEmpty()){
            int[] now = cheeseBorder.poll();
            map[now[0]][now[1]] = 0;
            bfsQ.offer(now);
        }
        return bfsQ.size();
    }

}
