package bj2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");

        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] trees = new int[n];
        String[] treeStr = br.readLine().split(" ");
        int maxTreeHigh = 0;
        for (int i = 0; i < n; i++){
            trees[i] = Integer.parseInt(treeStr[i]);
            if (trees[i] > maxTreeHigh) {
                maxTreeHigh = trees[i];
            }
        }

        long high = maxTreeHigh;
        long low = 0;
        while (low <= high){
            long sum = 0;
            long middle = (high + low) / 2;
            for (int i = 0; i < n; i++){
                if (middle < trees[i]) {
                    sum += trees[i] - middle;
                }
            }
            // 합이 m보다 크거나 같을 경우
            if (sum >= m){
                low = middle + 1;
            }
            //합이 m보다 작을 경우
            else{
                high = middle - 1;
            }
        }

        System.out.println(high);
    }
}
