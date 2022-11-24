package pg_n진수_게임;

public class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int maxSize = t * m;
        StringBuilder numStr = new StringBuilder();
        for(int i = 0; numStr.length() <= maxSize; i++){
            numStr.append(Integer.toString(i, n));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = p - 1; sb.length() < t; i += m){
            sb.append(numStr.charAt(i));
        }
        answer = sb.toString().toUpperCase();
        return answer;
    }
}
