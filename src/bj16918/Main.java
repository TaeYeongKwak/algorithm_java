package bj16918;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, N;
    static Boom[][] map;
    static Queue<Boom> lazyBooms;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new Boom[R][C];

        for (int r = 0; r < R; r++){
            char[] mapLine = br.readLine().toCharArray();
            for (int c = 0; c < C; c++){
                boolean isInstall = (mapLine[c] == '.')? false : true;
                map[r][c] = new Boom(c, r, isInstall);
            }
        }

        lazyBooms = new LinkedList<>();

        int time = 1;
        while(time < N){
            time++;
            if (time % 2 == 0){
                // 폭탄이 설치되어있지 않은 모든 칸에 폭탄을 설치
                for (int r = 0; r < R; r++){
                    for (int c = 0; c < C; c++){
                        if (!map[r][c].isInstall){
                            map[r][c].install(time);
                        }
                    }
                }
            }
            else{
                // 3초 전에 설치된 폭탄이 모두 폭발
                for (int r = 0; r < R; r++){
                    for (int c = 0; c < C; c++){
                        if (map[r][c].isBoom(time - 3)){
                            if (!map[r][c].isLazyBoom){
                                map[r][c].isLazyBoom = true;
                                lazyBooms.offer(map[r][c]);
                            }
                            for (int i = 0; i < 4; i++){
                                int nx = c + dx[i];
                                int ny = r + dy[i];

                                if (0 <= nx && nx < C && 0 <= ny && ny < R){
                                    if (!map[ny][nx].isLazyBoom){
                                        map[ny][nx].isLazyBoom = true;
                                        lazyBooms.offer(map[ny][nx]);
                                    }
                                }
                            }
                        }
                    }
                }

                while(!lazyBooms.isEmpty()){
                    lazyBooms.poll().boom();
                }
            }
        }

        for (int r = 0; r < R; r++){
            for (int c = 0; c < C; c++){
                bw.write(map[r][c].isInstall? 'O' : '.');
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}

class Boom{
    int x, y;
    int time;
    boolean isInstall;
    boolean isLazyBoom;

    public Boom(int x, int y, boolean isInstall) {
        this.x = x;
        this.y = y;
        this.isInstall = isInstall;
        this.time = (isInstall)? 0 : -100;
        isLazyBoom = false;
    }

    public void install(int time){
        this.isInstall = true;
        this.time = time;
    }

    public boolean isBoom(int time){
        return (this.isInstall && this.time == time);
    }

    public void boom(){
        isLazyBoom = false;
        isInstall = false;
        time = -100;
    }
}