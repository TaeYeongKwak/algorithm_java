package bj20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][][] lazy;
    static Queue<FireBall> fireBalls;
    static Queue<FireBall> movedFire;
    static int[][][] directionCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        lazy = new int[N + 1][N + 1][3];
        directionCheck = new int[N + 1][N + 1][2];
        fireBalls = new LinkedList<>();
        movedFire = new LinkedList<>();

        for (int i = 1; i < M + 1; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireBalls.offer(new FireBall(c, r, m, s, d));
        }

        for (int i = 0; i < K; i++){
            command();
        }

        System.out.println(getMassSum());
    }

    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int[] odd = {1, 3, 5, 7};
    static int[] even = {0, 2, 4, 6};

    public static void command(){
        //  1. 이동
        while(!fireBalls.isEmpty()){
            FireBall fire = fireBalls.poll();

            int nx = fire.x + (dx[fire.direction] * (fire.speed % N));
            int ny = fire.y + (dy[fire.direction] * (fire.speed % N));

            // 범위 안에 없을 경우
            if (0 >= nx){
                nx = N - Math.abs(nx);
            }
            else if (nx > N){
                nx = nx % N;
            }

            if (0 >= ny){
                ny = N - Math.abs(ny);
            }
            else if (ny > N){
                ny = ny % N;
            }

            fire.x = nx;
            fire.y = ny;

            // 0 : 질량의 합, 1 : 속력의 합, 2 : 해당 위치에 있는 파이어볼의 개수
            lazy[ny][nx][0] += fire.mass;
            lazy[ny][nx][1] += fire.speed;
            lazy[ny][nx][2] += 1;

            if (fire.direction % 2 == 1){
                directionCheck[ny][nx][0] += 1;
            }
            else if (fire.direction % 2 == 0){
                directionCheck[ny][nx][1] += 1;
            }

            movedFire.offer(fire);
        }

        // 2. 이동이 끝난 후
        while(!movedFire.isEmpty()){
            FireBall fire = movedFire.poll();
            int x = fire.x;
            int y = fire.y;
            
            // 같은 위치에 파이어볼이 2개 이상 있을 경우
            if (lazy[y][x][2] > 1){
                int m = lazy[y][x][0] / 5;
                int s = lazy[y][x][1] / lazy[y][x][2];
                int[] d = (directionCheck[y][x][0] == 0 || directionCheck[y][x][1] == 0)? even : odd;

                if (m > 0){
                    for (int i = 0; i < 4; i++){
                        fireBalls.offer(new FireBall(x, y, m, s, d[i]));
                    }
                }
            }
            else if (lazy[y][x][2] == 1){
                fireBalls.offer(fire);
            }

            directionCheck[y][x][0] = directionCheck[y][x][1] = 0;
            lazy[y][x][0] = lazy[y][x][1] = lazy[y][x][2] = 0;
        }
    }

    static int getMassSum(){
        int sum = 0;
        while(!fireBalls.isEmpty()) {
            FireBall fire = fireBalls.poll();
            sum += fire.mass;
        }
        return sum;
    }

}

class FireBall{
    int x, y;
    int mass;
    int direction;
    int speed;

    public FireBall(int x, int y, int mass, int speed, int direction) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.direction = direction;
        this.speed = speed;
    }
}
