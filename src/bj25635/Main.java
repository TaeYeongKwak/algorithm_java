package bj25635;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> nums = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++){
            nums.offer(Long.parseLong(st.nextToken()));
        }

        long result = 0;
        long temp = 0;
        while(!nums.isEmpty()){
            long x = nums.poll();
            if (temp == 0){
                temp += x;
            }
            else{
                result += temp * 2;
                temp = x - temp;
            }
        }

        if (temp > 0){
            result++;
        }

        System.out.println(result);
    }
}
