package bj12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean result;
    static String S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        result = false;

        solution(T);
        System.out.println(result? 1 : 0);
    }

    static void solution(String t){
        StringBuilder sb = new StringBuilder(t);
        if (result || sb.length() < S.length()){
            return;
        }

        if (sb.toString().equals(S)){
            result = true;
            return;
        }

        if (sb.charAt(sb.length() - 1) == 'A'){
            sb.deleteCharAt(sb.length() - 1);
            solution(sb.toString());
            sb.append('A');
        }

        if (sb.charAt(sb.length() - 1) == 'B'){
            sb.deleteCharAt(sb.length() - 1);
            sb.reverse();
            solution(sb.toString());
        }
    }
}
