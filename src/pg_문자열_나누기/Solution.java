package pg_문자열_나누기;

public class Solution {
    public int solution(String s) {
        int answer = 0;
        int same = 0;
        int diff = 0;
        int firstIndex = 0;

        for(int i = 0; i < s.length(); i++){
            if(i == firstIndex){
                same = 1;
                continue;
            }

            if(s.charAt(firstIndex) == s.charAt(i)){
                same++;
            }
            else if(s.charAt(firstIndex) != s.charAt(i)){
                diff++;
            }

            if(same == diff){
                answer++;
                same = 0;
                diff = 0;
                firstIndex = i + 1;
            }

        }

        if(firstIndex < s.length()){
            answer++;
        }

        return answer;
    }
}
