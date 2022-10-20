package bj17182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] map;
    static int result;
    static int maxBitMask;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 워샬로 다른 행성으로 우회해서 갈 경우의 시간을 확인
        for (int k = 0; k < N; k++){
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        result = Integer.MAX_VALUE;
        maxBitMask = (1 << N) - 1;
        solution(K, 1 << K, 0);
        System.out.println(result);
    }

    static void solution(int i, int bitmask, int time){
        // 모든 행성을 다 돌았는지 확인
        if (bitmask == maxBitMask) {
            // 다 돌았을 경우 모두 돌았을 경우의 시간을 확인
            result = Math.min(time, result);
            return;
        }
        // 다음에 갈 행성 확인
        for (int j = 0; j < N; j++){
            // 가지 않은 행성 확인
            int bit = 1 << j;
            if ((bitmask & bit) == 0){
                solution(j, bitmask | bit, time + map[i][j]);
            }
        }
    }
}
