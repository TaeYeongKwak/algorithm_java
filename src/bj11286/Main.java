package bj11286;

import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {

        class AbsInteger implements Comparable<AbsInteger> {
            int i;

            AbsInteger(Integer i){
                this.i = i;
            }

            @Override
            public int compareTo(AbsInteger o) {
                int temp = Integer.compare(Math.abs(this.i), Math.abs(o.i));
                return (temp != 0)? temp : Integer.compare(this.i, o.i);
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<AbsInteger> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++){
            AbsInteger absI = new AbsInteger(Integer.parseInt(br.readLine()));
            if(absI.i != 0) {
                queue.offer(absI);
            }else{
                bw.write(((!queue.isEmpty())? queue.poll().i : 0) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
