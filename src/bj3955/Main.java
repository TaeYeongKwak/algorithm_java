package bj3955;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            long K = Long.parseLong(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            long x0, y0;
            EGResult result = extendedGCD(K, C);
            if (result.r != 1)
                bw.write("IMPOSSIBLE\n");
            else{
                x0 = result.s;
                y0 = result.t;

                y0 %= K;
                if(y0 < 0) y0 += K;
                y0 = Math.max(y0, (K + C) / C);
                if(y0 <= Math.pow(10, 9)) {
                    bw.write(y0 + "\n");
                }else {
                    bw.write("IMPOSSIBLE\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static EGResult extendedGCD(long a, long b){
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
        return new EGResult(s0, t0, r0);
    }
}

class EGResult{
    long s;
    long t;
    long r;

    public EGResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }

    @Override
    public String toString() {
        return "EGResult{" +
                "s=" + s +
                ", t=" + t +
                ", r=" + r +
                '}';
    }
}
