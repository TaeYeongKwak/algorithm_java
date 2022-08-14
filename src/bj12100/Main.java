package bj12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(map, 0);

        System.out.println(result);
    }

    static void dfs(int[][] map, int r){
//        System.out.println("------------------------------");
//        System.out.println("r: " + r);
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("------------------------------");
        if (r == 5){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > result){
                        result = map[i][j];
                    }
                }
            }
            return;
        }

        dfs(left(copyMap(map)), r + 1);
        dfs(right(copyMap(map)), r + 1);
        dfs(top(copyMap(map)), r + 1);
        dfs(bottom(copyMap(map)), r + 1);
    }

    static int[][] left(int[][] map){
        for (int i = 0; i < N; i++){
            int index = 0;
            int block = 0;

            for (int j = 0; j < N; j++){
                if (map[i][j] == 0) continue;
                if(block == map[i][j]) {
                    map[i][index - 1] = block * 2;
                    block = 0;
                    map[i][j] = 0;
                }
                else {
                    block = map[i][j];
                    map[i][j] = 0;
                    map[i][index] = block;
                    index++;
                }
            }
        }

        return map;
    }

    static int[][] right(int[][] map){
        for (int i = 0; i < N; i++){
            int index = N - 1;
            int block = 0;
            for (int j = N - 1; j >= 0; j--){
                if (map[i][j] == 0) continue;
                if(block == map[i][j]) {
                    map[i][index + 1] = block * 2;
                    block = 0;
                    map[i][j] = 0;
                }
                else {
                    block = map[i][j];
                    map[i][j] = 0;
                    map[i][index] = block;
                    index--;
                }
            }
        }

        return map;
    }

    static int[][] top(int[][] map){
        for (int i = 0; i < N; i++){
            int index = 0;
            int block = 0;
            for (int j = 0; j < N; j++){
                if (map[j][i] == 0) continue;
                if(block == map[j][i]) {
                    map[index - 1][i] = block * 2;
                    block = 0;
                    map[j][i] = 0;
                }
                else {
                    block = map[j][i];
                    map[j][i] = 0;
                    map[index][i] = block;
                    index++;
                }
            }
        }

        return map;
    }

    static int[][] bottom(int[][] map){
        for (int i = 0; i < N; i++){
            int index = N - 1;
            int block = 0;
            for (int j = N - 1; j >= 0; j--){
                if (map[j][i] == 0) continue;
                if(block == map[j][i]) {
                    map[index + 1][i] = block * 2;
                    block = 0;
                    map[j][i] = 0;
                }
                else {
                    block = map[j][i];
                    map[j][i] = 0;
                    map[index][i] = block;
                    index--;
                }
            }
        }

        return map;
    }

    static int[][] copyMap(int[][] map){
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }
}
