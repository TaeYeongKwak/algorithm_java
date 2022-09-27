package bj13335;

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
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Queue<Integer> wait = new LinkedList<>();
        Queue<int[]> bridge = new LinkedList();
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++){
            wait.offer(Integer.parseInt(st.nextToken()));
        }

        int weight = 0;
        int out = 0;
        int time = 0;
        while(out < N){
            time++;

            if (!bridge.isEmpty() && bridge.peek()[1] == time){
                int[] truck = bridge.poll();
                weight -= truck[0];
                out++;
            }

            if (bridge.size() < W){
                if (!wait.isEmpty() && wait.peek() + weight <= L){
                    int truck = wait.poll();
                    weight += truck;
                    bridge.offer(new int[]{truck, time + W});
                }
            }
        }

        System.out.println(time);
    }
}
