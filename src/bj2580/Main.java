package bj2580;

import java.io.*;
import java.util.ArrayList;

public class Main {

    static BufferedWriter bw;
    static int[][] map;
    static ArrayList<int[]> zeroList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new int[9][9];
        zeroList = new ArrayList<>();

        for (int i = 0; i < 9; i++){
            String[] mapLine = br.readLine().split(" ");
            for (int j = 0; j < 9; j++){
                map[i][j] = Integer.parseInt(mapLine[j]);
                if (map[i][j] == 0)
                    zeroList.add(new int[]{j, i});
            }
        }

        br.close();
        visited = new boolean[zeroList.size()];
        sudoku(0);
    }

    static void sudoku(int r) throws IOException {
        if (r == zeroList.size()){
            for (int i = 0; i < 9; i++){
                for (int j = 0; j < 9; j++){
                    bw.write(map[i][j] + " ");
                }
                bw.write("\n");
            }

            bw.flush();
            bw.close();
            System.exit(0);
        }
        for (int k = r; k < zeroList.size(); k++){
            int x = zeroList.get(k)[0];
            int y = zeroList.get(k)[1];

            if (visited[k]) continue;
            visited[k] = true;

            for (int i = 1; i <= 9; i++){
                if (check(x, y, i)){
                    map[y][x] = i;
                    sudoku(k + 1);
                }
            }
            map[y][x] = 0;
            visited[k] = false;
            return;
        }
    }

    static boolean check(int x, int y, int n){
        for (int i = 0; i < 9; i++){
            if (map[y][i] == n)
                return false;
            if (map[i][x] == n)
                return false;
        }

        int nx = (x / 3) * 3;
        int ny = (y / 3) * 3;
        for (int i = ny; i < ny + 3; i++){
            for (int j = nx; j < nx + 3; j++){
                if (map[i][j] == n)
                    return false;
            }
        }

        return true;
    }
}
