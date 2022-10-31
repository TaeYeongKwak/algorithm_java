package bj15651;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] select;

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        select = new int[M];
        dfs(0);

        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int r) throws IOException {
        if (r == M){
            for (int i = 0; i < M; i++){
                bw.write(select[i] + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 1; i <= N; i++){
            select[r] = i;
            dfs(r + 1);
        }
    }
}
