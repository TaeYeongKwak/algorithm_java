package pg_괄호_회전하기;

import java.util.Stack;

public class Solution {
    public int solution(String s) {
        int answer = -1;
        StringBuilder sb = new StringBuilder(s);
        Stack<Character> stack = new Stack<>();

        answer = 0;
        for(int i = 0; i < s.length(); i++){
            char insert = sb.charAt(sb.length() - 1);
            sb.insert(0, insert);
            sb.deleteCharAt(s.length());
            stack.clear();
            for(int j = 0; j < s.length(); j++){
                if(!stack.isEmpty() && stack.peek() == '(' && sb.charAt(j) == ')'){
                    stack.pop();
                }
                else if(!stack.isEmpty() && stack.peek() == '{' && sb.charAt(j) == '}'){
                    stack.pop();
                }
                else if(!stack.isEmpty() && stack.peek() == '[' && sb.charAt(j) == ']'){
                    stack.pop();
                }
                else{
                    stack.push(sb.charAt(j));
                }
            }
            if(stack.size() == 0) answer++;
        }
        return answer;
    }
}
