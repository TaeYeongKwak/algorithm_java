package pg_최솟값_만들기;

import java.util.Arrays;

public class Solution {
    public int solution(int []A, int []B) {
        int answer = 0;
        int len = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
        for(int i = 0; i < len; i++){
            answer += A[i] * B[len - i - 1];
        }

        return answer;
    }
}
