package bj1516;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        int[] inDegree = new int[N + 1];
        int[] times = new int[N + 1];
        StringTokenizer st;
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int beforeBuild = Integer.parseInt(st.nextToken());
                if (beforeBuild == -1){
                    break;
                }
                graph[beforeBuild].add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++){
            if (inDegree[i] == 0){
                queue.offer(i);
            }
        }

        int[] result = new int[N + 1];

        while(!queue.isEmpty()){
            int beforeBuild = queue.poll();

            for (int nextBuild : graph[beforeBuild]){
                inDegree[nextBuild]--;
                result[nextBuild] = Math.max(result[nextBuild], result[beforeBuild] + times[beforeBuild]);
                if (inDegree[nextBuild] == 0){
                    queue.offer(nextBuild);
                }
            }
        }

        for (int i = 1; i < N + 1; i++){
            bw.write((result[i] + times[i]) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
