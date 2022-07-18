package bj1062;

import java.io.*;

public class Main {

    static int n, k;
    static String[] words;
    static boolean[] selectAlpha;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        k = Integer.parseInt(nk[1]);

        words = new String[n];
        result = 0;

        for (int i = 0; i < n; i++){
            words[i] = br.readLine();
        }

        int[] selectedAlphaNum = {0, 2, 8, 13, 19};
        selectAlpha = new boolean[26];
        for (int s : selectedAlphaNum){
            selectAlpha[s] = true;
        }

        learn(0, k-5);


        System.out.println(result);

    }
    static void learn(int s, int r){
        if (r == 0){
            int count = 0;
            for (int i = 0; i < n; i++){
                boolean isRead = true;
                for (int j = 0; j < words[i].length(); j++){
                    if (!selectAlpha[(words[i].charAt(j) - 97)]){
                        isRead = false;
                        break;
                    }
                }
                if (isRead) count++;
            }

            result = Math.max(count, result);
            return;
        }

        for (int i = s; i < 26; i++){
            if (!selectAlpha[i]){
                selectAlpha[i] = true;
                learn(i + 1, r - 1);
                selectAlpha[i] = false;
            }
        }
    }
}
