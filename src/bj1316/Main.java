package bj1316;

import java.io.*;
import java.util.Arrays;

public class Main {

    static boolean[] alphaCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int result = 0;
        alphaCheck = new boolean[26];
        for (int t = 0; t < T; t++){
            String str = br.readLine();
            result += check(str)? 1 : 0;
        }

        System.out.println(result);
    }

    static boolean check(String str){
        Arrays.fill(alphaCheck, false);
        char[] cArray = str.toCharArray();
        int lastIndex = -1;
        for (int i = 0; i < cArray.length; i++){
            int cIndex = cArray[i] - 'a';
            if (lastIndex == -1){
                lastIndex = cIndex;
                alphaCheck[cIndex] = true;
            }
            else if(lastIndex != cIndex){
                if (alphaCheck[cIndex]){
                    return false;
                }
                else{
                    lastIndex = cIndex;
                    alphaCheck[cIndex] = true;
                }
            }
        }
        return true;
    }

}
