package bj2981;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];

        for (int i = 0; i < N; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(num);

        int gcdResult = num[1] - num[0];
        for (int i = 2; i < N; i++) {
            gcdResult = gcd(gcdResult, num[i] - num[i - 1]);
        }

        for (int i = 2; i <= gcdResult; i++) {
            if(gcdResult % i == 0) {
                bw.write(i + " ");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int gcd(int a, int b){
        return (b == 0)? a : gcd(b, a % b);
    }
}
