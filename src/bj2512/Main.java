package bj2512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] budget = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++){
            budget[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(budget);

        int result = 0;
        int sumBudget = Integer.parseInt(br.readLine());
        for (int i = 1; i < N + 1; i++){
            int avg = sumBudget / (N + 1 - i);
            if (avg >= budget[i]){
                result = Math.max(budget[i], result);
                sumBudget -= budget[i];
            }
            else{
                result = Math.max(avg, result);
                break;
            }
        }

        System.out.println(result);
    }
}
