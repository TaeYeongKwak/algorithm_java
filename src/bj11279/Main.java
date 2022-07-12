package bj11279;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++){
            int x = Integer.parseInt(br.readLine());
            if(x != 0) {
                queue.offer(x);
            }else{
                bw.write(((!queue.isEmpty())? queue.poll() : 0) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
