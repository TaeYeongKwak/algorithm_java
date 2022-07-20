package bj2243;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long[] box;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        box = new long[4 * 1000001];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (a == 1){
                int candyNum = takeCandy(1, 1000001,1, b);
                insertCandy(1, 1000001 , 1, candyNum, -1);
                bw.write(candyNum + "\n");
            }else if (a == 2){
                int c = Integer.parseInt(st.nextToken());
                insertCandy(1, 1000001, 1, b, c);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void insertCandy(int left, int right, int node, long target, long diff){
        if (target < left || target > right){
            return;
        }
        box[node] += diff;
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        insertCandy(left, mid, node * 2, target, diff);
        insertCandy(mid + 1, right, (node * 2) + 1, target, diff);
    }

    static int takeCandy(int left, int right, int node, long rank){
        if (left == right) {
            return left;
        }

        int leftNode = node * 2;
        int mid = (left + right) / 2;

        if (box[leftNode] >= rank){
            return takeCandy(left, mid, leftNode, rank);
        } else{
            return takeCandy(mid + 1, right,leftNode + 1, rank-box[leftNode]);
        }
    }
}
