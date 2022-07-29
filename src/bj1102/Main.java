package bj1102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 987654321;
    static int N, P, allVisit, originStatusMask;
    static int[][] graph;
    static int[] dp;
    static char[] status;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        graph = new int[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        status = br.readLine().toCharArray();

        P = Integer.parseInt(br.readLine());
        originStatusMask = 0;
        for (int i = 0; i < N; i++){
            if (status[i] == 'Y'){
                originStatusMask |= (1 << i);
            }
        }

        allVisit = (1 << N) - 1;
        //
        dp = new int[allVisit + 1];
        Arrays.fill(dp, -1);

        int result = dfs(originStatusMask);
        if (result == -1){
            System.out.println(0);
        }else if (result == INF){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }


    }

    static int dfs(int visit){

        if (dp[visit] != -1){
            return dp[visit];
        }

        // 현재 고장나지 않은 발전소가 p개 일 경우
        if ((check(visit) >= P)){
            return 0;
        }

        dp[visit] = INF;

        for (int i = 0; i < N; i++){
            int next = 1 << i;
            // 고장난 발전소 찾기
            if ((visit & next) == 0){
                // 고장나지 않은 발전소 찾기
                int minWeight = INF;
                for (int j = 0; j < N; j++){
                    int k = 1 << j;
                    if (i != j && (visit & k) > 0){
                        minWeight = Math.min(graph[j][i],  minWeight);
                    }
                }
                dp[visit] = Math.min(dp[visit], dfs(visit | next) + minWeight);
            }

        }

        return dp[visit];
    }
    
    //발전소가 p개 켜져있는지 확인
    static int check(int visit){
        int cnt;
        for (cnt = 0; visit != 0; cnt++){
            visit &= visit - 1;
        }
        return cnt;
    }
}
