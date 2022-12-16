package pg_수식_최대화;

import java.util.ArrayList;

public class Solution {
    static boolean[] selected = new boolean[3];
    static char[] oper = {'+', '-', '*'};
    static long result = 0;
    static ArrayList<Long> nums = new ArrayList<>();
    static ArrayList<Character> ops = new ArrayList<>();

    public long solution(String expression) {
        long answer = 0;
        StringBuilder num = new StringBuilder();
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) >= '0' && expression.charAt(i) <= '9'){
                num.append(expression.charAt(i));
            }
            else{
                nums.add(Long.parseLong(num.toString()));
                num = new StringBuilder();
                ops.add(expression.charAt(i));
            }
        }
        nums.add(Long.parseLong(num.toString()));
        dfs(0, new char[3]);
        answer = result;
        return answer;
    }

    public static Long calc(Long num1, Long num2, char op){
        long num = 0;
        switch (op){
            case '+' : {
                return num1 + num2;
            }
            case '-' : {
                return num1 - num2;
            }
            case '*' : {
                return num1 * num2;
            }
        }
        return num;
    }

    static void dfs(int r, char[] order){
        if(r == 3){
            ArrayList<Long> cNums = new ArrayList<>(nums);
            ArrayList<Character> cOps = new ArrayList<>(ops);

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < cOps.size(); j++){
                    if(order[i] == cOps.get(j)){
                        Long num = calc(cNums.remove(j), cNums.remove(j), order[i]);
                        cNums.add(j, num);
                        cOps.remove(j);
                        j--;
                    }
                }
            }
            result = Math.max(result, Math.abs(cNums.get(0)));
            return;
        }

        for(int i = 0; i < 3; i++){
            if(!selected[i]){
                selected[i] = true;
                order[r] = oper[i];
                dfs(r + 1, order);
                selected[i] = false;
            }
        }
    }
}
