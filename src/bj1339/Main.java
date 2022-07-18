package bj1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] alpha = new Integer[26];
        Arrays.fill(alpha, 0);

        String[] word = new String[n];
        for (int i = 0; i < n; i++){
            String str = br.readLine();
            for (int j = 1; j <= str.length(); j++){
                alpha[(int)(str.charAt(j-1) - 'A')] += (int)(Math.pow(10, str.length() - j));
            }
        }



        int sum = 0;
        Arrays.sort(alpha, Comparator.reverseOrder());
        for (int i = 9; i > 0; i--){
            sum += alpha[9-i] * i;
        }

        System.out.println(sum);
    }
}
