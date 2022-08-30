package bj17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, T;
    static int[][] map;
    static List<int[]> machine;
    static Queue<int[]> dust;
    static int[][] lazyMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];
        lazyMap = new int[R + 1][C + 1];

        machine = new ArrayList<>();
        dust = new LinkedList<>();

        for (int r = 1; r < R + 1; r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c < C + 1; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == -1){
                    machine.add(new int[]{r, c});
                }
                else if (map[r][c] > 0){
                    dust.offer(new int[]{r, c});
                }
            }
        }

        for (int i = 0; i < T; i++){
            moveDust();
            operateMachine();
            dustCheck();
        }

        System.out.println(sumDust());
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void moveDust(){
        while(!dust.isEmpty()){
            int[] now = dust.poll();

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];

                int spread = map[now[0]][now[1]] / 5;
                if (0 < nx && nx < C + 1 && 0 < ny && ny < R + 1){
                    if (map[ny][nx] == -1) continue;
                    lazyMap[ny][nx] += spread;
                    lazyMap[now[0]][now[1]] -= spread;
                }
            }
        }

        for (int r = 1; r < R + 1; r++){
            for (int c = 1; c < C + 1; c++){
                if (lazyMap[r][c] != 0){
                    map[r][c] += lazyMap[r][c];
                    lazyMap[r][c] = 0;
                }
            }
        }
    }

    static void operateMachine(){
        int[] up = machine.get(0);
        // 왼쪽
        for (int y = up[0] - 1; y > 1; y--){
            map[y][up[1]] = map[y - 1][up[1]];
        }
        // 위
        for (int x = up[1]; x < C; x++){
            map[1][x] = map[1][x + 1];
        }
        // 오른쪽
        for (int y = 1; y < up[0]; y++){
            map[y][C] = map[y + 1][C];
        }
        //아래
        for (int x = C; x > up[1]; x--){
            map[up[0]][x] = map[up[0]][x - 1];
            if (map[up[0]][x] == -1){
                map[up[0]][x] = 0;
            }
        }

        int[] down = machine.get(1);
        // 왼쪽
        for (int y = down[0] + 1; y < R; y++){
            map[y][down[1]] = map[y + 1][down[1]];
        }
        //아래
        for (int x = down[1]; x < C; x++){
            map[R][x] = map[R][x + 1];
        }
        // 오른쪽
        for (int y = R; y > down[0]; y--){
            map[y][C] = map[y - 1][C];
        }
        // 위
        for (int x = C; x > down[1]; x--){
            map[down[0]][x] = map[down[0]][x - 1];
            if (map[down[0]][x] == -1){
                map[down[0]][x] = 0;
            }
        }
    }

    static void dustCheck(){
        for (int r = 1; r < R + 1; r++){
            for (int c = 1; c < C + 1; c++){
                if (map[r][c] > 0){
                    dust.offer(new int[]{r, c});
                }
            }
        }
    }

    static int sumDust(){
        int dust = 0;
        for (int r = 1; r < R + 1; r++){
            for (int c = 1; c < C + 1; c++){
                if (map[r][c] > 0){
                    dust += map[r][c];
                }
            }
        }
        return dust;
    }

}
