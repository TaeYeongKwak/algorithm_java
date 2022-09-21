package pg_양궁대회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] info = new int[11];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 11; i++){
            info[i] = Integer.parseInt(st.nextToken());
        }

        Solution s = new Solution();
        int[] answer = s.solution(N, info);
        for (int a : answer){
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public int[] solution(int n, int[] info) {
        int[] answer = {};
        diff = 0;
        result = new int[]{-1};
        int[] ryan = new int[11];
        dfs(info, ryan, n, 0);
        answer = result;
        return answer;
    }

    static int diff;
    static int[] result;

    static void dfs(int[] apeach, int[] ryan, int n, int r){
        int[] copyRyan = copyArray(ryan);
        if (r == 10 && n > 0){
            copyRyan[r] = n;
            n = 0;
        }

        if (n == 0){
            int ryanPoint = 0;
            int apeachPoint = 0;
            for (int i = 0; i < 11; i++){
                if (apeach[i] == 0 && copyRyan[i] == 0) continue;

                if (apeach[i] < copyRyan[i]){
                    ryanPoint += 10 - i;
                }
                else{
                    apeachPoint += 10 - i;
                }
            }

            if (ryanPoint - apeachPoint > diff){
                diff = ryanPoint - apeachPoint;
                result = copyRyan;
            }
            else if((ryanPoint - apeachPoint) == diff && diff > 0){
                for (int i = 10; i >= 0; i--){
                    if (copyRyan[i] == result[i]) continue;
                    else if (copyRyan[i] > result[i]){
                        result = copyRyan;
                        break;
                    }
                    else{
                        break;
                    }
                }
            }
            return;
        }

        dfs(apeach, copyRyan, n, r + 1);
        if (n > apeach[r]){
            copyRyan[r] = apeach[r] + 1;
            n -= copyRyan[r];
            dfs(apeach, copyRyan, n, r + 1);
        }
    }

    static int[] copyArray(int[] array){
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++){
            result[i] = array[i];
        }
        return result;
    }
}
