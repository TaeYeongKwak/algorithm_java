package pg_jadenCase_문자열_만들기;

public class Solution {
    public String solution(String s) {
        String answer = "";
        String[] sArray = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < sArray.length; i++){
            if(sArray[i] == null || sArray[i].isEmpty()){
                if(i < sArray.length - 1){
                    sb.append(" ");
                }
                continue;
            }

            char[] cArray = sArray[i].toLowerCase().toCharArray();
            if(97 <= cArray[0] && cArray[0] <= 122){
                cArray[0] = (char)(cArray[0] - 32);
            }

            for(char c : cArray){
                sb.append(c);
            }

            if(i < sArray.length - 1){
                sb.append(" ");
            }
        }

        if(s.charAt(s.length() - 1) == ' '){
            sb.append(' ');
        }

        answer = sb.toString();
        return answer;
    }
}
