package pg_큰_수_만들기;

public class Solution {
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int last = 0;
        int max = 0;
        for(int i = 0; i < number.length() - k; i++){
            max = 0;
            for(int j = last; j <= k + i; j++){
                int num = number.charAt(j) - '0';
                if(max < num) {
                    max = num;
                    last = j + 1;
                }
            }
            sb.append(max);
        }
        answer = sb.toString();
        return answer;
    }
}
