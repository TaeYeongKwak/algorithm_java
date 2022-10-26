package bj10610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numList = br.readLine().split("");
        int[] cnt = new int[10];
        int total = 0;
        for (String numStr : numList){
            int num = Integer.parseInt(numStr);
            cnt[num]++;
            total += num;
        }

        if (total % 3 != 0 || cnt[0] == 0){
            System.out.println(-1);
        }
        else{
            StringBuilder sb = new StringBuilder();
            for (int i = 9; i >= 0; i--){
                while(cnt[i] > 0){
                    sb.append(i);
                    cnt[i]--;
                }
            }
            System.out.println(sb);
        }
    }
}
