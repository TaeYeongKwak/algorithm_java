package bj11724;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static boolean[] visited;
    static Queue<Integer> queue;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        cnt = 0;
        queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++){
            if(!visited[i]){
                bfs(i);
            }
        }

        System.out.println(cnt);
    }

    static void bfs(int start){
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int next : graph[now]){
                if (visited[next]) continue;
                visited[next] = true;
                queue.offer(next);
            }
        }

        cnt++;
    }
}
