package pg_소수_만들기;

public class Solution {

    static int result;

    public int solution(int[] nums) {
        int answer = -1;
        dfs(0, -1, 0, nums);
        answer = result;
        return answer;
    }

    static void dfs(int r, int last, int sum, int[] nums){
        if(r == 3){
            for(int i = 2; i <= Math.sqrt(sum); i++){
                if(sum % i == 0){
                    return;
                }
            }

            result++;
            return;
        }

        for(int i = last + 1; i < nums.length; i++){
            dfs(r + 1, i, sum + nums[i], nums);
        }
    }

}
