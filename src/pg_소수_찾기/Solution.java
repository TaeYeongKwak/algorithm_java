package pg_소수_찾기;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean[] isNotPrime = new boolean[n + 1];
        for(int i = 2; i <= n; i++){
            if(isNotPrime[i]) continue;
            for(int j = 2; j * i <= n; j++){
                isNotPrime[i * j] = true;
            }
        }

        for(int i = 2; i <= n; i++){
            if(!isNotPrime[i]) answer++;
        }

        return answer;
    }
}
