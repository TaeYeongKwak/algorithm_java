package bj1103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n,m;
    static String[][] map;
    static int result;
    static int[][] dp;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        result = -1;

        map = new String[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];

        for (int y = 0; y < n; y++){
            String mapLine = br.readLine();
            for (int x = 0; x < m; x++){
                map[y][x] = String.valueOf(mapLine.charAt(x));
            }
        }

        visited[0][0] = true;
        dfs(new int[]{0, 0});

        System.out.println(result);
    }

    static void dfs(int[] point){
        // 구멍에 빠질 경우
        if (map[point[1]][point[0]].equals("H")){
            result = Math.max(result, dp[point[1]][point[0]]);
            return;
        }
        // else
        for (int i = 0; i < 4; i++){
            int k = Integer.parseInt(map[point[1]][point[0]]);
            int nx = point[0] + (dx[i] * k);
            int ny = point[1] + (dy[i] * k);

            if (0 <= nx && nx < m && 0 <= ny && ny < n){
                if(visited[ny][nx]){
                    System.out.println(-1);
                    System.exit(0);
                }

                if (dp[ny][nx] < dp[point[1]][point[0]] + 1){
                    visited[ny][nx] = true;
                    dp[ny][nx] = dp[point[1]][point[0]] + 1;
                    dfs(new int[]{nx, ny});
                    visited[ny][nx] = false;
                }
            }
            //밖으로 나갈 경우
            else{
                result = Math.max(result, dp[point[1]][point[0]] + 1);
            }
        }
    }
}
