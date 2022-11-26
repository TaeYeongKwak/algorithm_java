package pg_거스름돈;

public class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for(int i = 0; i < money.length; i++){
            for(int j = money[i]; j <= n; j++){
                dp[j] += (dp[j - money[i]] % 1000000007);
            }
        }

        answer = (int) dp[n];
        return answer;
    }
}
