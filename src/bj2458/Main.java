package bj2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] inCnt;
    static int[] outCnt;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
        }

        inCnt = new int[N + 1];
        outCnt = new int[N + 1];

        for (int i = 1; i < N + 1; i++){
            visited = new boolean[N + 1];
            outCnt[i] = dfs(i) - 1;
        }

        int result = 0;
        for (int i = 1; i < N + 1; i++){
            if ((inCnt[i] + outCnt[i]) == N - 1){
                result++;
            }
        }

        System.out.println(result);
    }

    static int dfs(int now){
        int outCnt = 0;
        visited[now] = true;
        for (int next : graph[now]){
            if (!visited[next]){
                inCnt[next]++;
                outCnt += dfs(next);
            }
        }
        return outCnt + 1;
    }
}
