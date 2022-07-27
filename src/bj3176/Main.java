package bj3176;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][][] dp;
    static ArrayList<int[]>[] graph;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        
        //그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new int[]{B, C});
            graph[B].add(new int[]{A, C});
        }

        init(1);

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[] result = solution(D, E);
            bw.write(result[0] + " " + result[1] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static void init(int start){
        dp = new int[18][N + 1][3];
        depth = new int[N + 1];

        for (int i = 0; i < 18; i++){
            for (int j = 1; j < N + 1; j++){
                dp[i][j][1] = 1000001;
                dp[i][j][2] = 0;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        while (!queue.isEmpty()){
            int now = queue.poll();

            for (int[] next : graph[now]){
                if (!visited[next[0]]){
                    visited[next[0]] = true;
                    dp[0][next[0]][0] = now;
                    dp[0][next[0]][1] = next[1];
                    dp[0][next[0]][2] = next[1];
                    depth[next[0]] = depth[now] + 1;
                    queue.offer(next[0]);
                }
            }
        }

        for (int i = 1; i < 18; i++){
            for (int j = 1; j < N + 1; j++){
                dp[i][j][0] = dp[i - 1][dp[i - 1][j][0]][0];
                dp[i][j][1] = Math.min(dp[i - 1][j][1], dp[i - 1][dp[i - 1][j][0]][1]);
                dp[i][j][2] = Math.max(dp[i - 1][j][2], dp[i - 1][dp[i - 1][j][0]][2]);
            }
        }
    }

    static int[] solution(int D, int E){
        int max = 0;
        int min = 1000001;
        if (depth[D] > depth[E]){
            int temp = E;
            E = D;
            D = temp;
        }

        int gap = depth[E] - depth[D];
        for (int i = 0; i < 18; i++){
            if ((gap & (1 << i)) > 0){
                min = Math.min(dp[i][E][1], min);
                max = Math.max(dp[i][E][2], max);
                E = dp[i][E][0];
            }
        }

        if (D == E){
            return new int[]{min, max};
        }

        for (int i = 17; i >= 0; i--){
            if (dp[i][D][0] != dp[i][E][0]){
                min = Math.min(min, Math.min(dp[i][D][1], dp[i][E][1]));
                max = Math.max(max, Math.max(dp[i][D][2], dp[i][E][2]));
                D = dp[i][D][0];
                E = dp[i][E][0];
            }
        }

        min = Math.min(min, Math.min(dp[0][D][1], dp[0][E][1]));
        max = Math.max(max, Math.max(dp[0][D][2], dp[0][E][2]));

        return new int[]{min, max};
    }

}
