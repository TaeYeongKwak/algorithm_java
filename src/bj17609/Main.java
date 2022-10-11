package bj17609;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            bw.write(solution(br.readLine()) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(String str){
        char[] cArray = str.toCharArray();
        int left = 0;
        int right = cArray.length - 1;

        if(check(left, right, cArray)) {
            return 0;
        }
        if(checkSimilar(left, right, cArray)) {
            return 1;
        }

        return 2;
    }

    static boolean check(int left, int right, char[] cArray) {
        while(left<=right) {
            if(cArray[left] != cArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    static boolean checkSimilar(int left,int right, char[] cArray) {
        while(left<=right) {
            if(cArray[left] != cArray[right]) {
                boolean a = check(left + 1, right, cArray);
                boolean b = check(left, right - 1, cArray);
                if(!a && !b) {
                    return false;
                }else {
                    return true;
                }
            }
            left++;
            right--;
        }
        return true;
    }
}
