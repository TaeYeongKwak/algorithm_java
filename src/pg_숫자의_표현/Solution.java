package pg_숫자의_표현;

public class Solution {

    static int cnt;

    public int solution(int n) {
        int answer = 0;
        for(int i = 1; i <= n; i++){
            dfs(i, n - i);
        }

        answer = cnt;
        return answer;
    }

    static void dfs(int r, int n){
        if(n == 0){
            cnt++;
            return;
        }

        if(n - (r + 1) >= 0){
            dfs(r + 1, n - (r + 1));
        }
    }

}
