package pg_다리를_지나는_트럭;

import java.util.*;

public class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();

        int cnt = 0;
        int index = 0;
        int tw = 0;
        while(true){
            answer++;

            if(!queue.isEmpty() && (answer - queue.peek()[0]) == bridge_length){
                tw = queue.poll()[1];
                weight += tw;
                cnt++;
            }

            if(queue.size() < bridge_length && index < truck_weights.length && weight - truck_weights[index] >= 0){
                tw = truck_weights[index++];
                queue.offer(new int[]{answer, tw});
                weight -= tw;
            }

            if(cnt == truck_weights.length){
                break;
            }
        }

        return answer;
    }
}
