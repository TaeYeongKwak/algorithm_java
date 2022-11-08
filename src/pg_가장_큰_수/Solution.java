package pg_가장_큰_수;

import java.util.PriorityQueue;

public class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        for(int num : numbers){
            pq.offer(String.valueOf(num));
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            sb.append(pq.poll());
        }

        answer = sb.toString();

        if(answer.charAt(0) == '0') answer = "0";

        return answer;
    }
}
