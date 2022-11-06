package pg_야근_지수;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int work : works){
            pq.offer(work);
        }
        while(n > 0){
            int work = pq.poll() - 1;
            pq.offer((work > 0? work : 0));
            n--;
        }

        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}
