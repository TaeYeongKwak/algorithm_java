package bj2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][3];
        int[][][] dp = new int[n][3][2];

        // map 넣기
        StringTokenizer mapLine = null;
        for (int i = 0; i < n; i++){
            mapLine = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++){
                map[i][j] = Integer.parseInt(mapLine.nextToken());
            }
        }

        //dp 초기값 정리
        dp[0][0][0] = dp[0][0][1] = map[0][0];
        dp[0][1][0] = dp[0][1][1] = map[0][1];
        dp[0][2][0] = dp[0][2][1] = map[0][2];

        for (int i = 1; i < n; i++){
            dp[i][0][1] = Integer.MAX_VALUE;
            dp[i][1][1] = Integer.MAX_VALUE;
            dp[i][2][1] = Integer.MAX_VALUE;
        }

        //dp 값 정리
        for (int i = 1; i < n; i++){
            for (int j = 0; j < 3; j++){
                for (int k = -1; k < 2; k++) {
                    int x = j + k;
                    if (0 <= x && x < 3){
                        dp[i][j][0] = Math.max(dp[i - 1][x][0] + map[i][j], dp[i][j][0]);
                        dp[i][j][1] = Math.min(dp[i - 1][x][1] + map[i][j], dp[i][j][1]);
                    }
                }
            }
        }

        //최대값, 최소값 구하기
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++){
            max = Math.max(dp[n-1][i][0], max);
            min = Math.min(dp[n-1][i][1], min);
        }

        System.out.println(max + " " + min);
    }
}
