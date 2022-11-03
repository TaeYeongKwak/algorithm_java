package pg_영어_끝말잇기;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};
        Set<String> check = new HashSet<>();
        int num = 1;
        int order = 1;
        boolean flag = false;
        char before = words[0].charAt(words[0].length() - 1);
        for(int i = 0; i < words.length; i++){
            if(i == 0){
                before = words[i].charAt(words[i].length() - 1);
                check.add(words[i]);
                continue;
            }

            num = (i % n) + 1;
            order = (i / n) + 1;

            if(before != words[i].charAt(0) || check.contains(words[i])){
                flag = true;
                break;
            }

            before = words[i].charAt(words[i].length() - 1);
            check.add(words[i]);
        }

        if(!flag){
            num = order = 0;
        }

        answer = new int[]{num, order};
        return answer;
    }
}
