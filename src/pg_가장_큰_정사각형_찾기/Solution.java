package pg_가장_큰_정사각형_찾기;

public class Solution {

    public int solution(int[][] board) {
        int answer = 1234;
        int h = board.length;
        int w = board[0].length;
        int[][] dp = new int[h + 1][w + 1];
        for(int i = 1; i < h + 1; i++){
            for(int j = 1; j < w + 1; j++){
                dp[i][j] = board[i - 1][j - 1];
            }
        }

        int max = 0;
        for(int i = 1; i < h + 1; i++){
            for(int j = 1; j < w + 1; j++){
                if(board[i - 1][j - 1] == 1){
                    int left = dp[i][j - 1];
                    int up = dp[i - 1][j];
                    int leftup = dp[i - 1][j - 1];
                    int min = Math.min(left, Math.min(up, leftup));
                    dp[i][j] = min + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        answer = max * max;
        return answer;
    }
}
