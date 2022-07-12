package bj1655;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();

        for(int i = 1; i <= n; i++){
            int x = Integer.parseInt(br.readLine());
            if (i == 1) {
                max.offer(x);
            }
            else{
                if (max.peek() < x){
                    min.offer(x);
                }else{
                    max.offer(x);
                }

                if (max.size() < min.size()){
                    max.offer(min.poll());
                }else if (max.size() - min.size() == 2){
                    min.offer(max.poll());
                }
            }

            bw.write(max.peek() + "\n");
        }

        bw.flush();

        bw.close();
        br.close();
    }
}
