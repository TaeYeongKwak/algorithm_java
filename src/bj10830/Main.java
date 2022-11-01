package bj10830;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] A;
    static long B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = pow(A, B);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                bw.write(result[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int[][] pow(int[][] matrix, long b){
        if (b == 1L){
            return matrix;
        }

        int[][] m = pow(matrix, b / 2);

        m = multiple(m, m);

        if (b % 2 != 0){
            m = multiple(m, A);
        }

        return m;
    }

    static int[][] multiple(int[][] a, int[][] b){
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                for (int k = 0; k < N; k++){
                    result[i][j] += a[i][k] * b[k][j];
                    result[i][j] %= 1000;
                }
            }
        }
        return result;
    }
}
