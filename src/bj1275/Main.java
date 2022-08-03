package bj1275;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static long[] num;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        num = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        tree = new long[4 * N];
        init(1, N, 1);

        for (int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (x > y){
                int temp = x;
                x = y;
                y = temp;
            }

            bw.write(query(1, N, 1, x, y) + "\n");

            update(1, N, 1, a, b - num[a]);
            num[a] = b;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static long init(int left, int right, int node){
        if (left == right) return tree[node] = num[left];
        int mid = (left + right) / 2;
        return tree[node] = init(left, mid, node * 2) + init(mid + 1, right, 2 * node + 1);
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){
        if (left > queryRight || queryLeft > right){
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

    static void update(int left, int right, int node, int target, long diff){
        if (left > target || target > right){
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
