package bj1978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[] isNotPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            if (!isNotPrime[Integer.parseInt(st.nextToken())]) answer++;
        }

        System.out.println(answer);
    }

    static void init(){
        isNotPrime = new boolean[1001];
        isNotPrime[1] = true;
        for(int i = 2; i < 1001; i++){
            if (isNotPrime[i]) continue;
            for (int j = 2; i * j < 1001; j++){
                isNotPrime[i * j] = true;
            }
        }
    }
}
