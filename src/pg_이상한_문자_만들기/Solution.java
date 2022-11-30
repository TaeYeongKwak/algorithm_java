package pg_이상한_문자_만들기;

public class Solution {
    public String solution(String s) {
        String answer = "";
        String[] sArray = s.split("");
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(String str : sArray){
            cnt = str.contains(" ")? 0 : cnt + 1;
            sb.append(((cnt % 2 == 0)? str.toLowerCase() : str.toUpperCase()));
        }
        answer = sb.toString();
        return answer;
    }
}
