package bj1966;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static PriorityQueue<Integer> pq;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        pq = new PriorityQueue<>(Comparator.reverseOrder());
        queue = new LinkedList<>();

        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            pq.clear();
            queue.clear();
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++){
                int value = Integer.parseInt(st.nextToken());
                queue.offer(new int[]{n, value});
                pq.offer(value);
            }

            int order = 0;
            while(!queue.isEmpty()){
                int[] now = queue.poll();
                if (now[1] == pq.peek()){
                    pq.poll();
                    order++;
                }
                else if (now[1] < pq.peek()){
                    queue.offer(now);
                    continue;
                }

                if (now[0] == M){
                    break;
                }
            }

            bw.write(order + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
