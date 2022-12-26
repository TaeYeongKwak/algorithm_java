package bj6064;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            bw.write(solution(M, N, x, y) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int M, int N, int x, int y){
        int answer = -1;
        int lcm = M * N / gcd(M, N);
        int n = 0;
        while(n * M < lcm){
            if((n * M + x - y) % N == 0){
                answer = n * M + x;
                break;
            }
            n++;
        }

        return answer;
    }

    static int gcd(int x, int y){
        return (y == 0)? x : gcd(y, x % y);
    }
}
