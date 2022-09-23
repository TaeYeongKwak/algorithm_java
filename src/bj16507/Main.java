package bj16507;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int R, C, Q;
    static int[][] cumSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        cumSum = new int[R + 1][C + 1];
        for (int i = 1; i < R + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < C + 1; j++){
                cumSum[i][j] = cumSum[i - 1][j] + cumSum[i][j - 1] + Integer.parseInt(st.nextToken()) - cumSum[i - 1][j - 1];
            }
        }

        for (int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int result = cumSum[r2][c2] - (cumSum[r1 - 1][c2] + cumSum[r2][c1 - 1]) + cumSum[r1 - 1][c1 - 1];
            result /= (r2 - r1 + 1) * (c2 - c1 + 1);
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
