package bj14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        Vacuum vacuum = new Vacuum(x, y, d, N, M, map);
        vacuum.cleaning();
        System.out.println(vacuum.getCount());
    }
}

class Vacuum{
    int x, y, d;
    int N, M;
    int[][] map;
    boolean[][] visited;
    int count;

    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};

    public Vacuum(int x, int y, int d, int n, int m, int[][] map) {
        this.x = x;
        this.y = y;
        this.d = d;
        N = n;
        M = m;
        this.map = map;
        this.visited = new boolean[N][M];
    }

    public void cleaning(){
        while(true){
            // 현재 위치를 청소한다.
            if (!visited[y][x]){
                visited[y][x] = true;
                count++;
            }

            // 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
            boolean flag = true;
            for (int i = 3; i >= 0; i--){
                int direction = (d + i) % 4;
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                // 해당 방향에 청소할 수 있는 공간이 존재한다면, 해당 방향으로 회전한 후 그 칸으로 이동한다.
                if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                    if (!visited[ny][nx] && map[ny][nx] == 0) {
                        x = nx;
                        y = ny;
                        d = direction;
                        flag = false;
                        break;
                    }
                }
            }
            // 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
            if (flag){
                int direction = (d + 2) % 4;
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                    if (map[ny][nx] == 0) {
                        x = nx;
                        y = ny;
                    }
                    // 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
                    else if (map[ny][nx] == 1){
                        break;
                    }
                }
            }
        }
    }

    public int getCount(){
        return count;
    }
}


