package bj9613;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++){
            String[] numLine = br.readLine().split(" ");
            int n = Integer.parseInt(numLine[0]);
            int[] num = new int[n];
            for (int j = 1; j < n + 1; j++){
                num[j - 1] = Integer.parseInt(numLine[j]);
            }

            long result = 0;
            for (int j = 0; j < n; j++){
                for (int k = j + 1; k < n; k++){
                    result += gcd(num[j], num[k]);
                }
            }
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int gcd(int a, int b){
        return (b == 0)? a : gcd(b, a % b);
    }
}
