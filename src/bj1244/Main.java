package bj1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] switchStatus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        switchStatus = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++){
            switchStatus[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (gender == 1) male(num);
            else if (gender == 2) female(num);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++){
            if (i > 20 && i % 20 == 1){
                sb.append("\n");
            }
            sb.append(switchStatus[i] + " ");
        }

        System.out.println(sb);
    }

    static void male(int num){
        for (int i = 1; i * num < N + 1; i++){
            switchStatus[num * i] = (switchStatus[num * i] == 0)? 1 : 0;
        }
    }

    static void female(int num){
        int left = num;
        int right = num;
        while(left > 0 && right < N + 1){
            if ((left - 1 <= 0 || right + 1 >= N + 1) || (switchStatus[left - 1] != switchStatus[right + 1])){
                break;
            }
            left--;
            right++;
        }

        for (int l = left; l <= right; l++){
            switchStatus[l] = (switchStatus[l] == 0)? 1 : 0;
        }
    }
}
