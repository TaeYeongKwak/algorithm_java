package bj1213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] c = br.readLine().toCharArray();

        int[] alpha = new int[26];

        for (int i = 0; i < c.length; i++){
            alpha[(c[i] - 'A')]++;
        }

        int odd = 0;
        for (int i = 0; i < 26; i++){
            if (alpha[i] % 2 != 0){
                odd++;
            }
        }

        StringBuilder front = new StringBuilder();
        StringBuilder back = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        if (odd > 1 || (odd == 1 && c.length % 2 == 0)){
            System.out.println("I'm Sorry Hansoo");
        }
        else{
            for (int i = 0; i < 26; i++){
                int size = alpha[i] / 2;
                char ch = (char)(i + 'A');
                for (int j = 0; j < size; j++){
                    front.append(ch);
                    back.append(ch);
                }

                alpha[i] -= size * 2;
                if (alpha[i] != 0){
                    mid.append(ch);
                }
            }
        }

        front.append(mid);
        front.append(back.reverse());
        System.out.println(front);
    }
}
