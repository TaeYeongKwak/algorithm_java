package bj20191;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static String S, T;
    static List<Integer>[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        System.out.println(solution());
    }

    static int solution(){
        char[] sArray = S.toCharArray();
        char[] tArray = T.toCharArray();

        dp = new ArrayList[26];
        for (int i = 0; i < 26; i++){
            dp[i] = new ArrayList<>();
        }

        int cInt = 0;
        for (int i = 0; i < tArray.length; i++){
            cInt = tArray[i] - 'a';
            dp[cInt].add(i);
        }

        int nowIndex = -1;
        int result = 1;
        int lastIndex = -1;
        for (int i = 0; i < sArray.length; i++){
            nowIndex = searchOrder(sArray[i], lastIndex);
            if (nowIndex > -1){
                if (nowIndex <= lastIndex){
                    result += 1;
                }
                lastIndex = nowIndex;
            }
            else{
                return -1;
            }
        }

        return result;

    }

    static int searchOrder(char searchChar, int fromIndex){
        int searchInt = searchChar - 'a';
        int lastSize = dp[searchInt].size();
        if (lastSize == 0) return -1;
        if (dp[searchInt].get(lastSize - 1) <= fromIndex) return dp[searchInt].get(0);

        int left = 0;
        int right = lastSize - 1;
        int mid = (left + right) / 2;
        int check = dp[searchInt].get(0);
        while(left <= right){
            if (dp[searchInt].get(mid) > fromIndex){
                right = mid - 1;
                check = dp[searchInt].get(mid);
            }
            else{
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return check;
    }
}
