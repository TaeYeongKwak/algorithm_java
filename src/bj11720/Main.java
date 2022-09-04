package bj11720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] num = br.readLine().split("");
        int sum = 0;
        for (int i = 0; i < N; i++){
            sum += Integer.parseInt(num[i]);
        }
        System.out.println(sum);
    }
}
