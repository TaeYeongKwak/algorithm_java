package bj12899;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] tree;
    static int S = 2000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        tree = new int[4 * S];

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (command == 1){
                update(1, S, 1, x, 1);
            }
            else if (command == 2){
                int k = query(1, S, 1, x);
                update(1, S, 1, k, -1);
                bw.write(k + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void update(int left, int right, int node, int target, int diff){
        if (target < left || target > right){
            return;
        }
        tree[node] += diff;
        if (left != right){
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }

    static int query(int left, int right, int node, int k){
        if (left == right){
            return left;
        }
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;
        int mid = (left + right) / 2;
        if (tree[leftNode] >= k){
            return query(left, mid, leftNode, k);
        }
        else{
            return query(mid + 1, right, rightNode, k - tree[leftNode]);
        }
    }

}
