package bj15655;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] nums;
    static boolean[] select;
    static List<String> answers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        select = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            nums[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        answers = new ArrayList<>();
        dfs(0, 0);

        for (String answer : answers){
            bw.write(answer);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static void dfs(int i, int r){
        if (r == M){
            StringBuilder sb = new StringBuilder();
            for(int n = 0; n < N; n++){
                if (select[n]){
                    sb.append(nums[n] + " ");
                }
            }
            answers.add(sb.toString());
            return;
        }

        for(int j = i; j < N; j++){
            if (select[j]) continue;
            select[j] = true;
            dfs(j + 1, r + 1);
            select[j] = false;
        }
    }
}
