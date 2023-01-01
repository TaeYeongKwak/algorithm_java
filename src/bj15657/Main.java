package bj15657;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] nums;
    static int[] count;
    static List<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        count = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            nums[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        answer = new ArrayList<>();
        dfs(0, 0);

        for(String s : answer){
            bw.write(s);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int r, int start){
        if(r == M){
            StringBuilder sb = new StringBuilder();
            for(int n = 0; n < N; n++){
                for (int c = 0; c < count[n]; c++){
                    sb.append(nums[n] + " ");
                }
            }
            answer.add(sb.toString());
            return;
        }

        for (int n = start; n < N; n++){
            count[n]++;
            dfs(r + 1, n);
            count[n]--;
        }
    }
}
