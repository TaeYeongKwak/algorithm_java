package bj1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;
    static ArrayList<int[]> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            parent[i] = i;
        }

        graph = new ArrayList<>();
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.add(new int[]{from, to, cost});
        }

        Collections.sort(graph, Comparator.comparingInt(o -> o[2]));

        ArrayList<Integer> edgeCost = new ArrayList<>();
        int sum = 0;
        for (int[] edge : graph){
            int aRoot = find(edge[0]);
            int bRoot = find(edge[1]);

            if (aRoot == bRoot) continue;
            union(edge[0], edge[1]);
            sum += edge[2];
            edgeCost.add(edge[2]);

            if (edgeCost.size() == N - 1) break;
        }

        int result = Integer.MAX_VALUE;
        for (int cost : edgeCost){
            result = Math.min(sum - cost, result);
        }

        System.out.println(result);
    }

    static int find(int x){
        return parent[x] = (x == parent[x])? x : find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;
        parent[aRoot] = bRoot;
    }
}


