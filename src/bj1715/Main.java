package bj1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++){
            pq.offer(Long.parseLong(br.readLine()));
        }

        long result = 0;

        while(pq.size() > 1){
            long card1 = pq.poll();
            long card2 = pq.poll();
            result += card1 + card2;
            pq.offer(card1 + card2);
        }

        System.out.println(result);
    }
}
