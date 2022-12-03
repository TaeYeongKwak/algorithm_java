package pg_정수_제곱근_판별;

public class Solution {
    public long solution(long n) {
        long answer = -1;
        Double sqrt = Math.sqrt(n);
        if(sqrt == sqrt.intValue()){
            return (long) Math.pow(sqrt + 1, 2);
        }
        return answer;
    }
}
