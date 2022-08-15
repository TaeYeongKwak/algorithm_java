package bj14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Work[] works = new Work[N + 1];
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            works[i] = new Work(i, i + time - 1, cost);
        }

        int[] dp = new int[N + 1];
        if (works[1].end <= 1){
            dp[1] = works[1].cost;
        }

        for (int i = 2; i < N + 1; i++){
            for (int j = 1; j <= i; j++){
                if(works[j].end <= i){
                    dp[i] = Math.max(dp[i], dp[works[j].start - 1] + works[j].cost);
                }
            }
        }

        System.out.println(dp[N]);
    }
}

class Work{
    int start;
    int end;
    int cost;

    public Work(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}
