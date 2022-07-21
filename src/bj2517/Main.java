package bj2517;

import java.io.*;
import java.util.Arrays;

public class Main {

    static int[] runner;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        runner = new int[N + 1];
        tree = new int[4 * N];
        for (int i = 1; i < N + 1; i++){
            // 실력 기록
            runner[i] = Integer.parseInt(br.readLine());
            update(1, N, 1, runner[i], 1);
        }

    }

    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if (left > queryRight || queryLeft > right){
            return 0;
        }
        else if (queryLeft <= left && right <= queryRight){

        }
    }

    static void update(int left, int right, int node, int target, int diff){
        if (left > target || target > right) {
            return;
        }
        tree[node] += diff;
        if (left != right){
            int mid = (left + right) / 2;
            update(left, mid, 2 * node, target, diff);
            update(mid + 1, right, 2 * node + 1, target, diff);
        }
    }
}
