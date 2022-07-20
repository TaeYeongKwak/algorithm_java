package bj2042;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N,M,K;
    static long[] tree;
    static int S;
    static long[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        num = new long[N + 1];
        for (int i = 1; i < N + 1; i++){
            num[i] = Long.parseLong(br.readLine());
        }

        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2];

        init(1, 1, N);

        for (int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (command == 1){
                long diff = c - num[b];
                num[b] = c;
                update(1, N, 1, b, diff);
            }else if (command == 2){
                long sum = query(1, N, 1, b, (int) c);
                bw.write(sum + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static long init(int node, int left, int right) {
        if(left==right) return tree[node] = num[left]; // 리프노드

        int mid=(left+right)/2;
        // (node)번째 노드 합=왼쪽 자식(2*node) 합+오른쪽 자식(2*node+1) 합
        return tree[node] = init(2*node, left, mid) + init(2*node+1, mid+1, right);
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){
        //1. 연관이 없을 경우 -> return 0
        if (queryRight < left || queryLeft > right){
            return 0;
        }
        //2. 해당 노드의 합 범위가 원하는 범위 내에 포함되어 있는 경우 return node value;
        else if (queryLeft <= left && queryRight >= right){
            return tree[node];
        }
        //3. 해당 노드의 합 범위가 원하는 범위에 겹쳐 있을 경우 return left query + right query
        else{
            int mid = (left + right) / 2;
            return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
        }

    }

    static void update(int left, int right, int node, long target, long diff){
        //연관없음
        if (target < left || right < target) {
            return;
        }
        //연관 있음 -> 현재 노드에 diff 반영 -> 자식에게 diff전달
        else {
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, (node * 2) + 1, target, diff);
            }
        }
    }
}

