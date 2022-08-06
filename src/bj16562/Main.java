package bj16562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] parent;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        cost = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++){
            cost[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        int result = 0;
        for (int i = 1; i < N + 1; i++){
            if (parent[i] == i){
                result += cost[i];
            }
        }

        if (K - result < 0){
            System.out.println("Oh no");
        }
        else{
            System.out.println(result);
        }

    }

    static int find(int x){
        return parent[x] = (parent[x] == x)? x : find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if (cost[aRoot] > cost[bRoot]){
            parent[aRoot] = bRoot;
        }else{
            parent[bRoot] = aRoot;
        }

    }
}
