package bj1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int result = n + 1;

        int[] num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        while(true){
            //합이 s보다 크거나 같을 경우
            if (sum >= s){
                sum -= num[left++];
                result = Math.min(result, (right - left) + 1);
            }
            //right가 배열 크기와 같을 때
            else if (right == n) {
                break;
            }
            //합이 s보다 작을 경우
            else if (sum < s){
                sum += num[right++];
            }
        }

        if (result == n + 1)
            System.out.println(0);
        else
            System.out.println(result);
    }
}
