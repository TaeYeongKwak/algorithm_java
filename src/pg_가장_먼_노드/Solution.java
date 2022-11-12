package pg_가장_먼_노드;

import java.util.*;

public class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        boolean[] visited = new boolean[n + 1];
        List<Integer>[] graph = new ArrayList[n + 1];
        int[] dist = new int[n + 1];
        for(int i = 1; i < n + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] e : edge){
            int from = e[0];
            int to = e[1];
            graph[from].add(to);
            graph[to].add(from);
        }

        Queue<Integer> queue = new LinkedList<>();
        visited[1] = true;
        queue.offer(1);

        int max = 0;
        while(!queue.isEmpty()){
            int now = queue.poll();

            max = Math.max(dist[now], max);

            for(int next : graph[now]){
                if(visited[next]) continue;
                visited[next] = true;
                queue.offer(next);
                dist[next] = dist[now] + 1;

            }
        }

        for(int i = 1; i < n + 1; i++){
            if(dist[i] == max) answer++;
        }

        return answer;
    }
}
