package pg_연속_부분_수열_합의_개수;

import java.util.*;

public class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        List<Integer> elementList = new ArrayList<>();
        for(int k = 0; k < 2; k++){
            for(int i = 0; i < elements.length; i++){
                elementList.add(elements[i]);
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < elements.length; i++){
            int sum = 0;
            for(int j = 0; j < elements.length; j++){
                sum += elementList.get(i + j);
                set.add(sum);
            }
        }
        answer = set.size();
        return answer;
    }
}
