package bj2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }


        int left = 0;
        int right = N - 1;
        int[] info = new int[3];
        info[0] = num[left];
        info[1] = num[right];
        info[2] = Integer.MAX_VALUE;
        while(left < right){
            int mix = num[left] + num[right];
            if (Math.abs(mix) < info[2]){
                info[0] = num[left];
                info[1] = num[right];
                info[2] = Math.abs(mix);
            }
            if (mix >= 0){
                right--;
            }
            else{
                left++;
            }
        }

        System.out.println(info[0] + " " + info[1]);
    }
}
