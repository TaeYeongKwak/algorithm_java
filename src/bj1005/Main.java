package bj1005;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, W;
    static int[] buildTime;
    static int[] inDegree;
    static ArrayList<Integer>[] graph;
    static int[] beforeBuildTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int t = 1; t < T + 1; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            inDegree = new int[N + 1];
            buildTime = new int[N + 1];
            beforeBuildTime = new int[N + 1];
            graph = new ArrayList[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int n = 1; n < N + 1; n++){
                buildTime[n] = Integer.parseInt(st.nextToken());
                graph[n] = new ArrayList<>();
            }

            for (int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                inDegree[to]++;
            }

            W = Integer.parseInt(br.readLine());
            build();
            bw.write(beforeBuildTime[W] + buildTime[W] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void build(){
        Queue<Integer> queue = new LinkedList<>();
        for (int n = 1; n < N + 1; n++){
            if (inDegree[n] == 0){
                queue.offer(n);
            }
        }

        while (!queue.isEmpty()){
            int now = queue.poll();

            if (now == W){
                break;
            }

            for (int next : graph[now]){
                inDegree[next]--;
                beforeBuildTime[next] = Math.max(beforeBuildTime[next], beforeBuildTime[now] + buildTime[now]);

                if (inDegree[next] == 0){
                    queue.offer(next);
                }
            }
        }
    }
}
