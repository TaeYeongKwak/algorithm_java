package bj11660;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++){
                int x = Integer.parseInt(st.nextToken());
                num[i][j] = num[i][j - 1] + x;
            }
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            bw.write(solution(x1, y1, x2, y2) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int x1, int y1, int x2, int y2){
        int result = 0;

        for (int i = x1; i <= x2; i++){
            result += num[i][y2] - num[i][y1 - 1];
        }

        return result;
    }
}
