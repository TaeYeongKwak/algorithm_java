package bj1725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] histogram;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        histogram = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            histogram[i] = Integer.parseInt(br.readLine());
        }

        tree = new int[4 * N];
        init(1, N, 1);

        System.out.println(maxArea(1, N));
    }

    static int init(int left, int right, int node){
        if (left == right) return tree[node] = left;
        int mid = (left + right) / 2;
        int leftIdx = init(left, mid, 2 * node);
        int rightIdx = init(mid + 1, right, 2 * node + 1);
        return tree[node] = (histogram[leftIdx] < histogram[rightIdx])? leftIdx : rightIdx;
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if (left > queryRight || right < queryLeft){
            return -1;
        }
        else if (queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        else{
            int mid = (left + right) / 2;
            int leftIdx = query(left, mid, node * 2, queryLeft, queryRight);
            int rightIdx = query(mid + 1, right, (node * 2) + 1, queryLeft, queryRight);
            if (leftIdx == -1){
                return rightIdx;
            }
            else if (rightIdx == -1){
                return leftIdx;
            }
            else{
                return (histogram[leftIdx] < histogram[rightIdx])? leftIdx : rightIdx;
            }
        }
    }

    static int maxArea(int left, int right){
        int minIdx = query(1, N, 1, left, right);
        int maxArea = (right - left + 1) * histogram[minIdx];

        if (left <= minIdx - 1){
            int tempArea = maxArea(left, minIdx - 1);
            maxArea = Math.max(tempArea, maxArea);
        }

        if (minIdx + 1 <= right){
            int tempArea = maxArea(minIdx + 1, right);
            maxArea = Math.max(tempArea, maxArea);
        }

        return maxArea;
    }
}
