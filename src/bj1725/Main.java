package bj1725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] histogram;
    static int[] area;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        histogram = new int[N + 1];
        area = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            histogram[i] = Integer.parseInt(br.readLine());
        }


    }

//    static void query(int left, int right, int node, int target, int diff){
//        if (left > target || target > right){
//            return;
//        }
//        tree[node] += diff;
//        if (left != right){
//            int mid = (left + right) / 2;
//        }
//    }
}
