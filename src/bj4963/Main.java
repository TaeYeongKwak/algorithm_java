package bj4963;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0){
                break;
            }

            int[][] map = new int[h][w];
            for(int i = 0; i < h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(solution(w, h, map) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {0, 1, -1, 1, -1, 0, 1, -1};

    static int solution(int w, int h, int[][] map){
        int result = 0;

        boolean[][] visited = new boolean[h][w];
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                if (visited[i][j] || map[i][j] == 0) continue;
                visited[i][j] = true;
                queue.offer(new int[]{i, j});
                while(!queue.isEmpty()){
                    int[] now = queue.poll();

                    for(int k = 0; k < 8; k++){
                        int nx = now[1] + dx[k];
                        int ny = now[0] + dy[k];
                        if (0 <= nx && nx < w && 0 <= ny && ny < h){
                            if (map[ny][nx] == 1 && !visited[ny][nx]){
                                queue.offer(new int[]{ny, nx});
                                visited[ny][nx] = true;
                            }
                        }
                    }
                }

                result++;
            }
        }

        return result;
    }

}
