package bj4949;

import java.io.*;
import java.util.Stack;

public class Main {

    static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        stack = new Stack<>();
        while(true){
            String str = br.readLine();
            if (str.equals(".")){
                break;
            }

            bw.write(solution(str) + '\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static String solution(String str){
        stack.clear();

        char[] c = str.toCharArray();

        for (int i = 0; i < c.length; i++){
            if (c[i] == '(' || c[i] == '['){
                stack.push(c[i]);
            }
            else if (c[i] == ')'){
                if (stack.isEmpty() || stack.peek() != '('){
                    stack.push(c[i]);
                    break;
                }
                stack.pop();
            }
            else if (c[i] == ']'){
                if (stack.isEmpty() || stack.peek() != '['){
                    stack.push(c[i]);
                    break;
                }
                stack.pop();
            }
        }

        return stack.isEmpty()? "yes" : "no";
    }
}
