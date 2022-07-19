package bj16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        ArrayList<int[]>[] map = new ArrayList[101];
        int[] visited = new int[101];
        Arrays.fill(visited, Integer.MAX_VALUE);

        for (int i = 1; i < 101; i++){
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < n + m; i++){
            String[] numStr = br.readLine().split(" ");
            int from = Integer.parseInt(numStr[0]);
            int to = Integer.parseInt(numStr[1]);
            map[from].add(new int[]{to, 0});
        }

        for (int i = 1; i < 101; i++){
            if (map[i].size() == 0){
                for (int j = 1; j < 7; j++){
                    if (i + j < 101)
                        map[i].add(new int[]{i + j, 1});
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = 0;

        while (!queue.isEmpty()){
            int temp = queue.poll();

            if (temp == 100)
                break;

            for (int[] x : map[temp]){
                if (visited[x[0]] > visited[temp] + x[1]){
                    queue.offer(x[0]);
                    visited[x[0]] = visited[temp] + x[1];
                }
            }
        }

        System.out.println(visited[100]);
    }
}
