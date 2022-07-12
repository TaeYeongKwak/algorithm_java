package sds_2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n;
    static int m;
    static int[][] cost;
    static int[][] point;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] answer = new int[t];
        for (int i = 0; i < t; i++){
            String[] nmrk = br.readLine().split(" ");
            n = Integer.parseInt(nmrk[0]);
            m = Integer.parseInt(nmrk[1]);
            int r = Integer.parseInt(nmrk[2]);
            int k = Integer.parseInt(nmrk[3]);

            map = new char[n+1][m+1];
            visited = new boolean[n+1][m+1];

            cost = new int[5][3];
            point = new int[5][1];

            for(int j = 0; j < 5; j++){
                for(int z = 0; z < 3; z++){
                    cost[j][z] = 0;
                }
            }

            for(int y = 1; y < n+1; y++){
                char[] mapLine = br.readLine().toCharArray();
                for (int x = 1; x < m+1; x++){
                    map[y][x] = mapLine[x-1];
                    if(map[y][x] == 'A') {
                        point[1] = new int[]{x, y};
                    }
                    else if(map[y][x] == 'B') {
                        point[2] = new int[]{x, y};
                    }
                    else if(map[y][x] == 'C') {
                        point[3] = new int[]{x, y};
                    }
                    else if(map[y][x] == 'S') {
                        point[4] = new int[]{x, y};
                    }
                }
            }

            answer[i] = createExcalibur(r, k);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < t; i++){
            bw.write("#" + (i+1) + " " + answer[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int createExcalibur(int r, int k){
        int distance = 0;
        point[0] = new int[]{k, r};

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 3; j++){
                if(i < 4){
                    if((i-1) != j){
                        cost[i][j] = shortDistance(point[i], point[j+1]);
                        visited = new boolean[n+1][m+1];
                    }else{
                        cost[i][j] = 10001;
                    }
                }else{
                    cost[i][j] = sPointDistance(point[j+1]);
                    visited = new boolean[n+1][m+1];
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(int z = 0; z < 3; z++) {
                    if (i != j && j != z && z != i)
                        result.add(cost[0][i] + cost[i+1][j] + cost[j+1][z] + cost[4][z]);
                }
            }
        }


        int temp = 10001;
        for(int re : result){
            if(temp > re){
                temp = re;
            }
        }
        distance = temp;
        return distance;
    }

    static int shortDistance(int[] start, int[] end) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{start[0], start[1], 0});
        visited[start[1]][start[0]] = true;
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = temp[0] + dx[i];
                int ny = temp[1] + dy[i];
                if (nx > 0 && nx <= m && ny > 0 && ny <= n) {
                    if (map[ny][nx] != 'X' && !visited[ny][nx]) {
                        queue.offer(new int[]{nx, ny, temp[2] + 1});
                        visited[ny][nx] = true;
                        if (nx == end[0] && ny == end[1]){
                            return temp[2] + 1;
                        }
                    }
                }
            }
        }
        return 0;
    }

    static int sPointDistance(int[] start){
        return Math.abs(start[0] - point[4][0]) + Math.abs(start[1] - point[4][1]);
    }
}