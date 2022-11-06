package pg_네트워크;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    static int[] parent;

    public int solution(int n, int[][] computers) {
        int answer = 0;

        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(computers[i][j] == 1){
                    union(i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            set.add(find(i));
        }

        answer = set.size();
        return answer;
    }

    static int find(int x){
        return parent[x] = (parent[x] == x)? x : find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return;

        parent[aRoot] = bRoot;
    }
}
