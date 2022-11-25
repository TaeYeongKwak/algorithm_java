package pg_문자열_내_p와_y의_개수;

public class Solution {
    boolean solution(String s) {
        boolean answer = true;

        int p = 0;
        int y = 0;
        s = s.toUpperCase();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'P'){
                p++;
            }
            else if(s.charAt(i) == 'Y'){
                y++;
            }
        }
        answer = (p == y);
        return answer;
    }
}
