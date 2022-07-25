package bj2252;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[N + 1];
        ArrayList<Integer>[] list = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++){
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            inDegree[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++){
            if (inDegree[i] == 0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int zeroDegree = queue.poll();

            for (int x : list[zeroDegree]){
                inDegree[x]--;

                if (inDegree[x] == 0){
                    queue.offer(x);
                }
            }

            bw.write(zeroDegree + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
