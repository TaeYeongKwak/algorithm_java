package bj1735;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int[][] num = new int[2][2];
        for (int i = 0; i < 2; i++){
            st = new StringTokenizer(br.readLine());
            num[i][0] = Integer.parseInt(st.nextToken());
            num[i][1] = Integer.parseInt(st.nextToken());
        }

        int denominator = num[0][1] * num[1][1];
        int numerator = num[0][0] * num[1][1] + num[1][0] * num[0][1];

        int gcdNum = gcd(numerator, denominator);
        System.out.println((numerator / gcdNum) + " " + (denominator / gcdNum));
        br.close();
    }

    static int gcd(int a, int b){
        return (b == 0)? a : gcd(b, a % b);
    }
}
