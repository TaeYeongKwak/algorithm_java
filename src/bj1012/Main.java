package bj1012;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> cabbages, bfsQ;
    static int worms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        cabbages = new LinkedList<>();
        bfsQ = new LinkedList<>();
        StringTokenizer st;
        for (int t = 1; t < T + 1; t++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[N][M];
            cabbages.clear();

            for (int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                cabbages.offer(new int[]{y, x});
                map[y][x] = 1;
            }

            worms = 0;
            while(!cabbages.isEmpty()){
                int[] cabbage = cabbages.poll();
                if (!visited[cabbage[0]][cabbage[1]]){
                    bfs(cabbage);
                }
            }

            bw.write(worms + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void bfs(int[] start){
        bfsQ.clear();
        visited[start[0]][start[1]] = true;
        bfsQ.offer(start);

        while(!bfsQ.isEmpty()){
            int[] now = bfsQ.poll();

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];

                if (0 <= nx && nx < M && 0 <= ny && ny < N){
                    if (visited[ny][nx] || map[ny][nx] == 0) continue;
                    visited[ny][nx] = true;
                    bfsQ.offer(new int[]{ny, nx});
                }
            }
        }

        worms++;
    }
}
