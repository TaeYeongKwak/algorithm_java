package bj14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int n = 1; n < N + 1; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m < M + 1; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 블록별로 dfs를 돌려서 나오는 4개의 블록을 합한 값의 최대값을 result에 집어넣는다.
        int result = 0;
        for (int n = 1; n < N + 1; n++){
            for (int m = 1; m < M + 1; m++){
                if (!visited[n][m]){
                    visited[n][m] = true;
                    result = Math.max(dfs(1, new int[]{n, m}), result);
                    visited[n][m] = false;
                }
            }
        }

        System.out.println(result);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int dfs(int r, int[] point){
        // 블록 크기가 4개일 경우 해당 블록의 값을 반환
        if (r == 4){
            return map[point[0]][point[1]];
        }
        // 전체 블록의 합 4개의 방향으로 dfs를 돌려서 블록 값을 확인
        int sum = 0;
        for (int i = 0; i < 4; i++){
            int nx = point[1] + dx[i];
            int ny = point[0] + dy[i];
            if (0 < nx && nx < M + 1 && 0 < ny && ny < N + 1){
                if (!visited[ny][nx]){
                    visited[ny][nx] = true;
                    // 현재 블록값과 다음으로 올 블록 값들을 합함
                    sum = Math.max(map[point[0]][point[1]] + dfs(r + 1, new int[]{ny, nx}), sum);
                    visited[ny][nx] = false;
                }
            }
        }
        
        // 합한 값을 반환
        return sum;
    }
}
