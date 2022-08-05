package bj10999;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static long[] num;
    static long[] tree, lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        num = new long[N + 1];
        tree = new long[4 * N];
        lazy = new long[4 * N];

        for (int i = 1; i < N + 1; i++){
            num[i] = Long.parseLong(br.readLine());
        }

        init(1, N, 1);

        for (int x = 0; x < M + K; x++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1){
                long d = Long.parseLong(st.nextToken());
                update(1, N, 1, b, c, d);
            }
            else if (a == 2){
                bw.write(query(1, N, 1, b, c) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static long init(int left, int right, int node){
        if (left == right) return tree[node] = num[left];
        int mid = (left + right) / 2;
        return tree[node] = init(left, mid, 2 * node) + init(mid + 1, right, 2 * node + 1);
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){
        propagate(left, right, node);
        if (left > queryRight || right < queryLeft){
            return 0;
        }
        else if (queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        else{
            int mid = (left + right) / 2;
            return query(left, mid, 2 * node, queryLeft, queryRight)
                    + query(mid + 1, right, 2 * node + 1, queryLeft, queryRight);
        }
    }

    static void update(int left, int right, int node, int updateLeft, int updateRight, long diff){
        propagate(left, right, node);
        if (left > updateRight || right < updateLeft){
            return;
        }
        else if(updateLeft <= left && right <= updateRight){
            lazy[node] = diff;
            propagate(left, right, node);
            return;
        }
        int mid = (left + right) / 2;
        update(left, mid, 2 * node, updateLeft, updateRight, diff);
        update(mid + 1, right, 2 * node + 1, updateLeft, updateRight, diff);
        tree[node] = tree[2 * node] + tree[2 * node + 1];

    }

    static void propagate(int left, int right, int node){
        // lazy 값이 0이 아닐 경우
        if (lazy[node] != 0){
            // left != right 일 경우 (리프 노드가 아닐 경우)
            if (left != right){
                lazy[2 * node] += lazy[node];
                lazy[2 * node + 1] += lazy[node];
            }
            tree[node] += lazy[node] * (right - left + 1);
            lazy[node] = 0;
        }
    }
}
