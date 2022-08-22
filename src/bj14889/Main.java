package bj14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] S;
    static boolean[] selected;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N + 1][N + 1];
        selected = new boolean[N + 1];
        StringTokenizer st;
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++){
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MAX_VALUE;
        dfs(0, 0);
        System.out.println(result);
    }

    static void dfs(int r, int x){
        if (r == N / 2){
            result = Math.min(getSum(), result);
            return;
        }

        for (int n = x + 1; n < N + 1; n++){
            if (!selected[n]){
                selected[n] = true;
                dfs(r + 1, n);
                selected[n] = false;
            }
        }
    }

    static int getSum(){
        int startSum = 0;
        int linkSum = 0;
        for (int i = 1; i < N + 1; i++){
            for (int j = 1; j < N + 1; j++){
                if (selected[i] && selected[j]){
                    startSum += S[i][j];
                }
                else if(!selected[i] && !selected[j]){
                    linkSum += S[i][j];
                }
            }
        }
        return Math.abs(startSum - linkSum);
    }
}
