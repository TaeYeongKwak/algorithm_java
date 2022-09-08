package bj4485;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static PriorityQueue<int[]> heap;
    static int[][] rupee;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        heap = new PriorityQueue<>(Comparator.comparing(o -> o[2]));
        int T = 1;
        StringTokenizer st;
        while (true){
            N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            map = new int[N][N];
            rupee = new int[N][N];

            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = dijkstra();
            bw.write("Problem " + T + ": " + result + "\n");
            T++;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int dijkstra(){
        heap.clear();
        for (int i = 0; i < N; i++){
            Arrays.fill(rupee[i], 1126);
        }

        rupee[0][0] = map[0][0];
        heap.offer(new int[]{0, 0, map[0][0]});

        while(!heap.isEmpty()){
            int[] now = heap.poll();

            if (rupee[now[0]][now[1]] < now[2]) continue;

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N){
                    if (rupee[ny][nx] <= now[2] + map[ny][nx]) continue;
                    rupee[ny][nx] = now[2] + map[ny][nx];
                    heap.offer(new int[]{ny, nx, rupee[ny][nx]});
                }
            }
        }

        return rupee[N - 1][N - 1];
    }
}
