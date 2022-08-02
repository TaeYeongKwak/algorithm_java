package bj17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, result;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        dfs(1, 2, 1);
        System.out.println(result);

    }

    public static void dfs(int x, int y, int type) {
        if (x == N && y == N) {
            result++;
            return;
        }

        switch (type) {
            case 1:
                if (y + 1 <= N && map[x][y + 1] == 0) {
                    dfs(x, y + 1, 1);
                }
                break;
            case 2:
                if (x + 1 <= N && map[x + 1][y] == 0) {
                    dfs(x + 1, y, 2);
                }
                break;
            case 3:
                if (y + 1 <= N && map[x][y + 1] == 0) {
                    dfs(x, y + 1, 1);
                }
                if (x + 1 <= N && map[x + 1][y] == 0) {
                    dfs(x + 1, y, 2);
                }
                break;
        }

        if (y + 1 <= N && x + 1 <= N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
            dfs(x + 1, y + 1, 3);
        }
    }
}