package bj2357;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static long[] num;
    static long[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new long[N + 1];
        tree = new long[4 * N][2];
        for (int i = 1; i < N + 1; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        initMax(1, N, 1);
        initMin(1, N, 1);

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long[] maxMin = query(1, N, 1, a, b);
            bw.write(maxMin[1] + " " + maxMin[0] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static long initMax(int left, int right, int node){
        if (left == right) return tree[node][0] = num[left];
        int middle = (left + right) / 2;
        return tree[node][0] = Math.max(initMax(left, middle, 2 * node), initMax(middle + 1, right, (2 * node) + 1));
    }

    static long initMin(int left, int right, int node){
        if (left == right) return tree[node][1] = num[left];
        int middle = (left + right) / 2;
        return tree[node][1] = Math.min(initMin(left, middle, 2 * node), initMin(middle + 1, right, (2 * node) + 1));
    }

    static long[] query(int left, int right, int node, int queryLeft, int queryRight){
        //연관 없을 경우
        if (left > queryRight || right < queryLeft){
            return new long[]{0, 1000000001};
        }
        //연관 있을 경우
        if (queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        //연관이 있을 수도 있을 경우
        else{
            int middle = (left + right) / 2;
            long[] leftNode = query(left, middle, 2 * node, queryLeft, queryRight);
            long[] rightNode = query(middle + 1, right, (2 * node) + 1, queryLeft, queryRight);
            return new long[]{Math.max(leftNode[0], rightNode[0]), Math.min(leftNode[1], rightNode[1])};
        }
    }
}
