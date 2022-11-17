package pg_하샤드_수;

public class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        String[] sArray = Integer.toString(x).split("");
        int sum = 0;
        for(String s : sArray){
            sum += Integer.parseInt(s);
        }

        answer = (x % sum == 0);
        return answer;
    }
}
