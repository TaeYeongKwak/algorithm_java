package bj1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static boolean[] alpha;
    static char[][] board;
    static int result = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alpha = new boolean[26];
        board = new char[R + 1][C + 1];

        for (int r = 1; r < R + 1; r++){
            char[] boardLine = br.readLine().toCharArray();
            for (int c = 1; c < C + 1; c++){
                board[r][c] = boardLine[c - 1];
            }
        }
        dfs(new int[]{1, 1}, 1);
        System.out.println(result);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void dfs(int[] point, int cnt){
        int a = (int)(board[point[0]][point[1]]) - 'A';
        if (alpha[a]){
            result = Math.max(cnt - 1, result);
            return;
        }
        alpha[a] = true;
        for (int i = 0; i < 4; i++){
            int nx = point[1] + dx[i];
            int ny = point[0] + dy[i];

            if (0 < nx && nx <= C && 0 < ny && ny <= R){
                dfs(new int[]{ny, nx}, cnt + 1);
            }
        }
        alpha[a] = false;
    }
}

