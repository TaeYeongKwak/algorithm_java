package bj2668;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] num;
    static boolean[] visited;
    static int start;
    static PriorityQueue<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        num = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        result = new PriorityQueue<>();

        for (int i = 1; i < N + 1; i++){
            visited[i] = true;
            start = i;
            dfs(i);
            visited[i] = false;
        }



        bw.write(result.size() + "\n");
        while (!result.isEmpty()){
            bw.write(result.poll() + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int r){
        if (!visited[num[r]]){
            visited[num[r]] = true;
            dfs(num[r]);
            visited[num[r]] = false;
        }

        if (num[r] == start){
            result.offer(start);
        }
    }
}
