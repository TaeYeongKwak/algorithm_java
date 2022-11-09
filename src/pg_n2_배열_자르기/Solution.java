package pg_n2_배열_자르기;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();

        for(long i = left; i <= right; i++){
            list.add((int)(Math.max(i / n, i % n) + 1));
        }

        answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = (int)list.get(i);
        }
        return answer;
    }
}
