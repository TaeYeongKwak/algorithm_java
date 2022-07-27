package bj17398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, Q;
    static int[] parent;
    static int[][] connect;
    static int[] cut;
    static boolean[] isCut;
    static long[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        count = new long[N + 1];
        for (int i = 1; i < N + 1; i++){
            parent[i] = i;
            count[i] = 1;
        }

        connect = new int[M + 1][2];
        for (int i = 1; i < M + 1; i++){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            connect[i][0] = X;
            connect[i][1] = Y;
        }

        cut = new int[Q + 1];
        isCut = new boolean[M + 1];
        for (int i = 1; i < Q + 1; i++){
            int q = Integer.parseInt(br.readLine());
            isCut[q] = true;
            cut[i] = q;
        }

        for (int i = 1; i < M + 1; i++){
            if (!isCut[i]){
                union(connect[i][0], connect[i][1]);
            }
        }

        long answer = 0;

        for (int i = Q; i > 0; i--){
            int a = connect[cut[i]][0];
            int b = connect[cut[i]][1];
            answer += union(a, b);
        }

        System.out.println(answer);

    }

    static int find(int x){
        return parent[x] = (x == parent[x])? x : find(parent[x]);
    }

    static long union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return 0;

        long aCnt = count[aRoot];
        long bCnt = count[bRoot];

        count[aRoot] += bCnt;
        count[bRoot] = 0;
        parent[bRoot] = aRoot;

        return aCnt * bCnt;
    }
}
