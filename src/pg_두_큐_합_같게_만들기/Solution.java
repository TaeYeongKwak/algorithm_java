package pg_두_큐_합_같게_만들기;

import java.util.*;

public class Solution {

    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        int size = queue1.length;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum1 = 0;
        long sum2 = 0;
        for(int i = 0; i < size; i++){
            q1.offer(queue1[i]);
            sum1 += queue1[i];

            q2.offer(queue2[i]);
            sum2 += queue2[i];
        }

        answer = 0;
        int k = 0;
        while(sum1 != sum2){
            if(sum1 < sum2){
                k = q2.poll();
                sum2 -= k;
                q1.offer(k);
                sum1 += k;
            }
            else if(sum1 > sum2){
                k = q1.poll();
                sum1 -= k;
                q2.offer(k);
                sum2 += k;
            }
            answer++;

            if(answer > (4 * size)){
                return -1;
            }
        }

        return answer;
    }

}
