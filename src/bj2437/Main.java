package bj2437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 1; n < N + 1; n++){
            weight[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weight);
        int sum = 0;
        for (int i = 1; i < N + 1; i++) {
            if (sum + 1 < weight[i]) {
                break;
            }
            sum += weight[i];
        }

        System.out.println(sum + 1);
    }
}
