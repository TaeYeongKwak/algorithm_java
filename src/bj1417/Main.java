package bj1417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int myTicket = 0;
        for (int n = 1; n < N + 1; n++){
            int ticket = Integer.parseInt(br.readLine());
            if(n == 1){
                myTicket = ticket;
            }
            else{
                pq.offer(ticket);
            }
        }

        int result = 0;
        while(!pq.isEmpty() && pq.peek() >= myTicket){
            int ticket = pq.poll();
            result += 1;
            myTicket += 1;
            ticket -= 1;
            pq.offer(ticket);
        }

        System.out.println(result);
    }
}
