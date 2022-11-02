package pg_올바른_괄호;

import java.util.Stack;

public class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        char[] sArray = s.toCharArray();
        for(char c : sArray){
            if(c == '('){
                stack.push(c);
            }
            else if(c == ')'){
                if(stack.isEmpty() || stack.peek() == ')'){
                    stack.push(c);
                }
                else if(stack.peek() == '('){
                    stack.pop();
                }
            }
        }

        answer = stack.isEmpty()? true : false;
        return answer;
    }
}
