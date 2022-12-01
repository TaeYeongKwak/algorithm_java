package pg_압축;

import java.util.*;

public class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        Map<String, Integer> dic = new HashMap<>();
        for(int i = 1; i < 27; i++){
            dic.put(String.valueOf((char)(i + 64)), i);
        }

        String str;
        int last = 0;
        int index = 27;
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < msg.length(); i++){
            boolean check = true;
            for(int j = i + 1; j < msg.length() + 1; j++){
                str = msg.substring(i, j);
                if(dic.containsKey(str)){
                    last = dic.get(str);
                }
                else{
                    check = false;
                    result.add(last);
                    dic.put(str, index++);
                    i = j - 2;
                    break;
                }
            }

            if(check) {
                result.add(last);
                break;
            }
        }
        answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}
