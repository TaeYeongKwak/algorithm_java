package bj7579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] appMemory;
    static int[] inactiveM;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 현재 올라가있는 앱의 갯수
        N = Integer.parseInt(st.nextToken());
        // 다음에 올릴 앱이 차지하는 메모리 수
        M = Integer.parseInt(st.nextToken());

        // 현재 올라가있는 앱이 차지하는 메모리 용량 배열
        appMemory = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++){
            appMemory[i] = Integer.parseInt(st.nextToken());
        }

        // 현재 올라가있는 앱을 비활성화 할 경우 발생하는 비용 배열
        int costSum = 1;
        inactiveM = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++){
            inactiveM[i] = Integer.parseInt(st.nextToken());
            costSum += inactiveM[i];
        }
        //dp 전처리 dp[비용 합]
        dp = new int[N + 1][costSum];
        //
        for (int i = 1; i < N + 1; i++){
            for (int j = costSum - 1; j >= inactiveM[i] ; j--){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - inactiveM[i]] + appMemory[i]);
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        int result = costSum;
        for (int i = 1; i < N + 1; i++){
            for (int j = 0; j < costSum; j++) {
                if (dp[i][j] >= M){
                    result = j;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
