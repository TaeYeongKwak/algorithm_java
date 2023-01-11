package bj2581;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean[] isNotPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        init(N);
        int sum = 0;
        int min = 10001;
        boolean flag = false;
        for (int i = M; i <= N; i++){
            if (isNotPrime[i]) continue;
            sum += i;
            if (!flag){
                min = i;
                flag = true;
            }
        }

        if (flag){
            System.out.println(sum);
            System.out.println(min);
        }
        else{
            System.out.println(-1);
        }
    }

    static void init(int N){
        isNotPrime = new boolean[N + 1];
        isNotPrime[1] = true;

        for(int i = 2; i <= N; i++){
            if (isNotPrime[i]) continue;
            for(int j = 2; j * i <= N; j++){
                isNotPrime[i * j] = true;
            }
        }
    }
}
