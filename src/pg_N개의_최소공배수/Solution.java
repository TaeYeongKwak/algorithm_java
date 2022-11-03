package pg_N개의_최소공배수;

public class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        answer = 1;
        for(int n : arr){
            answer = lcm(answer, n);
        }
        return answer;
    }

    static int gcd(int a, int b){
        return (b == 0)? a : gcd(b, a % b);
    }

    static int lcm(int a, int b){
        return (a * b) / gcd(a, b);
    }
}
