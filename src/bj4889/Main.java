package bj4889;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Character> stack = new Stack<>();
        int index = 0;
        while(true){
            char[] cArray = br.readLine().toCharArray();
            if (cArray.length > 0 && cArray[0] == '-'){
                break;
            }

            index++;
            stack.clear();

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
            char last;
            while(!stack.isEmpty()){
                last = stack.pop();

            }

            bw.write(index + ". " + cnt);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
