package pg_삼각_달팽이;

public class Solution {
    public int[] solution(int n) {
        int[] answer = {};

        int[][] snail = new int[n][n];
        int value = 1;
        int x = 0, y = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) {
                    y++;
                }
                else if (i % 3 == 1) {
                    x++;
                }
                else if (i % 3 == 2) {
                    x--;
                    y--;
                }
                snail[y][x] = value++;
            }
        }

        int size = (n * (n + 1)) / 2;
        answer = new int[size];

        int index = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(snail[i][j] == 0) break;
                answer[index++] = snail[i][j];
            }
        }

        return answer;
    }
}
