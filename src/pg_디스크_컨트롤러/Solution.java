package pg_디스크_컨트롤러;

import java.util.*;

public class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> waitHeap = new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));

        for(int[] job : jobs){
            waitHeap.offer(job);
        }

        PriorityQueue<int[]> runHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int time = 0;
        int count = 0;
        while(count < jobs.length){
            while(!waitHeap.isEmpty() && waitHeap.peek()[0] <= time){
                runHeap.offer(waitHeap.poll());
            }

            if(!runHeap.isEmpty()){
                int[] cur = runHeap.poll();
                answer += cur[1] + time - cur[0];
                time += cur[1];
                count++;
            }
            else if(!waitHeap.isEmpty()){
                time = waitHeap.peek()[0];
            }
        }

        answer /= jobs.length;
        return answer;
    }
}
