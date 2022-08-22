package bj14888;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] num;
    static int[] operator;
    static int max, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        operator = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        dfs(1, num[0]);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int r, int result){
        if (r == N){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++){
            if (operator[i] == 0) continue;
            int tempResult = result;
            switch(i){
                case 0:
                    tempResult += num[r];
                    break;
                case 1:
                    tempResult -= num[r];
                    break;
                case 2:
                    tempResult *= num[r];
                    break;
                case 3:
                    tempResult /= num[r];
                    break;
            }

            operator[i]--;
            dfs(r + 1, tempResult);
            operator[i]++;
        }
    }
}
