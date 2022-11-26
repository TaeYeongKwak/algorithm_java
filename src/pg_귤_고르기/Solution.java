package pg_귤_고르기;

import java.util.*;

public class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int n = tangerine.length - k;
        Map<Integer, int[]> map = new HashMap<>();
        List<int[]> list = new ArrayList<>();
        for(int tan : tangerine){
            if(map.containsKey(tan)){
                map.get(tan)[1]++;
            }
            else{
                int[] info = {tan, 1};
                map.put(tan, info);
                list.add(info);
            }
        }

        Collections.sort(list, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        for(int[] info : list){
            if(n == 0){
                answer++;
            }
            else if(n > 0){
                if(info[1] < n){
                    n -= info[1];
                }
                else if(info[1] > n){
                    n = 0;
                    answer++;
                }
                else if(info[1] == n){
                    n = 0;
                }
            }
        }

        return answer;
    }
}
