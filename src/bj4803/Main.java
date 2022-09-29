package bj4803;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;
    static Set<Integer> rootSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        rootSet = new HashSet<>();
        int t = 0;
        while(true){
            t++;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            init();
            for (int m = 0; m < M; m++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            int size = getTreeCnt();
            StringBuilder sb = new StringBuilder("Case " + t + ": ");
            if (size == 0){
                sb.append("No trees.");
            }
            else if (size == 1){
                sb.append("There is one tree.");
            }
            else{
                sb.append("A forest of " + size + " trees.");
            }

            bw.write(sb.toString() + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void init(){
        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            parent[i] = i;
        }
    }

    static int find(int x){
        return parent[x] = (parent[x] == x)? x : find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) {
            parent[aRoot] = parent[bRoot] = 0;
            return;
        }

        if (aRoot > bRoot){
            parent[aRoot] = bRoot;
        }
        else if (bRoot >= aRoot){
            parent[bRoot] = aRoot;
        }

    }

    static int getTreeCnt(){
        rootSet.clear();
        for (int i = 1; i < N + 1; i++){
            int iRoot = find(i);
            if (iRoot != 0){
                rootSet.add(iRoot);
            }
        }
        return rootSet.size();
    }
}
