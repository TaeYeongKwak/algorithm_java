package bj15663;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] nums;
    static boolean[] selected;
    static int[] selectNum;
    static Set<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        selected = new boolean[N];
        selectNum = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            nums[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        answer = new LinkedHashSet<>();
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
            for(int n : selectNum){
                sb.append(n + " ");
            }
            answer.add(sb.toString());
            return;
        }

        for(int n = 0; n < N; n++){
            if (selected[n]) continue;
            selected[n] = true;
            selectNum[r] = nums[n];
            dfs(r + 1);
            selected[n] = false;
        }
    }
}
