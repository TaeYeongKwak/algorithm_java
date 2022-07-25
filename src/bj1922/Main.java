package bj1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<int[]> edgeList = new ArrayList<>();
        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            parent[i] = i;
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            //0 : from, 1 : to, 2 : weight
            int[] edge = new int[3];
            for (int j = 0; j < 3; j++){
                edge[j] = Integer.parseInt(st.nextToken());
            }
            edgeList.add(edge);
        }

        Collections.sort(edgeList, (o1, o2) -> o1[2] - o2[2]);

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
        int aRoot = find(a);
        int bRoot = find(b);

        parent[aRoot] = bRoot;
    }

}
