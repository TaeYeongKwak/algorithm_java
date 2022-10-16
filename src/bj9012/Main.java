package bj9012;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        Stack<Character> stack = new Stack<>();
        for (int t = 0; t < T; t++){
            stack.clear();
            char[] ps = br.readLine().toCharArray();
            stack.push(ps[0]);
            for (int i = 1; i < ps.length; i++){
                if (stack.isEmpty() || ps[i] == '('){
                    stack.push(ps[i]);
                }
                else if (ps[i] == ')'){
                    if (stack.peek() == '('){
                        stack.pop();
                    }
                    else{
                        stack.push(ps[i]);
                    }
                }
            }

            bw.write((stack.isEmpty()? "YES" : "NO") + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
