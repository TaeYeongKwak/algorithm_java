package bj1837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String[] p = st.nextToken().split("");
        int k = Integer.parseInt(st.nextToken());
        boolean[] isNotPrime = new boolean[k];

        // 소수 구하기
        for (int i = 2; i < k; i++){
            //해당 수가 아니라면 건너뛴다.
            if (isNotPrime[i])
                continue;
            
            // 해당 수가 소수라면 해당 수의 배수를 제거
            int idx = 2;
            while(i * idx < k){
                isNotPrime[i * (idx++)] = true;
            }
        }

        // 소수로 p 나누기
        boolean isGood = true;
        for (int i = 2; i < k; i++){
            if (!isNotPrime[i]){
                if (isDivide(p, i)){
                    System.out.println("BAD " + i);
                    isGood = false;
                    break;
                }
            }
        }

        if (isGood){
            System.out.println("GOOD");
        }
    }

    static boolean isDivide(String[] p, int b){
        int x = 0;
        for (int i = 0; i < p.length; i++){
            x *= 10;
            x += Integer.parseInt(p[i]);
            x %= b;
        }
        return (x == 0);
    }
}
