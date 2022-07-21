package bj2824;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigInteger A = BigInteger.ONE;
        for (int i = 0; i < N; i++){
            A = A.multiply(new BigInteger(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        BigInteger B = BigInteger.ONE;
        for (int i = 0; i < M; i++){
            B = B.multiply(new BigInteger(st.nextToken()));
        }

        String result = A.gcd(B).toString();
        if (result.length() > 9){
            result = result.substring(result.length() - 9);
        }
        System.out.println(result);


    }

//    static BigInteger gcd(BigInteger a, BigInteger b){
//        if (b.equals(0)){
//            return a;
//        }else{
//            return gcd(b, a.remainder(b));
//        }
//    }
}
