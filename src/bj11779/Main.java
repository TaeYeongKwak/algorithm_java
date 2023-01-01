package bj11779;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<int[]>[] graph;
    static int[] dist;
    static int[] before;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        before = new int[N + 1];

        for(int n = 1; n < N + 1; n++){
            graph[n] = new ArrayList<>();
            dist[n] = Integer.MAX_VALUE;
        }

        StringTokenizer st;
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, cost});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        solution(start, end);
        int index = end;
        Stack<Integer> path = new Stack<>();
        while(index != start){
            path.push(index);
            index = before[index];
        }

        path.push(start);
        bw.write(dist[end] + "\n");
        bw.write(path.size() + "\n");
        while(!path.isEmpty()){
            bw.write(path.pop() + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void solution(int start, int end){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{start, 0});
        dist[start] = 0;

        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if (now[1] > dist[now[0]]){
                continue;
            }

            if (now[0] == end){
                break;
            }

            for(int[] next : graph[now[0]]){
                if (now[1] + next[1] >= dist[next[0]]) continue;
                dist[next[0]] = now[1] + next[1];
                pq.offer(new int[]{next[0], now[1] + next[1]});
                before[next[0]] = now[0];
            }
        }
    }
}
