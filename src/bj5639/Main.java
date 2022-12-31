package bj5639;

import java.io.*;

public class Main {

    static int[] tree;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tree = new int[10001];
        int index = 0;
        while(true){
            String input = br.readLine();
            if (input == null || input.equals("")){
                break;
            }
            tree[index++] = Integer.parseInt(input);
        }

        answer = new StringBuilder();

        postOrder(0, index - 1);
        System.out.println(answer);
    }

    static void postOrder(int i, int end){
        if (i > end){
            return;
        }

        int mid = i + 1;
        while(mid <= end && tree[mid] < tree[i]){
            mid++;
        }

        postOrder(i + 1, mid - 1);
        postOrder(mid, end);
        answer.append(tree[i] + "\n");
    }
}
