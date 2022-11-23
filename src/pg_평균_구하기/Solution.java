package pg_평균_구하기;

public class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        for(int n : arr){
            answer += n;
        }
        answer /= (double) arr.length;
        return answer;
    }
}
