package bj7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int M;
    static int N;
    static int days;
    static int[][] field;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> q = new LinkedList<>();


    static void bfs(){
        ArrayList<int[]> tempList = new ArrayList<>();
        while(!q.isEmpty()){
            int[] temp = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = temp[0] + dx[i];
                int ny = temp[1] + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M){
                    if (field[nx][ny] == 0 && !visited[nx][ny]){
                        field[nx][ny] = 1;
                        tempList.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }

            if(q.size() == 0){
                for (int[] point: tempList) {
                    q.offer(point);
                }
                tempList.clear();
                days++;
            }
        }
    }

    static int checkTomato(){
        int checkCount = 0;
        for (int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if (field[i][j] == 0 && !visited[i][j]){
                    checkCount++;
                }
            }
        }
        return checkCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mn = br.readLine().split(" ");
        M = Integer.parseInt(mn[0]);
        N = Integer.parseInt(mn[1]);

        field = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++){
            String[] tomato = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                field[i][j] = Integer.parseInt(tomato[j]);
            }
        }

        for (int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if (field[i][j] == 1 && !visited[i][j]){
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }


        bfs();

        if(checkTomato() > 0) days = 0;

        System.out.println(days-1);
    }
}
