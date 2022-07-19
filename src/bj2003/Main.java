package bj2003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int result = 0;

        int[] num = new int[n];
        String[] numListStr = br.readLine().split(" ");
        for (int i = 0; i < n; i++){
            num[i] = Integer.parseInt(numListStr[i]);
        }

        int left = 0;
        int right = 1;
        while(right <= n){
            //합 구하기
            int sum = 0;
            for (int i = left; i < right; i++){
                sum += num[i];
            }
            //합이 M보다 작을 경우
            if (sum < m){
                right++;
            }
            //합이 M보다 클 경우
            else if (sum > m){
                left++;
            }
            //합이 M과 같을 경우
            else{
                result++;
                left++;
            }
        }

        System.out.println(result);
    }

}
