package bj2217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] ropes = new int[N];
        for(int n = 0; n < N; n++){
            ropes[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes);

        int max = 0;
        for(int n = 0; n < N; n++){
            max = Math.max(max, ropes[n] * (N - n));
        }

        System.out.println(max);
    }
}
