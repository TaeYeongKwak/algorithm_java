package bj24479;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int[] answer;
    public static int k = 1;

    public static void dfs(List<Integer>[] node, boolean[] visited, int v){
        visited[v] = true;
        answer[v] = k++;
        for(int j: node[v]){
            if (!visited[j]){
                dfs(node, visited, j);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] nmr = bf.readLine().split(" ");
        int n = Integer.parseInt(nmr[0]);
        int m = Integer.parseInt(nmr[1]);
        int r = Integer.parseInt(nmr[2]);

        ArrayList<Integer>[] node = new ArrayList[n + 1];
        for(int i = 1; i < n + 1; i++) node[i] = new ArrayList<Integer>();

        boolean[] visited = new boolean[n + 1];
        answer = new int[n + 1];

        for(int i = 0; i < m; i++){
            String[] xy = bf.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);

            node[x].add(y);
            node[y].add(x);
        }

        for(int i = 1; i < n + 1; i++) Collections.sort(node[i]);

        dfs(node, visited, r);
        for(int i = 1; i < n + 1; i++) System.out.println(answer[i]);
    }
}
