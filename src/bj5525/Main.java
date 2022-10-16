package bj5525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, M;
    static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine();
        char[] cs = S.toCharArray();
        int result = 0;
        int count = 0;

        for (int i = 1; i < M - 1; i++){
            if(cs[i - 1] == 'I' && cs[i] == 'O' && cs[i + 1] == 'I') {
                count++;

                if(count == N) {
                    count--;
                    result++;
                }
                i++;
            }
            else {
                count = 0;
            }
        }

        System.out.println(result);
    }

}
