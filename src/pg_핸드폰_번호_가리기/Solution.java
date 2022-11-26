package pg_핸드폰_번호_가리기;

public class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int n = phone_number.length() - 4;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append('*');
        }

        sb.append(phone_number.substring(n));
        answer = sb.toString();
        return answer;
    }
}
