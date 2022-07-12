package bj10844;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] dp = new long[101][10];

        for (int i = 1; i < 10; i++){
            dp[1][i] = 1;
        }

        for(int i = 2; i < 101; i++){

            for (int j = 0; j < 10; j++){
                if (j == 0){
                    dp[i][0] = dp[i-1][1];
                }else if (j == 9){
                    dp[i][9] = dp[i-1][8] % 1000000000;
                }else{
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
                }
            }
        }

        long cnt = 0;
        for (int i = 0; i < 10; i++){
            cnt = (cnt + dp[n][i]) % 1000000000;
        }

        System.out.println(cnt);
    }
}
