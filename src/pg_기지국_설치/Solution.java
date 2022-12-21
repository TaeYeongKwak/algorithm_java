package pg_기지국_설치;

public class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 0;
        int end = 0;
        int x, k;
        for(int i = 0; i < stations.length; i++){
            int left = stations[i] - w;
            int right = stations[i] + w;

            if(left <= 0){
                start = right;
                end = right;
                continue;
            }
            end = left;
            x = end - start - 1;
            k = ((2 * w) + 1);
            answer += x / k;
            if(x % k > 0){
                answer++;
            }

            if(right >= n){
                start = n;
                break;
            }
            start = right;
        }

        x = n - start;
        k = ((2 * w) + 1);
        if(x > 0){
            answer += x / k;
            if(x % k > 0){
                answer++;
            }
        }

        return answer;
    }
}
