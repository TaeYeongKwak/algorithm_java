package bj12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[200001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        heap.offer(new int[]{N, 0});
        dp[N] = 0;

        int result = 0;
        while(!heap.isEmpty()){
            int[] now = heap.poll();

            if (dp[now[0]] < now[1]) continue;

            int[] next = {now[0] + 1, now[0] - 1, 2 * now[0]};
            for (int i = 0; i < 3; i++){
                if (0 <= next[i] && next[i] <= 200000){
                    if (dp[next[i]] >= now[1] + 1){
                        heap.offer(new int[]{next[i], dp[now[0]] + 1});
                        dp[next[i]] = now[1] + 1;

                        if (next[i] == K){
                            result++;
                        }
                    }
                }
            }
        }

        if (N == K) result = 1;

        System.out.print(dp[K] + "\n" + result);

    }
}
