package pg_기능개발;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < progresses.length; i++){
            int work = 100 - progresses[i];
            int time = work / speeds[i];
            if(work % speeds[i] > 0){
                time++;
            }

            queue.offer(time);
        }

        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            int time = queue.poll();
            int cnt = 1;
            while(!queue.isEmpty() && time >= queue.peek()){
                queue.poll();
                cnt++;
            }
            list.add(cnt);
        }

        answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }

        return answer;
    }
}
