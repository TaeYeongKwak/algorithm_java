package bj17087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] A = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            int a = Integer.parseInt(st.nextToken());
            A[i] = Math.abs(a - S);
        }

        int result = A[1];
        for (int i = 2; i < N + 1; i++){
            result = gcd(result, A[i]);
        }

        System.out.println(result);
    }

    static int gcd(int a, int b){
        return (b == 0)? a : gcd(b, a % b);
    }
}
