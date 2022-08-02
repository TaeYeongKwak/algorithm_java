package bj10868;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] num;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        tree = new int[4 * N];

        init(1, 1, N);

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            bw.write(query(1, N, 1, left, right) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int init(int node, int left, int right){
        if (left == right) return tree[node] = num[left];
        int mid = (left + right) / 2;
        return tree[node] = Math.min(init(node * 2, left, mid), init(node * 2 + 1, mid + 1, right));
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if (right < queryLeft || left > queryRight){
            return Integer.MAX_VALUE;
        }
        else if (queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        else{
            int mid = (left + right) / 2;
            return Math.min(query(left, mid, node * 2, queryLeft, queryRight),
                    query(mid + 1, right, node * 2 + 1, queryLeft, queryRight));
        }
    }
}
