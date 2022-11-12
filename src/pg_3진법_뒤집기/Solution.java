package pg_3진법_뒤집기;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        StringBuilder sb = new StringBuilder(Integer.toString(n, 3));
        answer = Integer.parseInt(sb.reverse().toString(), 3);
        return answer;
    }
}
