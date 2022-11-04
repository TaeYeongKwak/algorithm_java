package pq_더_맵게;

import java.util.PriorityQueue;

public class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int sco : scoville){
            pq.offer(sco);
        }

        while(!pq.isEmpty()){
            int low = pq.poll();
            if(low < K){
                if(pq.isEmpty()){
                    answer = -1;
                    break;
                }
                int nextLow = pq.poll();
                pq.offer(low + (2 * nextLow));
                answer++;
            }
        }
        return answer;
    }
}
