package bj2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static final int INF = 16000001; // 최댓값
    static int bitmaskSize;
    static int[][] graph;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //모든 도시에 방문했을 경우의 비트마스크 크기
        bitmaskSize = (1 << N);
        //dp 배열 [방문한 도시를 표시한 비트마스크][도시 번호]
        dp = new int[bitmaskSize][N + 1];

        // 아직 방문하지 않은 곳은 -1로 처리
        for (int i = 0; i < bitmaskSize; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(1, 1));

    }

    static int dfs(int visit, int now){
        // 모든 도시를 순회하고 원래 도시로 돌아왔을 경우
        if (visit == bitmaskSize){
            //모든 도시를 순회하고 다시 1번 도시로 돌아오는 길이 없을 경우 INF 반환 있을 경우 해당 길에 대한 가중치를 반환
            return (graph[now][1] == 0)? INF : graph[now][1];
        }
        // 이미 방문한 위치일 경우 해당 값을 반환
        if (dp[visit][now] != -1){
            return dp[visit][now];
        }
        // 일단 방문했으면 해당 값을 INF로 변경 -> 어처피 최솟값으로 변경할 예정
        dp[visit][now] = INF;

        for (int i = 1; i < N + 1; i++){
            // 다음에 갈 도시에 대한 비트마스킹
            int next = 1 << i;
            // 현 도시에서 다음 도시로 갈 수 있는 경로가 존재하고, 아직 해당 도시로 가지 않았을 경우
            // (비트 마스킹으로 & 연산을 했을 때 해당 도시를 간적이 있다면 0보다 클 것임)
            if(graph[now][i] != 0 && (visit & next) == 0){
                dp[visit][now] = Math.min(dp[visit][now], dfs(i, (visit|next)) + graph[now][i]);
            }
        }
        return dp[visit][now];

    }
}
