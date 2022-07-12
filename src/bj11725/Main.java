package bj11725;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int[] parents;
    static List<Integer>[] node;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        node = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) node[i] = new ArrayList<>();
        parents = new int[n + 1];
        visited = new boolean[n + 1];

        for(int i = 0; i < n - 1; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            node[x].add(y);
            node[y].add(x);
        }

        dfs(1);

        for(int i = 2; i < n + 1; i++)
            System.out.println(parents[i]);
    }

    public static void dfs(int i){
        visited[i] = true;

        for(int j : node[i]){
            if(!visited[j]){
                parents[j] = i;
                dfs(j);
            }
        }
    }
}
