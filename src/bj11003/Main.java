package bj11003;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Deque<int[]> deque = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            while(!deque.isEmpty() && deque.peekLast()[0] > num) {
                deque.pollLast();
            }

            deque.offer(new int[] {num, i});
            if(deque.peek()[1] < i - l + 1) {
                deque.poll();
            }
            bw.write(deque.peek()[0] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
