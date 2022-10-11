package bj3036;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] saw = new int[N];
        for (int i = 0; i < N; i++){
            saw[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++){
            int value = gcd(saw[0], saw[i]);
            bw.write(String.format("%d/%d\n", (saw[0] / value), saw[i] / value));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int gcd(int a, int b){
        return (b == 0)? a : gcd(b, a % b);
    }
}
