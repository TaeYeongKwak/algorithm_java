package pg_섬_연결하기;

import java.util.Arrays;

public class Solution {

    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));
        for(int[] cost : costs){
            int aRoot = find(cost[0]);
            int bRoot = find(cost[1]);

            if(aRoot == bRoot) continue;

            union(cost[0], cost[1]);
            answer += cost[2];
        }

        return answer;
    }

    static int find(int x){
        return parent[x] = (parent[x] == x)? x : find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        parent[aRoot] = parent[bRoot];
    }

}
