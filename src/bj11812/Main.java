package bj11812;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long N;
    static int K, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            long result = (K == 1)? Math.abs(a - b) : lca(a, b);

            bw.write( result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static long lca(long a, long b){
        long aDist = 0;
        long bDist = 0;
        while(a != b){
            if (a < b){
                b = getParent(b);
                bDist++;
            }
            if (b < a){
                a = getParent(a);
                aDist++;
            }
        }
        return aDist + bDist;
    }

    static long getParent(long x){
        long result = (x - 1) / K;
        return ((x - 1) % K == 0)? result : result + 1;
    }

}
