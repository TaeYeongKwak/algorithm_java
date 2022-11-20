package pg_단속카메라;

import java.util.Arrays;

public class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        int camera = -30000;
        for(int[] route : routes){
            if(camera < route[0]){
                camera = route[1];
                answer++;
            }
        }
        return answer;
    }
}
