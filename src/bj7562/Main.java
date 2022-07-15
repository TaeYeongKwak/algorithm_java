package bj7562;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int l;
    static int[] start;
    static int[] end;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[] answer = new int[t];
        for (int i = 0; i < t; i++){
            l = Integer.parseInt(br.readLine());
            start = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            end = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            visited = new boolean[l][l];
            answer[i] = bfs();
        }

        for (int i = 0; i < t; i++){
            bw.write(answer[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
    static int[] dy = {2, -2, 1, -1, 2, -2, 1, -1};
    static int bfs(){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});
        visited[start[1]][start[0]] = true;

        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            if (temp[0] == end[0] && temp[1] == end[1]){
                return temp[2];
            }

            for (int i = 0; i < 8; i++){
                int nx = temp[0] + dx[i];
                int ny = temp[1] + dy[i];

                if (0 <= nx && nx < l && 0 <= ny && ny < l){
                    if (!visited[ny][nx]){
                        queue.offer(new int[]{nx, ny, temp[2] + 1});
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        return 0;
    }
}
