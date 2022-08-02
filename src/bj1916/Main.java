package bj1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final long INF = 10000000001L;
    static int N, M;
    static ArrayList<long[]>[] graph;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int n = 1; n < N + 1; n++){
            graph[n] = new ArrayList<>();
        }

        dp = new long[N + 1];
        Arrays.fill(dp, INF);


        StringTokenizer st;
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[from].add(new long[]{to, time});
        }

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        dijkstra(A);

        System.out.println(dp[B]);

    }

    static void dijkstra(int start){
        PriorityQueue<long[]> heap = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        heap.offer(new long[]{start, 0});
        dp[start] = 0;

        while(!heap.isEmpty()){
            long[] now = heap.poll();

            if (dp[(int) now[0]] < now[1]) continue;

            for (long[] next : graph[(int)now[0]]){
                if (dp[(int)next[0]] > dp[(int)now[0]] + next[1]){
                    dp[(int)next[0]] = dp[(int)now[0]] + next[1];
                    heap.offer(new long[]{next[0], dp[(int)next[0]]});
                }
            }
        }
    }
}
