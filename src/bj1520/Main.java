package bj1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] map;
    static int n, m;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        m = Integer.parseInt(nm[0]);
        n = Integer.parseInt(nm[1]);
        map = new int[m][n];
        dp = new int[m][n];

        for (int i = 0; i < m; i++){
            String[] mapLine = br.readLine().split(" ");
            for (int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(mapLine[j]);
                dp[i][j] = -1;
            }
        }

        System.out.println(downhillRoadCnt(0, 0));
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int downhillRoadCnt(int x, int y){
        if (x == n-1 && y == m-1){
            return 1;
        }

        if(dp[y][x] != -1){
            return dp[y][x];
        }

        dp[y][x] = 0;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(0 <= nx && nx < n && 0 <= ny && ny < m){
                if(map[y][x] > map[ny][nx]){
                    dp[y][x] += downhillRoadCnt(nx, ny);
                }
            }
        }

        return dp[y][x];
    }
}
