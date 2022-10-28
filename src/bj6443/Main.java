package bj6443;

import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    static Set<String> result;
    static int[] alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        result = new TreeSet<>();
        alpha = new int[26];
        for (int i = 0; i < N; i++){
            Arrays.fill(alpha, 0);
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++){
                alpha[str.charAt(j) - 'a']++;
            }
            result.clear();
            anagram(str.length(), new StringBuilder());
            for (String anagram : result){
                bw.write(anagram + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void anagram(int r, StringBuilder sb){
        if (r == 0){
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < 26; i++){
            if (alpha[i] > 0){
                alpha[i]--;
                sb.append((char)(i + 'a'));
                anagram(r - 1, sb);
                sb.deleteCharAt(sb.length() - 1);
                alpha[i]++;
            }
        }
    }
}
