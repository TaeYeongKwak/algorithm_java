package bj2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] iceHeight;
    static int[][] lazyMelt;
    static int iceCount;
    static Queue<int[]> ice, melt;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        iceHeight = new int[N + 1][M + 1];
        lazyMelt = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        ice = new LinkedList<>();
        melt = new LinkedList<>();

        for (int n = 1; n < N + 1; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m < M + 1; m++){
                iceHeight[n][m] = Integer.parseInt(st.nextToken());
                if (iceHeight[n][m] > 0){
                    ice.offer(new int[]{n, m});
                }
            }
        }

        System.out.println(meltIce());
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int meltIce(){
        int year = 0;
        while (!ice.isEmpty()){
            int[] icePoint = ice.poll();

            for (int i = 0; i < 4; i++){
                int nx = icePoint[1] + dx[i];
                int ny = icePoint[0] + dy[i];

                if (0 < nx && nx < M + 1 && 0 < ny && ny < N + 1 && iceHeight[ny][nx] == 0){
                    lazyMelt[icePoint[0]][icePoint[1]] += 1;
                }
            }
            melt.offer(icePoint);

            if (ice.isEmpty()){
                visited = new boolean[N + 1][M + 1];
                iceCount = 0;
                dfs(icePoint);
                if (iceCount < melt.size()){
                    return year;
                }

                while(!melt.isEmpty()){
                    int[] meltPoint = melt.poll();
                    if (iceHeight[meltPoint[0]][meltPoint[1]] > lazyMelt[meltPoint[0]][meltPoint[1]]){
                        ice.offer(meltPoint);
                        iceHeight[meltPoint[0]][meltPoint[1]] -= lazyMelt[meltPoint[0]][meltPoint[1]];
                    }
                    else{
                        iceHeight[meltPoint[0]][meltPoint[1]] = 0;
                    }
                    lazyMelt[meltPoint[0]][meltPoint[1]] = 0;
                }
                year++;
            }
        }

        return 0;
    }

    static void dfs(int[] icePoint){
        if (visited[icePoint[0]][icePoint[1]]){
            return;
        }

        visited[icePoint[0]][icePoint[1]] = true;
        iceCount++;

        for (int i = 0; i < 4; i++){
            int nx = icePoint[1] + dx[i];
            int ny = icePoint[0] + dy[i];

            if (0 < nx && nx < M + 1 && 0 < ny && ny < N + 1 && iceHeight[ny][nx] != 0){
                dfs(new int[]{ny, nx});
            }
        }
    }
}
