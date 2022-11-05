package pg_최고의_집합;

public class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};

        int k = s / n;
        int x = s % n;
        if(k == 0){
            return new int[]{-1};
        }

        answer = new int[n];
        for(int i = 0; i < n; i++){
            answer[i] = (n - i <= x)? k + 1 : k;
        }

        return answer;
    }
}
