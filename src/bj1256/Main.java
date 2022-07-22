package bj1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 파스칼 삼각형 만들기
        int[][] dp = new int[N + M + 1][N + M + 1];
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int n = 2; n < N + M + 1; n++){
            for (int k = 0; k <= n; k++){
                // 0 또는 n과 같을때
                if (k == 0 || k == n){
                    dp[n][k] = 1;
                }
                // 나머지
                else{
                    int value = dp[n-1][k-1] + dp[n-1][k];
                    // K 값과 같은 경우
                    if (value >= K) {
                        value = K;
                    }
                    dp[n][k] = value;
                }
            }
        }

        // dp[N + M][M] 와 K값을 비교 후 작거나 같으면 dp[N + M][M], 크면 dp[N + M][M - 1]로 간 후 반복
        StringBuilder sb = new StringBuilder();
        int n = N + M;
        int k = M;
        int x = 0;
        int aCnt = 0;
        while (n > 1){
            n = n - 1;
            if (x + dp[n][k] >= K){
                sb.append("a");
                aCnt++;
            }else{
                x += dp[n][k];
                k--;
                sb.append("z");
            }
            if (k == -1){
                System.out.println(-1);
                System.exit(0);
            }
        }

        sb.append((aCnt == N)? "z" : "a");

        System.out.println(sb.toString());

    }
}
