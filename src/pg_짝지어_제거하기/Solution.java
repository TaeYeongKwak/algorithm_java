package pg_짝지어_제거하기;

import java.util.Stack;

public class Solution {
    public int solution(String s){
        int answer = -1;
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(stack.isEmpty()){
                stack.push(c);
            }
            else{
                if(stack.peek() == c){
                    stack.pop();
                }
                else{
                    stack.push(c);
                }
            }
        }

        answer = (stack.isEmpty())? 1 : 0;
        return answer;
    }
}
