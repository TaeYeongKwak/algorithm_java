package pg_124_나라의_숫자;

public class Solution {
    public String solution(int n) {
        String answer = "";
        char[] num = {'4', '1', '2'};
        StringBuilder sb = new StringBuilder();
        int mod;
        while(n > 0){
            mod = n % 3;
            n /= 3;
            if(mod == 0) n--;

            sb.append(num[mod]);
        }
        answer = sb.reverse().toString();
        return answer;
    }
}
