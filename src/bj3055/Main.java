package bj3055;

import java.io.*;
import java.util.*;

public class Main {

    static int r, c;
    static char[][] map;
    static int[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rc = br.readLine().split(" ");
        r = Integer.parseInt(rc[0]);
        c = Integer.parseInt(rc[1]);

        String result = "KAKTUS";
        map = new char[r][c];
        visited = new int[r][c];

        int[] start = new int[2];
        int[] end = new int[2];
        List<int[]> water = new ArrayList<>();

        for (int y = 0; y < r; y++){
            char[] mapLine = br.readLine().toCharArray();
            for (int x = 0; x < c; x++){
                map[y][x] = mapLine[x];
                visited[y][x] = -1;
                if (mapLine[x] == 'S')
                    start = new int[]{x, y};
                else if (mapLine[x] == 'D')
                    end = new int[]{x, y};
                else if (mapLine[x] == '*')
                    water.add(new int[]{x, y});
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});
        visited[start[1]][start[0]] = 0;



        while(!queue.isEmpty()){
            //물
            int currentWaterSize = water.size();
            for (int w = 0; w < currentWaterSize; w++){
                for (int i = 0; i < 4; i++){
                    int nx = water.get(w)[0] + dx[i];
                    int ny = water.get(w)[1] + dy[i];

                    if (0 <= nx && nx < c && 0 <= ny && ny < r){
                        if (map[ny][nx] == '.' || map[ny][nx] == 'S'){
                            map[ny][nx] = '*';
                            water.add(new int[]{nx, ny});
                        }
                    }
                }
            }

            int currentQueueSize = queue.size();
            for (int q = 0; q < currentQueueSize; q++){
                //고슴도치
                int[] temp = queue.poll();
                if (temp[0] == end[0] && temp[1] == end[1]){
                    result = String.valueOf(temp[2]);
                    break;
                }

                for (int i = 0; i < 4; i++){
                    int nx = temp[0] + dx[i];
                    int ny = temp[1] + dy[i];

                    if (0 <= nx && nx < c && 0 <= ny && ny < r){
                        if (visited[ny][nx] == -1 && (map[ny][nx] == '.' || map[ny][nx] == 'D')){
                            visited[ny][nx] = temp[2] + 1;
                            queue.offer(new int[]{nx, ny, temp[2] + 1});
                        }
                    }
                }
            }

        }

        System.out.println(result);
    }
}
