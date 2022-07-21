package bj11505;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static long[] num;
    static long[] tree;
    static long MOD_VALUE = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        num = new long[N + 1];
        tree = new long[4 * N];
        for (int i = 1; i < N + 1; i++){
            num[i] = Long.parseLong(br.readLine());
        }

        init(1, N, 1);

        for (int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1){
                num[b] = c;
                update(1, N, 1, b, c);
            }else if(a == 2){
                long x = query(1, N, 1, b, c);
                bw.write(x + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static long init(int left, int right, int node){
        if (left == right){
            return tree[node] = num[left];
        }

        int middle = (left + right) / 2;
        return tree[node] = (init(left, middle, node * 2) * init(middle + 1, right, (node * 2) + 1)) % MOD_VALUE;
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){
//        System.out.println(node+ " " + tree[node]);
        // 연관이 없을 경우
        if (left > queryRight || right < queryLeft){
            return 1;
        }
        // 연관이 있을 경우
        else if (queryLeft <= left && right <= queryRight){
            return tree[node] % MOD_VALUE;
        }
        // 연관이 있을 수도 있을 경우
        else{
            int middle = (left + right) / 2;
            return (query(left, middle, 2 * node, queryLeft, queryRight)
                    * query(middle + 1, right, (2 * node) + 1, queryLeft, queryRight)) % MOD_VALUE;
        }
    }

    static long update(int left, int right, int node, int target, long diff){
        // target이 범위에 없을 경우
        if(target < left || target > right){
            return tree[node];
        }
        // 리프 노드에 도착했을 경우
        if (left == right) {
            return tree[node] = diff;
        }
        // 나머지
        int middle = (left + right) / 2;
        return tree[node] = (update(left, middle, 2 * node, target, diff)
                * update(middle + 1, right, (2 * node) + 1, target, diff)) % MOD_VALUE;
    }
}
