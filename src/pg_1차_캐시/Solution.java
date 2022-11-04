package pg_1차_캐시;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> cache = new LinkedList<>();
        for(int i = 0; i < cities.length; i++){
            String city = cities[i].toUpperCase();
            if(cache.remove(city)){
                answer += 1;
                cache.offer(city);
            }
            else{
                answer += 5;
                if(cacheSize == 0){
                    if(cache.isEmpty()) continue;
                    cache.poll();
                    cache.offer(city);
                    continue;
                }
                cacheSize--;
                cache.offer(city);
            }
        }
        return answer;
    }
}
