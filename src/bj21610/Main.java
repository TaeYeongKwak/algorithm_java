package bj21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] bucket;
    static Queue<Cloud> clouds;
    static Queue<int[]> addWaterPoints;
    static boolean[][] visited;

    static int[] dx = {-1, -1, 1, 1};
    static int[] dy = {-1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bucket = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++){
                bucket[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds = new LinkedList<>();
        addWaterPoints = new LinkedList<>();
        for (int n = N; n >= N-1 ; n--){
            for (int i = 1; i <= 2; i++){
                clouds.offer(new Cloud(i, n, N));
            }
        }

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            moveCloud(d, s);
        }

        System.out.println(sumBucket());
    }

    static void moveCloud(int d, int s){
        // 0. 초기화
        for (int i = 1; i < N + 1; i++){
            Arrays.fill(visited[i], false);
        }

        // 1. 모든 구름이 이동한다.
        while(!clouds.isEmpty()){
            Cloud cloud = clouds.poll();
            cloud.move(d, s);
            // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
            bucket[cloud.y][cloud.x]++;
            addWaterPoints.offer(new int[]{cloud.y, cloud.x});
            visited[cloud.y][cloud.x] = true;
        }

        // 3. 물복사 버그
        while(!addWaterPoints.isEmpty()){
            int[] point = addWaterPoints.poll();

            int count = 0;
            for (int i = 0; i < 4; i++){
                int nx = point[1] + dx[i];
                int ny = point[0] + dy[i];

                if (0 < nx && nx < N + 1 && 0 < ny && ny < N + 1){
                    if (bucket[ny][nx] > 0){
                        count++;
                    }
                }
            }

            bucket[point[0]][point[1]] += count;
        }

        // 4. 구름 생성
        for (int i = 1; i < N + 1; i++){
            for (int j = 1; j < N + 1; j++){
                if (!visited[i][j] && bucket[i][j] >= 2){
                    bucket[i][j] -= 2;
                    clouds.offer(new Cloud(j, i, N));
                }
            }
        }
    }

    static int sumBucket(){
        int sum = 0;
        for (int i = 1; i < N + 1; i++){
            for (int j = 1; j < N + 1; j++){
                sum += bucket[i][j];
            }
        }
        return sum;
    }
}

class Cloud{
    int x, y;
    int N;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public Cloud(int x, int y, int N) {
        this.x = x;
        this.y = y;
        this.N = N;
    }

    public void move(int d, int s){
        x = (x + dx[d - 1] * s) % N;
        x = (x <= 0)? x + N : x;
        y = (y + dy[d - 1] * s) % N;
        y = (y <= 0)? y + N : y;
    }
}
