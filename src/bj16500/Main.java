package bj16500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int N = Integer.parseInt(br.readLine());

        Set<String> dict = new HashSet<>();
        for (int n = 0; n < N; n++){
            dict.add(br.readLine());
        }

        int[] dp = new int[S.length() + 1];

        for (int i = S.length() - 1; i >= 0; i--){
            for (int j = i + 1; j < S.length(); j++){
                if (dp[j] == 1 && dict.contains(S.substring(i, j))){
                    dp[i] = 1;
                }
            }

            if (dict.contains(S.substring(i))){
                dp[i] = 1;
            }
        }

        System.out.println(dp[0]);
    }
}
