package pg_줄_서는_방법;

import java.util.*;

public class Solution {
    public int[] solution(int n, long k) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();

        long fact = 1;
        for(int i = 1; i <= n; i++){
            list.add(i);
            fact *= i;
        }

        answer = new int[n];
        k--;
        int index = 0;
        while(index < n){
            fact /= n - index;
            answer[index++] = list.remove((int) (k / fact));
            k %= fact;
        }

        return answer;
    }
}
