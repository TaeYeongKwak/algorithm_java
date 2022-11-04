package pg_h_index;

import java.util.Arrays;

public class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int n = citations.length;
        Arrays.sort(citations);
        for(int i = 0; i < n; i++){
            int h = citations[i];
            if(h <= n - i){
                answer = h;
                continue;
            }
            else{
                for(int j = answer; j < h; j++){
                    if(j <= n - i){
                        answer = j;
                        continue;
                    }
                }
                break;
            }
        }
        return answer;
    }
}
