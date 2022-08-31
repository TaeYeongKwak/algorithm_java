package bj17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int R, C, M;
    static Shark[] sharks;
    static int[][] map;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sharks = new Shark[M + 1];
        map = new int[R + 1][C + 1];
        result = 0;

        for (int m = 1; m < M + 1; m++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks[m] = new Shark(m, r, c, s, d, z);
            map[r][c] = m;
        }

        for (int h = 1; h < C + 1; h++){
            huntingShark(h);
            moveShark();
        }

        System.out.println(result);
    }

    static void huntingShark(int c){
        int sharkIdx = 0;
        for (int r = 1; r < R + 1; r++){
            sharkIdx = map[r][c];
            if (sharkIdx == 0) continue;

            Shark shark = sharks[sharkIdx];
            if (!shark.isDie){
                result += shark.size;
                shark.isDie = true;
                break;
            }
        }
    }

    static int[] dx = {0, 0, 0, 1, -1};
    static int[] dy = {0, -1, 1, 0, 0};

    static void moveShark(){
        // 맵을 초기화해준다.
        for (int r = 1; r < R + 1; r++){
            Arrays.fill(map[r], 0);
        }

        for (int i = 1; i < M + 1; i++){
            Shark shark = sharks[i];
            // 이미 상어가 죽거나 잡혔을 때는 넘어간다.
            if (shark.isDie) continue;

            int direction = shark.direction;
            int nx = shark.x + (dx[direction] * shark.speed);
            int ny = shark.y + (dy[direction] * shark.speed);

            // 벽을 봤을 경우
            while(true){
//                System.out.println(ny + " " + nx);
                // 위로 넘어갔을 때
                if (ny <= 0){
                    ny = 2 - ny;
                    direction = 2;
                }
                // 아래로 넘어갔을 때
                if(ny > R){
                    ny = (2 * R) - ny;
                    direction = 1;
                }
                // 오른쪽으로 넘어갔을 때
                if (nx > C){
                    nx = (2 * C) - nx;
                    direction = 4;
                }
                // 왼쪽으로 넘어갔을 때
                if (nx <= 0){
                    nx = 2 - nx;
                    direction = 3;
                }
                if (0 < nx && nx < C + 1 && 0 < ny && ny < R + 1){
                    break;
                }
            }

            shark.x = nx;
            shark.y = ny;
            shark.direction = direction;

            // 이미 해당 자리에 상어가 존재한다면,
            if (map[ny][nx] != 0){
                int nowSharkSize = shark.size;
                int lastSharkSize = sharks[map[ny][nx]].size;

                // 현재 상어의 크기가 더 클 경우 지금 해당 자리에 있는 상어를 죽이고 현재 상어를 넣는다.
                if (nowSharkSize > lastSharkSize){
                    sharks[map[ny][nx]].isDie = true;
                    map[ny][nx] = shark.idx;
                }
                // 반대의 경우 현재 상어를 죽인다.
                else{
                    shark.isDie = true;
                }
            }
            // 상어가 존재하지 않는다면,
            else{
                map[ny][nx] = shark.idx;
            }
        }
    }
}

class Shark{
    int idx;
    int y;
    int x;
    int speed;
    int direction;
    int size;
    boolean isDie;

    public Shark(int idx, int y, int x, int speed, int direction, int size) {
        this.idx = idx;
        this.y = y;
        this.x = x;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
        isDie = false;
    }
}
