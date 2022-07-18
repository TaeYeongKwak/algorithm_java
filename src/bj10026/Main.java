package bj10026;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int n;
    static char[][][] map;
    static boolean[][] visited;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n][2];
        visited = new boolean[n][n];
        result = new int[2];
        for (int i = 0; i < n; i++){
            char[] mapLine = br.readLine().toCharArray();
            for (int j = 0; j < n; j++){
                map[i][j][0] = mapLine[j];
                map[i][j][1] = (mapLine[j] == 'G')? 'R' : mapLine[j];
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (!visited[i][j]){
                    bfs(new int[]{j, i});
                }
            }
        }

        visited = new boolean[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (!visited[i][j]){
                    bfs2(new int[]{j, i});
                }
            }
        }

        bw.write( result[0] + " " + result[1]);

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(int[] point){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{point[0], point[1]});
        visited[point[1]][point[0]] = true;

        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            for (int i = 0; i < 4; i++){
                int nx = temp[0] + dx[i];
                int ny = temp[1] + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n){
                    if (!visited[ny][nx] && map[ny][nx][0] == map[temp[1]][temp[0]][0]){
                        visited[ny][nx] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        result[0]++;
    }

    static void bfs2(int[] point){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{point[0], point[1]});
        visited[point[1]][point[0]] = true;

        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            for (int i = 0; i < 4; i++){
                int nx = temp[0] + dx[i];
                int ny = temp[1] + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n){
                    if (!visited[ny][nx] && map[ny][nx][1] == map[temp[1]][temp[0]][1]){
                        visited[ny][nx] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        result[1]++;
    }
}
