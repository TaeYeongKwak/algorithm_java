package bj2960;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] isNotPrime = new boolean[n + 1];

        int kCheck = 0;
        for (int i = 2; i <= n; i++){
            if (isNotPrime[i])
                continue;

            int idx = 1;
            while(i * idx <= n){
                if (!isNotPrime[i * idx]){
                    isNotPrime[i * idx] = true;
                    kCheck++;
                    if (kCheck == k){
                        System.out.println(i * idx);
                        break;
                    }
                }
                idx++;
            }
        }

        br.close();
    }
}
