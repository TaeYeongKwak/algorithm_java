package pg_다음_큰_숫자;

public class Solution {
    public int solution(int n) {
        int answer = 0;
        int oneCnt = Integer.bitCount(n);

        answer = n + 1;
        while(true){
            int temp = Integer.bitCount(answer);
            if(temp == oneCnt){
                break;
            }
            answer++;
        }

        return answer;
    }
}
