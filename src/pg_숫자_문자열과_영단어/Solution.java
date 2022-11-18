package pg_숫자_문자열과_영단어;

public class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] numStr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i = 0; i < 10; i++){
            s = s.replace(numStr[i], String.valueOf(i));
        }
        answer = Integer.parseInt(s);
        return answer;
    }
}
