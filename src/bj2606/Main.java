package bj2606;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer>[] node;
    static boolean[] visited;
    static int answer = 0;

    public static void bfs(int v){
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(v);
        visited[v] = true;

        while(!q.isEmpty()){
            int temp = q.poll();
            for(int n : node[temp]){
                if(!visited[n]){
                    q.offer(n);
                    visited[n] = true;
                    answer++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        node = new ArrayList[n + 1];
        for(int i = 1; i < n + 1; i++) node[i] = new ArrayList<Integer>();

        visited = new boolean[n + 1];
        for(int j = 0; j < m; j++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            node[x].add(y);
            node[y].add(x);
        }

        bfs(1);
        System.out.println(answer);
    }
}
