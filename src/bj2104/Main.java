package bj2104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] num;
    static long[] sumTree;
    static int[] minIdxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        num = new int[N + 1];
        sumTree = new long[4 * N];
        minIdxTree = new int[4 * N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        sumTreeInit(1, N, 1);
        minIdxTreeInit(1, N, 1);

        System.out.println(maxScore(1, N));
    }

    static long sumTreeInit(int left, int right, int node){
        if (left == right) {
            return sumTree[node] = num[left];
        }
        int mid = (left + right) / 2;
        return sumTree[node] = sumTreeInit(left, mid, 2 * node) + sumTreeInit(mid + 1, right, 2 * node + 1);
    }

    static int minIdxTreeInit(int left, int right, int node){
        if (left == right) {
            return minIdxTree[node] = left;
        }
        int mid = (left + right) / 2;
        int leftIdx = minIdxTreeInit(left, mid, 2 * node);
        int rightIdx = minIdxTreeInit(mid + 1, right, 2 * node + 1);
        return minIdxTree[node] = (num[leftIdx] < num[rightIdx])? leftIdx : rightIdx;
    }

    static long maxSumQuery(int left, int right, int node, int queryLeft, int queryRight){
        if (left > queryRight || right < queryLeft){
            return 0;
        }
        else if (queryLeft <= left && right <= queryRight){
            return sumTree[node];
        }
        else{
            int mid = (left + right) / 2;
            return maxSumQuery(left, mid, 2 * node, queryLeft, queryRight) + maxSumQuery(mid + 1, right, 2 * node + 1, queryLeft, queryRight);
        }
    }

    static int minIdxQuery(int left, int right, int node, int queryLeft, int queryRight){
        if (left > queryRight || right < queryLeft){
            return -1;
        }
        else if (queryLeft <= left && right <= queryRight){
            return minIdxTree[node];
        }
        else{
            int mid = (left + right) / 2;
            int leftIdx = minIdxQuery(left, mid, 2 * node, queryLeft, queryRight);
            int rightIdx = minIdxQuery(mid + 1, right, 2 * node + 1, queryLeft, queryRight);

            if (leftIdx == -1){
                return rightIdx;
            }
            else if (rightIdx == -1){
                return leftIdx;
            }
            else{
                return (num[leftIdx] < num[rightIdx])? leftIdx : rightIdx;
            }
        }
    }

    static long maxScore(int left, int right){
        int minIdx = minIdxQuery(1, N, 1, left, right);
        long maxScore = maxSumQuery(1, N, 1, left, right) * (long)num[minIdx];

        if (left <= minIdx - 1){
            maxScore = Math.max(maxScore, maxScore(left, minIdx - 1));
        }

        if (minIdx + 1 <= right){
            maxScore = Math.max(maxScore, maxScore(minIdx + 1, right));
        }

        return maxScore;
    }
}
