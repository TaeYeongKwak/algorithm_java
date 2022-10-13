package bj14490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numStr = br.readLine().split(":");
        int a = Integer.parseInt(numStr[0]);
        int b = Integer.parseInt(numStr[1]);
        int gcd = gcd(a, b);
        System.out.println((a / gcd) + ":" + (b / gcd));
    }

    static int gcd(int a, int b){
        return (b == 0)? a : gcd(b, a % b);
    }
}
