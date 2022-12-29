package bj15652;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] selected;
    static List<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[N + 1];
        answer = new ArrayList<>();

        dfs(0, 1);

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
            for(int i = 1; i <= N; i++){
                for(int j = 0; j < selected[i]; j++){
                    sb.append(i + " ");
                }
            }
            answer.add(sb.toString());
            return;
        }

        for(int i = start; i <= N; i++){
            selected[i]++;
            dfs(r + 1, i);
            selected[i]--;
        }
    }
}
