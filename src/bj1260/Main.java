package bj1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmv = br.readLine().split(" ");
        int n = Integer.parseInt(nmv[0]);
        int m = Integer.parseInt(nmv[1]);
        int v = Integer.parseInt(nmv[2]);

        int[][] node = new int[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];

        for(int i = 0; i < m; i++){
            String[] xy = br.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            node[x][y] = 1;
            node[y][x] = 1;
        }

        dfs(v, visited, node);
        System.out.println();
        visited = new boolean[n + 1];
        bfs(v, visited, node);

    }

    public static void dfs(int i, boolean[] visited, int[][] node){
        visited[i] = true;
        System.out.print(i + " ");
        for(int j = 1; j < visited.length; j++){
            if(node[i][j] == 1 && !visited[j])
                dfs(j, visited, node);
        }
    }

    public static void bfs(int i, boolean[] visited, int[][] node){
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(i);
        visited[i] = true;
        System.out.print(i + " ");
        while(!q.isEmpty()){
            int temp = q.poll();

            for(int j = 1; j < visited.length; j++){
                if(node[temp][j] == 1 && !visited[j]) {
                    q.offer(j);
                    visited[j] = true;
                    System.out.print(j + " ");
                }
            }
        }
    }
}
