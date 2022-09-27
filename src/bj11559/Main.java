package bj11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> bfsQueue, tempQueue, boomQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];
        visited = new boolean[12][6];
        bfsQueue = new LinkedList<>();
        tempQueue = new LinkedList<>();
        boomQueue = new LinkedList<>();

        for (int y = 0; y < 12; y++){
            String[] mapLine = br.readLine().split("");
            for (int x = 0; x < 6; x++){
                char c = mapLine[x].charAt(0);
                map[y][x] = c;
            }
        }

        int result = 0;
        while(true){
            for (int i = 0; i < 12; i++){
                Arrays.fill(visited[i], false);
            }

            for (int y = 0; y < 12; y++){
                for (int x = 0; x < 6; x++){
                    if (map[y][x] != '.' && !visited[y][x]){
                        boom(new int[]{y, x});
                    }
                }
            }

            if (boomQueue.isEmpty()) break;
            result++;

            while(!boomQueue.isEmpty()){
                int[] boomPoint = boomQueue.poll();
                map[boomPoint[0]][boomPoint[1]] = '.';
            }

            move();
        }

        System.out.println(result);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void boom(int[] start){
        bfsQueue.clear();
        tempQueue.clear();
        bfsQueue.offer(start);
        visited[start[0]][start[1]] = true;

        while(!bfsQueue.isEmpty()){
            int[] now = bfsQueue.poll();

            tempQueue.offer(now);

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];

                if (0 <= nx && nx < 6 && 0 <= ny && ny < 12){
                    if (visited[ny][nx] || map[ny][nx] != map[now[0]][now[1]]) continue;
                    visited[ny][nx] = true;
                    bfsQueue.offer(new int[]{ny, nx});
                }
            }
        }

        if (tempQueue.size() >= 4){
            while(!tempQueue.isEmpty()){
                boomQueue.offer(tempQueue.poll());
            }
        }
    }

    static void move(){
        for (int y = 11; y >= 0; y--){
            for (int x = 5; x >= 0; x--){
                if (map[y][x] == '.') continue;
                int tempY = y;
                for (int ny = y + 1; ny < 12; ny++){
                    if (map[ny][x] == '.'){
                        map[ny][x] = map[tempY][x];
                        map[tempY][x] = '.';
                        tempY = ny;
                    }
                    else{
                        break;
                    }
                }
            }
        }
    }
}
