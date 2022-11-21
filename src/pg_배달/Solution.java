package pg_배달;

import java.util.*;

public class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        List<int[]>[] graph = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] r : road){
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[]{1, 0});
        dist[1] = 0;
        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if(dist[now[0]] < now[1]){
                continue;
            }

            for(int[] next : graph[now[0]]){
                if(dist[next[0]] > now[1] + next[1]){
                    dist[next[0]] = now[1] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        for(int i = 1; i < N + 1; i++){
            if(dist[i] <= K){
                answer++;
            }
        }

        return answer;
    }
}
