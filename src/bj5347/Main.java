package bj5347;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            bw.write(lcm(a, b) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static long gcd(long a, long b){
        return (b == 0)? a : gcd(b, a % b);
    }

    static long lcm(long a, long b){
        return (a * b) / gcd(a, b);
    }
}
