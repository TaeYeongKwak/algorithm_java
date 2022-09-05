package bj11441;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int n = 1; n < N + 1; n++){
            sum += Integer.parseInt(st.nextToken());
            num[n] = sum;
        }

        int M = Integer.parseInt(br.readLine());
        for (int m = 1; m < M + 1; m++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int result = num[j] - num[i - 1];
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
