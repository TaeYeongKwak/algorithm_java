package bj2660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Integer>[] graph;
    static Queue<Integer> queue;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (from == -1 && to == -1){
                break;
            }

            graph[from].add(to);
            graph[to].add(from);
        }

        queue = new LinkedList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            int x = Integer.compare(o1[0], o2[0]);
            return (x == 0)? Integer.compare(o1[1], o2[1]) : x;
        });

        for (int i = 1; i < N + 1; i++){
            pq.offer(new int[]{bfs(i), i});
        }

        StringBuilder sb = new StringBuilder();
        int minScore = pq.peek()[0];
        int count = 0;
        while(!pq.isEmpty()){
            if (minScore == pq.peek()[0]){
                count++;
                sb.append(pq.poll()[1] + " ");
            }
            else{
                break;
            }
        }

        System.out.println(minScore + " " + count);
        System.out.println(sb);
    }

    static int bfs(int n){
        Arrays.fill(visited, false);
        queue.clear();
        queue.offer(n);
        visited[n] = true;

        int score = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            boolean flag = false;
            for (int i = 0; i < size; i++){
                int now = queue.poll();

                for (int next : graph[now]){
                    if (visited[next]) continue;
                    visited[next] = true;
                    queue.offer(next);
                    flag = true;
                }
            }

            if(flag){
                score++;
            }
        }

        return score;
    }
}
