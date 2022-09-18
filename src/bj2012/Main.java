package bj2012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] num = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(num);

        long sum = 0;
        for (int i = 1; i < N + 1; i++){
            sum += Math.abs(i - num[i]);
        }

        System.out.println(sum);
    }
}
