package bj14476;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N + 2];
        int[][] array = new int[N + 2][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i++) {
            array[i][0] = gcd(num[i], array[i - 1][0]);
        }

        for(int i = N; i > 0; i--) {
            array[i][1] = gcd(num[i], array[i + 1][1]);
        }

        int answer = -1;
        int max = -1;
        for(int i = 1; i <= N; i++) {
            int result = gcd(array[i - 1][0], array[i + 1][1]);
            if(result > max) {
                if(num[i] % result !=0) {
                    max = result;
                    answer = num[i];
                }
            }
        }

        if(answer == -1) {
            System.out.println(-1);
        }else {
            System.out.println(max + " " + answer);
        }

    }

    static int gcd(int a, int b){
        if (b == 0){
            return a;
        }else{
            return gcd(b, a % b);
        }
    }
}
