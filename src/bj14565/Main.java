package bj14565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long A = Long.parseLong(st.nextToken());


        // 덧셈역
        long addition = N - A;

        // 곱셈역
        MyEGResult multiplication = extendedGCD(A, N);
        long x0 = multiplication.s;

        if (multiplication.r != 1){
            x0 = -1;
        }else{
            while(x0 < 0){
                x0 += N;
            }
        }

        System.out.println(addition + " " + x0);
    }

    static MyEGResult extendedGCD(long a, long b){
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long temp;
        while(r1 != 0){
            long q = r0 / r1;
            temp = r0 - q * r1;
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1;
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1;
            t0 = t1;
            t1 = temp;
        }
        return new MyEGResult(s0, t0, r0);
    }
}

class MyEGResult{
    long s;
    long t;
    long r;

    public MyEGResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }

}