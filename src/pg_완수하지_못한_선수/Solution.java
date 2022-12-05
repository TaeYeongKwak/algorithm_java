package pg_완수하지_못한_선수;

import java.util.*;

public class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < completion.length; i++){
            map.put(completion[i], map.getOrDefault(completion[i], 0) + 1);
        }

        for(int i = 0; i < participant.length; i++){
            if(map.containsKey(participant[i])){
                int count = map.get(participant[i]);
                if(count == 1){
                    map.remove(participant[i]);
                    continue;
                }
                map.put(participant[i], count - 1);
            }
            else{
                answer = participant[i];
                break;
            }
        }

        return answer;
    }
}
