package bj14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<int[]> virus;
    static Queue<int[]> queue;
    static int[][] map;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        virus = new ArrayList<>();
        map = new int[N + 1][M + 1];
        queue = new LinkedList<>();
        for (int n = 1; n < N + 1; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m < M + 1; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] == 2){
                    virus.add(new int[]{n, m});
                }
            }
        }

        result = 0;
        for (int n = 1; n < N + 1; n++){
            for (int m = 1; m < M + 1; m++){
                if (map[n][m] == 0){
                    map[n][m] = 1;
                    dfs(1, m, n);
                    map[n][m] = 0;
                }
            }
        }

        System.out.println(result);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void dfs(int r, int x, int y){
        if (r == 3){
            result = Math.max(result, getSafeZone(copyMap(map)));
            return;
        }

        for (int n = y; n < N + 1; n++){
            for (int m = 1; m < M + 1; m++){
                if (map[n][m] == 0){
                    map[n][m] = 1;
                    dfs(r + 1, m, n);
                    map[n][m] = 0;
                }
            }
        }
    }

    static int getSafeZone(int[][] map){
        queue.clear();
        for (int[] v : virus){
            queue.offer(v);
        }

        while(!queue.isEmpty()){
            int[] v = queue.poll();

            for (int i = 0; i < 4; i++){
                int nx = v[1] + dx[i];
                int ny = v[0] + dy[i];

                if (0 < nx && nx <= M && 0 < ny && ny <= N){
                    if (map[ny][nx] == 0){
                        queue.offer(new int[]{ny, nx});
                        map[ny][nx] = 2;
                    }
                }
            }
        }

        int cnt = 0;
        for (int n = 1; n < N + 1; n++){
            for (int m = 1; m < M + 1; m++){
                if (map[n][m] == 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static int[][] copyMap(int[][] map){
        int[][] copyMap = new int[N + 1][M + 1];
        for (int n = 1; n < N + 1; n++){
            for (int m = 1; m < M + 1; m++){
                copyMap[n][m] = map[n][m];
            }
        }
        return copyMap;
    }
}
