package pg_k진수에서_소수_개수_구하기;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(solution(n, k));
    }

    public static int solution(int n, int k) {
        int answer = -1;
        String[] kNumStr = getKNumber(n, k).split("0");
        int result = 0;
        for (String str : kNumStr){
            if (str.length() == 0) continue;
            long x = Long.parseLong(str);
            if (isPrime(x)){
                result++;
            }
        }
        answer = result;
        return answer;
    }

    static String getKNumber(int n, int k){
        StringBuilder sb = new StringBuilder();
        int mod = 0;
        while(n != 0){
            mod = n % k;
            n /= k;
            sb.insert(0, mod);
        }
        return sb.toString();
    }

    static boolean isPrime(long x){
        if (x <= 1) return false;
        for (long i = 2; i <= Math.sqrt(x); i++){
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }
}
