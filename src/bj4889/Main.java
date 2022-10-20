package bj4889;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Character> stack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();
        int index = 0;
        while(true){
            char[] cArray = br.readLine().toCharArray();
            if (cArray.length > 0 && cArray[0] == '-'){
                break;
            }

            index++;
            stack.clear();
            rightStack.clear();

            for (int i = 0; i < cArray.length; i++){
                if (cArray[i] == '{'){
                    stack.push('{');
                }
                else if (cArray[i] == '}'){
                    if (!stack.isEmpty() && stack.peek() == '{'){
                        stack.pop();
                        continue;
                    }
                    stack.push('}');
                }
            }

            int cnt = 0;
            while(!stack.isEmpty()){
                char c = stack.pop();
                if (c == '}'){
                    if (rightStack.isEmpty()){
                        rightStack.push('}');
                    }
                    else if (rightStack.peek() == '}'){
                        cnt++;
                        rightStack.pop();
                    }
                }
                else if (c == '{'){
                    if (rightStack.isEmpty()){
                        cnt++;
                        rightStack.push('}');
                    }
                    else if (rightStack.peek() == '}'){
                        rightStack.pop();
                    }
                }
            }

            bw.write(index + ". " + cnt);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
