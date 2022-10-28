package bj1937;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static Queue<int[]> queue;
    static int[][] dp;
    static boolean[][] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue = new LinkedList<>();
        visited = new boolean[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (visited[i][j]) continue;
                dfs(new int[]{i, j});
            }
        }

        System.out.println(result);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int dfs(int[] now){
        visited[now[0]][now[1]] = true;
        int max = 0;
        for (int i = 0; i < 4; i++){
            int nx = now[1] + dx[i];
            int ny = now[0] + dy[i];

            if (0 < nx && nx < N + 1 && 0 < ny && ny < N + 1){
                if (map[ny][nx] > map[now[0]][now[1]]){
                    if (visited[ny][nx]){
                        max = Math.max(dp[ny][nx], max);
                    }
                    else{
                        visited[ny][nx] = true;
                        max = Math.max(dfs(new int[]{ny, nx}), max);
                    }
                }
            }
        }

        dp[now[0]][now[1]] = max + 1;
        result = Math.max(result, dp[now[0]][now[1]]);
        return dp[now[0]][now[1]];
    }
}
