package bj2011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final long MOD = 1000000L;
    static String password;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        password = br.readLine();
        int size = password.length();
        long[] dp = new long[size + 1];

        if (getValue(0, 1) > 0){
            dp[1] = 1;
        }

        if (size >= 2){
            if (getValue(1, 2) == 0){
                dp[1] = 0;
            }

            dp[2] = dp[1];
            int value = getValue(0, 2);
            if (getValue(0, 1) != 0 && (0 < value && value < 27)){
                dp[2] += 1;
            }
        }

        for (int i = 3; i < size + 1; i++){
            if (getValue(i - 1, i) > 0){
                dp[i] = dp[i - 1] % MOD;
            }

            int value2 = getValue(i - 2, i);
            if (getValue(i - 2, i - 1) != 0 && (0 < value2 && value2 < 27)){
                dp[i] += dp[i - 2] % MOD;
            }
        }

        System.out.println(dp[size] % MOD);

    }

    static int getValue(int start, int end){
        return Integer.parseInt(password.substring(start, end));
    }
}
