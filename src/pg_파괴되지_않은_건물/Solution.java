package pg_파괴되지_않은_건물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] skill = new int[K][6];
        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            skill[i][0] = Integer.parseInt(st.nextToken());
            skill[i][1] = Integer.parseInt(st.nextToken());
            skill[i][2] = Integer.parseInt(st.nextToken());
            skill[i][3] = Integer.parseInt(st.nextToken());
            skill[i][4] = Integer.parseInt(st.nextToken());
            skill[i][5] = Integer.parseInt(st.nextToken());
        }

        Solution s = new Solution();
        int result = s.solution(board, skill);
        System.out.println(result);
    }

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sum = new int[board.length + 1][board[0].length + 1];
        for (int q = 0; q < skill.length; q++){
            int type = skill[q][0];
            int r1 = skill[q][1];
            int c1 = skill[q][2];
            int r2 = skill[q][3];
            int c2 = skill[q][4];
            int degree = skill[q][5];

            sum[r1][c1] += (type == 1)? -degree : degree;
            sum[r1][c2 + 1] += (type == 1)? degree : -degree;
            sum[r2 + 1][c1] += (type == 1)? degree : -degree;
            sum[r2 + 1][c2 + 1] += (type == 1)? -degree : degree;
        }

        cumSum(sum);

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] + sum[i][j] > 0){
                    answer++;
                }
            }
        }

        return answer;
    }

    static void cumSum(int[][] sum){
        for (int i = 1; i < sum.length; i++){
            for (int j = 0; j < sum[0].length; j++){
                sum[i][j] += sum[i - 1][j];
            }
        }

        for (int i = 0; i < sum.length; i++){
            for (int j = 1; j < sum[0].length; j++){
                sum[i][j] += sum[i][j - 1];
            }
        }
    }
}
