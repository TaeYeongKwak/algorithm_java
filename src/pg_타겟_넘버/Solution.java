package pg_타겟_넘버;

public class Solution {
    static int result;
    static int[] numbers;
    static int target;

    public int solution(int[] numbers, int target) {
        int answer = 0;
        this.numbers = numbers;
        this.target = target;
        result = 0;
        dfs(0, 0);
        answer = result;
        return answer;
    }

    static void dfs(int r, int n){
        if(r == numbers.length){
            if(n == target){
                result++;
            }
            return;
        }

        dfs(r + 1, n + numbers[r]);
        dfs(r + 1, n - numbers[r]);
    }
}
