package bj9465;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][N];


            for(int i = 0; i < 2; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = solution(N, sticker);
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int N, int[][] sticker){
        int answer = Math.max(sticker[0][0], sticker[1][0]);

        if (N > 1){
            sticker[0][1] = sticker[1][0] + sticker[0][1];
            sticker[1][1] = sticker[0][0] + sticker[1][1];
            answer = Math.max(sticker[0][1], sticker[1][1]);
        }

        for(int i = 2; i < N; i++){
            sticker[0][i] += Math.max(sticker[1][i - 1], Math.max(sticker[0][i - 2], sticker[1][i - 2]));
            sticker[1][i] += Math.max(sticker[0][i - 1], Math.max(sticker[0][i - 2], sticker[1][i - 2]));
            answer = Math.max(sticker[0][i], sticker[1][i]);
        }

        return answer;
    }
}
