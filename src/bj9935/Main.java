package bj9935;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String boomStr = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++){
            stack.push(str.charAt(i));
            if (stack.size() >= boomStr.length()){
                boolean flag = true;
                for (int j = 0; j < boomStr.length(); j++){
                    if (boomStr.charAt(j) != stack.get(stack.size() - boomStr.length() + j)){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    for (int j = 0; j < boomStr.length(); j++){
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack){
            sb.append(c);
        }

        String result = (sb.length() == 0)? "FRULA" : sb.toString();
        System.out.println(result);
    }
}
