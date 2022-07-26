package bj1854;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static ArrayList<int[]>[] graph;
    static PriorityQueue<Integer>[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new PriorityQueue[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
            dp[i] = new PriorityQueue<>(Comparator.reverseOrder());
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        heap.offer(new int[]{1, 0});
        dp[1].offer(0);

        while(!heap.isEmpty()){
            int[] now = heap.poll();
            int nowCity = now[0];
            int weight = now[1];

            for (int[] next : graph[nowCity]){
                if(dp[next[0]].size() < K ){
                    heap.offer(new int[]{next[0], weight + next[1]});
                    dp[next[0]].offer(weight + next[1]);
                }else if(dp[next[0]].peek() > weight + next[1]){
                    heap.offer(new int[]{next[0], weight + next[1]});
                    dp[next[0]].poll();
                    dp[next[0]].offer(weight + next[1]);
                }
            }
        }

        for (int i = 1; i < N + 1; i++){
            if (dp[i].size() == K){
                bw.write(dp[i].peek() + "\n");
            }else{
                bw.write(-1 + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
