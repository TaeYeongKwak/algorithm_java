package bj12919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S, T;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        result = 0;

        check(T);
        System.out.println(result);
    }

    static void check(String t){
        if (S.length() == t.length()){
            if (S.equals(t)){
                result = 1;
                System.out.println(result);
                System.exit(0);
            }
            return;
        }

        // 1번 방법
        StringBuilder sb = new StringBuilder(t);
        int lastIdx = t.length() - 1;
        if (t.charAt(lastIdx) == 'A'){
            sb.deleteCharAt(lastIdx);
            check(sb.toString());
            sb.append('A');
        }
        // 2번 방법
        if (t.charAt(0) == 'B'){
            sb.deleteCharAt(0);
            check(sb.reverse().toString());
        }
    }
}
