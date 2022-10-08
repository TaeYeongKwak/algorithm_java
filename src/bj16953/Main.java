package bj16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        Queue<Long> queue = new LinkedList<>();
        queue.offer(A);

        int result = -1;
        int cnt = 1;
        while(!queue.isEmpty()){
            int size = queue.size();

            boolean flag = false;
            for (int i = 0; i < size; i++){
                long n = queue.poll();

                if (n == B){
                    result = cnt;
                    flag = true;
                    break;
                }

                long num1 = n * 2;
                if (num1 <= B){
                    queue.offer(num1);
                }

                long num2 = n * 10 + 1;
                if (num2 <= B){
                    queue.offer(num2);
                }
            }
            if (flag){
                break;
            }
            cnt++;
        }

        System.out.println(result);
    }
}
