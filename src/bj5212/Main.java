package bj5212;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < R; r++){
            char[] mapLine = br.readLine().toCharArray();
            for (int c = 0; c < C; c++){
                map[r][c] = mapLine[c];
                if (map[r][c] == 'X'){
                    queue.offer(new int[]{r, c});
                }
            }
        }

        int maxR = 0;
        int minR = R;
        int maxC = 0;
        int minC = C;
        boolean[][] temp = new boolean[R][C];

        while(!queue.isEmpty()){
            int[] land = queue.poll();

            int count = 0;
            for (int i = 0; i < 4; i++){
                int nx = land[1] + dx[i];
                int ny = land[0] + dy[i];

                if (0 <= nx && nx < C && 0 <= ny && ny < R){
                    if (map[ny][nx] == '.'){
                        count++;
                    }
                }
                else {
                    count++;
                }
            }

            if (count < 3){
                maxR = Math.max(maxR, land[0]);
                minR = Math.min(minR, land[0]);
                maxC = Math.max(maxC, land[1]);
                minC = Math.min(minC, land[1]);
                temp[land[0]][land[1]] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = minR; r <= maxR; r++){
            for (int c = minC; c <= maxC; c++){
                sb.append(temp[r][c]? 'X' : '.');
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
