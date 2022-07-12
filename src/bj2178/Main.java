package bj2178;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n,m;
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        map = new char[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];

        for (int y = 1; y < n + 1; y++){
            char[] mapLine = br.readLine().toCharArray();
            for (int x = 1; x < m + 1; x++){
                map[y][x] = mapLine[x-1];
            }
        }

        System.out.println(bfs(new int[]{1, 1}));
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int bfs(int[] start){
        int result = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 1});
        visited[start[1]][start[0]] = true;

        while(!queue.isEmpty()){
            int[] temp = queue.poll();

            for (int i = 0; i < 4; i++){
                int nx = temp[0] + dx[i];
                int ny = temp[1] + dy[i];

                if(0 < nx && nx < m + 1 && 0 < ny && ny < n + 1){
                    if (map[ny][nx] == '1' && !visited[ny][nx]){
                        queue.offer(new int[]{nx, ny, temp[2] + 1});
                        visited[ny][nx] = true;
                        if (ny == n && nx == m){
                            result = temp[2] + 1;
                        }
                    }
                }
            }
        }
        return result;
    }
}
