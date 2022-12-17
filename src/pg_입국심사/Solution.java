package pg_입국심사;

import java.util.Arrays;

public class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);

        long left = 0;
        long right = (long)times[times.length - 1] * n;
        long sum, mid;
        while(left <= right){
            sum = 0;
            mid = (left + right) / 2;
            for(int time : times){
                sum += mid / time;
            }
            if(sum < n){
                left = mid + 1;
            }
            else{
                answer = mid;
                right = mid - 1;
            }
        }
        return answer;
    }
}
