package bj16938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L, R, X;
    static int[] tier;
    static boolean[][] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        tier = new int[N];
        int visitedSize = 1 << 16;
        visited = new boolean[N][visitedSize];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            tier[i] = Integer.parseInt(st.nextToken());
        }

        result = 0;
        dfs(0, 0, 0);
        System.out.println(result);
    }

    static void dfs(int r, int selectCnt, int bitMask){
        if (r >= N) return;

        // 현재 문제를 풀지 않았을 경우
        dfs(r + 1, selectCnt, bitMask);

        // 현재 문제를 풀었을 경우
        bitMask |= 1 << r;
        selectCnt += 1;
        if (selectCnt >= 2 && !visited[r][bitMask]){
            visited[r][bitMask] = true;
            int tierSum = 0;
            int max = 0;
            int min = 1000001;
            for (int i = 0; i < N; i++){
                int x = 1 << i;
                if ((bitMask & x) > 0){
                    tierSum += tier[i];
                    max = Math.max(max, tier[i]);
                    min = Math.min(min, tier[i]);
                }
            }

            if ((L <= tierSum && tierSum <= R) && (max - min >= X)){
                result++;
            }
        }

        dfs(r + 1, selectCnt, bitMask);

    }
}
