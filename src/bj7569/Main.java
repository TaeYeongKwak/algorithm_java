package bj7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M , H;
    static int[][][] map;
    static ArrayList<int[]> tomato;
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, -1, 0, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][M + 1][N + 1];
        tomato = new ArrayList<>();

        for (int h = 1; h < H + 1; h++){
            for (int m = 1; m < M + 1; m++){
                st = new StringTokenizer(br.readLine());
                for (int n = 1; n < N + 1; n++){
                    map[h][m][n] = Integer.parseInt(st.nextToken());
                    if (map[h][m][n] == 1){
                        tomato.add(new int[]{h, m, n});
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int[] t : tomato){
            queue.offer(t);
        }
        tomato.clear();
        int day = 0;
        while(!queue.isEmpty()){
            int[] t = queue.poll();

            for (int i = 0; i < 6; i++){
                int nh = t[0] + dh[i];
                int ny = t[1] + dy[i];
                int nx = t[2] + dx[i];
                if (0 < nh && nh <= H && 0 < ny && ny <= M && 0 < nx && nx <= N){
                    if (map[nh][ny][nx] == 0){
                        map[nh][ny][nx] = 1;
                        tomato.add(new int[]{nh, ny, nx});
                    }
                }
            }

            if (queue.isEmpty()){
                for (int[] to : tomato){
                    queue.offer(to);
                }
                tomato.clear();
                day++;
            }
        }

        System.out.println((check())? day - 1 : "-1");
    }

    static boolean check(){
        for (int h = 1; h < H + 1; h++){
            for (int m = 1; m < M + 1; m++){
                for (int n = 1; n < N + 1; n++){
                    if (map[h][m][n] == 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
