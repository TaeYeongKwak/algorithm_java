package pg_최대공약수와_최소공배수;

public class Solution {
    public int[] solution(int n, int m) {
        int[] answer = {};
        answer = new int[]{gcd(n, m), lcm(n, m)};
        return answer;
    }

    static int gcd(int a, int b){
        return (b == 0)? a : gcd(b, a % b);
    }

    static int lcm(int a, int b){
        return (a * b) / gcd(a, b);
    }
}
