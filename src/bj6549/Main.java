package bj6549;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] histogram;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        while (true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            if (N == 0) break;

            histogram = new int[N + 1];
            for (int i = 1; i < N + 1; i++){
                histogram[i] = Integer.parseInt(st.nextToken());
            }

            tree = new int[4 * N];
            init(1, N, 1);

            bw.write(maxArea(1, N) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static int init(int left, int right, int node){
        if (left == right) return tree[node] = left;
        int mid = (left + right) / 2;
        int leftIdx = init(left, mid, 2 * node);
        int rightIdx = init(mid + 1, right, 2 * node + 1);
        return tree[node] = (histogram[leftIdx] < histogram[rightIdx])? leftIdx : rightIdx;
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if (queryLeft > right || queryRight < left){
            return -1;
        }
        else if (queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        else{
            int mid = (left + right) / 2;
            int leftIdx = query(left, mid, 2 * node, queryLeft, queryRight);
            int rightIdx = query(mid + 1, right, 2 * node + 1, queryLeft, queryRight);
            if (leftIdx == -1) return rightIdx;
            else if (rightIdx == -1) return leftIdx;
            else return (histogram[leftIdx] < histogram[rightIdx])? leftIdx : rightIdx;
        }
    }

    static long maxArea(int left, int right){
        int minIdx = query(1, N, 1, left, right);
        long maxArea = (long)(right - left + 1) * (long)histogram[minIdx];

        if (left <= minIdx - 1){
            maxArea = Math.max(maxArea, maxArea(left, minIdx - 1));
        }

        if (minIdx + 1 <= right){
            maxArea = Math.max(maxArea, maxArea(minIdx + 1, right));
        }

        return maxArea;
    }
}
