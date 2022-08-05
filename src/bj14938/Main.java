package bj14938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, R;
    static int[] itemCnt;
    static ArrayList<int[]>[] graph;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        itemCnt = new int[N + 1];
        graph = new ArrayList[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++){
            itemCnt[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }


        for (int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, dist});
            graph[to].add(new int[]{from, dist});
        }

        int result = 0;
        for (int i = 1; i < N + 1; i++){
            result = Math.max(dijkstra(i), result);
        }

        System.out.println(result);

    }

    static int dijkstra(int start){
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        heap.offer(new int[]{start, 0});
        dp[start] = 0;

        while (!heap.isEmpty()){
            int[] now = heap.poll();

            for (int[] next : graph[now[0]]){
                if (dp[next[0]] > dp[now[0]] + next[1] && dp[now[0]] + next[1] <= M){
                    dp[next[0]] = dp[now[0]] + next[1];
                    heap.offer(new int[]{next[0], dp[next[0]]});
                }
            }
        }

        int result = 0;
        for (int i = 1; i < N + 1; i++){
            if (dp[i] <= M){
                result += itemCnt[i];
            }
        }
        return result;
    }

}
