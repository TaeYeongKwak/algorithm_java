package bj12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n, k;
    static int[][] products;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        k = Integer.parseInt(nk[1]);

        products = new int[n + 1][2];
        for (int i = 1; i < n + 1; i++){
            String[] pLine = br.readLine().split(" ");
            products[i][0] = Integer.parseInt(pLine[0]);
            products[i][1] = Integer.parseInt(pLine[1]);
        }

        dp = new int[k + 1];

        for (int i = 1; i < n + 1; i++){
            for(int j = k; j >= products[i][0]; j--){
                dp[j] = Math.max(dp[j], dp[j - products[i][0]] + products[i][1]);
            }
        }

        System.out.println(dp[k]);
    }

}
