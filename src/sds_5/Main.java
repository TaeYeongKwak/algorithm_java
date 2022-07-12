package sds_5;

import java.io.*;

public class Main {

    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        long[] answer = new long[t];
        for (int i = 0; i < t; i++) {
            String[] nmk = br.readLine().split(" ");
            n = Integer.parseInt(nmk[0]);
            m = Integer.parseInt(nmk[1]);
            k = Integer.parseInt(nmk[2]);

            char[][] map = new char[n + 1][m + 1];
            int[] start = new int[2];
            for(int y = 1; y < n + 1; y++){
                char[] mapLine = br.readLine().toCharArray();
                for(int x = 1; x < m + 1; x++){
                    map[y][x] = mapLine[x-1];
                    if (map[y][x] == 'S')
                        start = new int[]{x, y};
                }
            }
            answer[i] = antEscape(map, start);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < t; i++){
            bw.write("#" + (i+1) + " " + answer[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static long caseNum = 0;
    static long[][][] dp;

    static long antEscape(char[][] map, int[] start){
        caseNum = 0;
        dp = new long[n + 1][m + 1][k + 1];
        dp[start[1]][start[0]][0] = 1;

        for(int r = 1; r <= k; r++){
            for(int y = 1; y < n + 1; y++) {
                for (int x = 1; x < m + 1; x++) {
                    if(dp[y][x][r-1] > 0){
                        for(int i = 0; i < 4; i++) {
                            int nx = x + dx[i];
                            int ny = y + dy[i];

                            if(0 < nx && nx < m + 1 && 0 < ny && ny < n + 1 && map[ny][nx] != 'X'){
                                dp[ny][nx][r] += dp[y][x][r-1] % 1000000007;
                            }else if(0 >= nx || nx >= m + 1 || 0 >= ny || ny >= n + 1){
                                caseNum += dp[y][x][r-1] % 1000000007;
                            }
                        }
                    }
                }
            }
        }

        return caseNum % 1000000007;
    }

}

