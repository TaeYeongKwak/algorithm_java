package bj14499;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, Y, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice(map, N, M, X, Y);
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++){
            int direction = Integer.parseInt(st.nextToken());
            if (dice.move(direction)){
                bw.write(dice.getTopValue() + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Dice{
    int[] diceNum;
    int topIdx;
    int[][] map;
    int N, M, X, Y;
    int[] diceInfo;

    int[] dx = {0, 1, -1, 0, 0};
    int[] dy = {0, 0, 0, -1, 1};
    int[] opposite = {0, 2, 1, 4, 3};

    public Dice(int[][] map, int N, int M, int X, int Y){
        diceNum = new int[7];
        topIdx = 1;
        this.map = map;
        this.N = N;
        this.M = M;
        this.X = X;
        this.Y = Y;
        diceInfo = new int[]{6, 3, 4, 5, 2};
    }

    public int getTopValue(){
        return diceNum[topIdx];
    }

    public boolean move(int direction){
        int nx = X + dx[direction];
        int ny = Y + dy[direction];

        if (0 <= nx && nx < M && 0 <= ny && ny < N){
            X = nx; Y = ny;
            int temp = topIdx;
            int bottomIdx = diceInfo[direction];
            topIdx = 7 - bottomIdx;
            diceInfo[direction] = temp;
            diceInfo[opposite[direction]] = 7 - temp;
            diceInfo[0] = bottomIdx;

            if (map[ny][nx] == 0){
                map[ny][nx] = diceNum[bottomIdx];
            }
            else if (map[ny][nx] != 0){
                diceNum[bottomIdx] = map[ny][nx];
                map[ny][nx] = 0;
            }

            return true;
        }

        return false;
    }
}
