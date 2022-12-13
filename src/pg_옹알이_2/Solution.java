package pg_옹알이_2;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        Set<String> set = new HashSet<>();
        set.add("aya");
        set.add("ye");
        set.add("woo");
        set.add("ma");

        for(String b : babbling){
            String last = "";
            int start = 0;

            boolean check = false;
            for(int i = 1; i <= b.length(); i++){
                String s = b.substring(start, i);
                if(set.contains(s) && !s.equals(last)){
                    start = i;
                    check = true;
                    last = s;
                    continue;
                }
                check = false;
            }

            if(check) {
                answer++;
            }
        }

        return answer;
    }
}
