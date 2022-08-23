package bj14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static int[][] map;
    static int[][] rowRoad;
    static int[][] columnRoad;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init();
        int row = checkRoad(rowRoad);
        int column = checkRoad(columnRoad);
        System.out.println(row + column);
    }

    static void init(){
        rowRoad = new int[N][N];
        columnRoad = new int[N][N];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (j == 0){
                    rowRoad[i][j] = 0;
                }
                else{
                    rowRoad[i][j] = map[i][j] - map[i][j - 1];
                }
            }
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (j == 0){
                    columnRoad[i][j] = 0;
                }
                else{
                    columnRoad[i][j] = map[j][i] - map[j - 1][i];
                }
            }
        }
    }

    static int checkRoad(int[][] road){
        int count = 0;
        for (int i = 0; i < N; i++) {
            int flat = 0;
            boolean check = true;
            boolean downhillCheck = false;
            for (int j = 0; j < N; j++) {
                // 평지
                if (road[i][j] == 0){
                    flat++;
                }
                // 오르막길
                else if (road[i][j] == 1){
                    if (downhillCheck){
                        downhillCheck = false;
                        flat -= L;
                    }
                    if (flat < L){
                        check = false;
                        break;
                    }
                    flat = 1;
                }
                // 내리막길
                else if (road[i][j] == -1){
                    if (downhillCheck && flat < L){
                        check = false;
                        break;
                    }
                    check = false;
                    downhillCheck = true;
                    flat = 1;
                }

                if (downhillCheck && flat >= L){
                    check = true;
                    downhillCheck = false;
                    flat = 0;
                }

                if (road[i][j] > 1 || road[i][j] < -1){
                    check = false;
                    break;
                }
            }
            if (check){
                count++;
            }
        }

        return count;
    }
}
