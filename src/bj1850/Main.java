package bj1850;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long gcd = gcd(a, b);

        StringBuilder sb = new StringBuilder();
        for (long i = 0; i < gcd; i++){
            sb.append("1");
        }

        System.out.println(sb.toString());
    }

    static long gcd(long a, long b){
        return (b == 0)? a : gcd(b, a % b);
    }
}
