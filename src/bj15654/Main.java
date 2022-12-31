package bj15654;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] nums;
    static int[] numOrder;
    static boolean[] selected;

    static ArrayList<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        selected = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        numOrder = new int[M];
        answer = new ArrayList<>();

        dfs(0);

        for(String s : answer){
            bw.write(s);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static void dfs(int r){
        if (r == M){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < M; i++){
                sb.append(numOrder[i] + " ");
            }
            answer.add(sb.toString());
            return;
        }

        for(int i = 0; i < N; i++){
            if (selected[i]) continue;
            selected[i] = true;
            numOrder[r] = nums[i];
            dfs(r + 1);
            selected[i] = false;
        }
    }
}
