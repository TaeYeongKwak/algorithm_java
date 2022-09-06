package bj2629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[] dp = new boolean[40001];
        List<Integer> possible = new ArrayList<>();
        possible.add(0);
        dp[0] = true;


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++){
            int w = Integer.parseInt(st.nextToken());

            int size = possible.size();
            for (int i = 0; i < size; i++){
                int x = possible.get(i);

                int plusValue = w + x;
                int minusValue = Math.abs(w - x);
                if (!dp[plusValue] && plusValue <= 40000){
                    possible.add(plusValue);
                    dp[w + x] = true;
                }
                if (!dp[minusValue]){
                    possible.add(minusValue);
                    dp[minusValue] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++){
            int bead = Integer.parseInt(st.nextToken());
            sb.append((dp[bead])? "Y " : "N ");
        }

        System.out.println(sb);
    }
}
