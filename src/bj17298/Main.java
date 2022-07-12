package bj17298;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] answer = new int[n];

        Stack<int[]> stack = new Stack<>();
        String[] numLine = br.readLine().split(" ");
        for (int i = 0; i < n; i++){
            answer[i] = -1;
            int num = Integer.parseInt(numLine[i]);
            if (i == 0) {
                stack.push(new int[]{i, num});
            }else{
                while(!stack.isEmpty() && stack.peek()[1] < num){
                    answer[stack.pop()[0]] = num;
                }
                stack.push(new int[]{i, num});
            }
        }

        for (int i = 0; i < n; i++){
            bw.write(answer[i] + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
