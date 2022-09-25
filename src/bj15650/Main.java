package bj15650;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static BufferedWriter bw;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new boolean[N + 1];
        dfs(0, 0);
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int r, int selectCnt) throws IOException {
        if (selectCnt == M){
            StringBuilder sb = new StringBuilder("");
            for (int i = 1; i < N + 1; i++){
                if (selected[i]){
                    sb.append(i + " ");
                }
            }
            bw.write(sb.toString() + "\n");
            return;
        }

        for (int i = r + 1; i < N + 1; i++){
            selected[i] = true;
            dfs(i, selectCnt + 1);
            selected[i] = false;
        }
    }
}
