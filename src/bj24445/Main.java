package bj24445;

import java.io.*;
import java.util.*;

public class Main {

    static int k = 1;
    static int[] answer;

    public static void bfs(List<Integer>[] node, boolean[] visited, int v){
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(v);
        visited[v] = true;
        answer[v] = k++;
        while(!q.isEmpty()){
            int temp = q.poll();

            for(int n : node[temp]){
                if(!visited[n]) {
                    q.offer(n);
                    visited[n] = true;
                    answer[n] = k++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmr = br.readLine().split(" ");

        int n = Integer.parseInt(nmr[0]);
        int m = Integer.parseInt(nmr[1]);
        int r = Integer.parseInt(nmr[2]);

        ArrayList<Integer>[] node = new ArrayList[n + 1];
        for(int i = 1; i < n + 1; i++) node[i] = new ArrayList<Integer>();
        boolean[] visited = new boolean[n + 1];
        answer = new int[n + 1];

        for(int i = 0; i < m; i++){
            String[] xy = br.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);

            node[x].add(y);
            node[y].add(x);
        }
        for(int i = 1; i < n + 1; i++) Collections.sort(node[i], Collections.reverseOrder());

        bfs(node, visited, r);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 1; i < n + 1; i++)
            bw.write(answer[i] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
