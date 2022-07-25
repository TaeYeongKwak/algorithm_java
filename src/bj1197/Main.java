package bj1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        ArrayList<int[]> edgeList = new ArrayList<>();
        
        // 루트 초기화
        for (int i = 1; i < V + 1; i++){
            parent[i] = i;
        }

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int[] edge = new int[3];
            for (int j = 0; j < 3; j++){
                edge[j] = Integer.parseInt(st.nextToken());
            }
            edgeList.add(edge);
        }

        Collections.sort(edgeList, Comparator.comparingInt(o -> o[2]));

        int result = 0;
        for (int[] edge : edgeList){
            int fromRoot = find(edge[0]);
            int toRoot = find(edge[1]);

            if (fromRoot == toRoot) continue;

            union(edge[0], edge[1]);
            result += edge[2];
        }

        System.out.println(result);
    }

    static int find(int x){
        return parent[x] = (x == parent[x])? x : find(parent[x]);
    }

    static void union(int a, int b){
        parent[find(a)] = find(b);
    }
}
