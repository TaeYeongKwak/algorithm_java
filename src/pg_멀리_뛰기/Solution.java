package pg_멀리_뛰기;

public class Solution {
    public long solution(int n) {
        long answer = 0;
        int[] dp = new int[2001];
        dp[1] = 1;
        if(n > 1){
            dp[2] = 2;
        }
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= 1234567;
        }

        answer = dp[n];
        return answer;
    }
}
