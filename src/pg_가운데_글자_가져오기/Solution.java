package pg_가운데_글자_가져오기;

public class Solution {
    public String solution(String s) {
        String answer = "";
        int len = s.length();
        answer = s.substring((len - 1) / 2, (len / 2) + 1);
        return answer;
    }
}
