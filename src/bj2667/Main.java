package bj2667;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int n;
    static int count;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void dfs(int x, int y){
        visited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n){
                if (map[nx][ny] == 1 && !visited[nx][ny]){
                    dfs(nx, ny);
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            char[] mapLine = sc.next().toCharArray();
            for(int j = 0; j < n; j++){
                map[i][j] = mapLine[j] - '0';
            }
        }

        count = 0;
        ArrayList<Integer> aparts = new ArrayList<Integer>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (map[i][j] == 1 && !visited[i][j]){
                    count = 1;
                    dfs(i, j);
                    aparts.add(count);
                }
            }
        }

        Collections.sort(aparts);
        System.out.println(aparts.size());
        for (int a : aparts) System.out.println(a);
    }
}
