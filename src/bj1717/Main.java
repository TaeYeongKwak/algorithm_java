package bj1717;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++){
            parent[i] = i;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (command == 0){
                union(a, b);
            }
            else if(command == 1){
                int aRoot = find(a);
                int bRoot = find(b);

                bw.write(((aRoot == bRoot)? "YES" : "NO") + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int find(int x){
        if (x == parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        parent[aRoot] = bRoot;
    }
}
