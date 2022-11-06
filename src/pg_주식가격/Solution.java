package pg_주식가격;

import java.util.Stack;

public class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        int len = prices.length;
        answer = new int[len];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && prices[i] < prices[stack.peek()]){
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            int index = stack.pop();
            answer[index] = len - (index + 1);
        }

        return answer;
    }
}
