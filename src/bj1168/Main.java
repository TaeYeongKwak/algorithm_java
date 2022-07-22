package bj1168;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] tree;
    static boolean[] visited;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder("<");
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = 1;
        while(S < N){
            S *= 2;
        }

        visited = new boolean[N + 1];
        tree = new int[2 * S];

        init(1, N, 1);

        int start = 1;
        int k = K;
        for (int i = 1; i < N; i++){
            //해당 번호의 사람 찾기
            int x = query(1, N, 1,  k, start);
            sb.append(x + ", ");
            // 사람 빼기
            update(1, N, 1, x, -1);


            // 뺄 사람 번호 조정
            start = x + 1;
            int rightSum = sumQuery(1, N, 1, start, N);
            if (rightSum < k){
                start = 1;
                if (tree[1] < k){
                    k %= tree[1];
                }
                else{
                    k -= rightSum;
                }
            }else{
                k = K;
            }
        }
        sb.append(query(1, N, 1,  1, 1));

        sb.append(">");

        bw.write(sb.toString());
    }

    static int init(int left, int right, int node){
        if (left == right) return tree[node] = 1;
        int mid = (left + right) / 2;
        return tree[node] = init(left, mid, 2 * node) + init(mid + 1, right, 2 * node + 1);
    }

    static int sumQuery(int left, int right, int node, int queryLeft, int queryRight){
        if (left > queryRight || right < queryLeft){
            return 0;
        }
        else if (queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        else{
            int mid = (left + right) / 2;
            return sumQuery(left, mid, 2 * node, queryLeft, queryRight)
                    + sumQuery(mid + 1, right, 2 * node + 1, queryLeft, queryRight);
        }
    }

    static int query(int left, int right, int node, int k ,int start){
        if (left == right){
            return left;
        }
        //왼쪽값과 k값 비교
        // k값이 왼쪽값보다 작을 경우
        int mid = (left + right) / 2;
        int sum = sumQuery(1, N, 1, start, mid);
        if (sum >= k){
            return query(left, mid, 2 * node, k, start);
        }
        // k값이 왼쪽값보다 큰 경우
        else{
            return query(mid + 1, right, 2 * node + 1, k - sum, mid + 1);
        }
    }

    static void update(int left, int right, int node, int target, int diff){
        //
        if (left > target || right < target){
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
