package pg_전력망을_둘로_나누기;

import java.util.*;

public class Solution {

    static int[] parent;
    static int[] cnt;
    static int n;
    static int[][] wires;
    static int result;

    public int solution(int n, int[][] wires) {
        int answer = -1;
        parent = new int[n + 1];
        cnt = new int[n + 1];
        this.n = n;
        this.wires = wires;
        result = 101;
        for(int i = 0; i < n - 1; i++){
            search(i);
        }

        answer = result;
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
        cnt[bRoot] += cnt[aRoot];
        cnt[aRoot] = cnt[bRoot];
    }

    static void search(int k){
        for(int i = 1; i <= n; i++){
            parent[i] = i;
            cnt[i] = 1;
        }

        for(int i = 0; i < wires.length; i++){
            if(k != i){
                union(wires[i][0], wires[i][1]);
            }
        }

        Set<Integer> set = new HashSet<>();

        for(int i = 1; i <= n; i++){
            set.add(cnt[find(i)]);
        }

        if(set.size() == 1){
            result = 0;
        }
        else{
            int[] x = new int[2];
            int i = 0;
            for(int n : set){
                x[i++] = n;
            }
            result = Math.min(result, Math.abs(x[0] - x[1]));
        }
    }

}
