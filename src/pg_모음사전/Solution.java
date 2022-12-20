package pg_모음사전;

public class Solution {
    public int solution(String word) {
        int answer = word.length();
        int[] RATE_OF_INCREASE = {781, 156, 31, 6, 1};
        String alphabet = "AEIOU";

        for (int i = 0; i < word.length(); i++) {
            answer += (RATE_OF_INCREASE[i] * alphabet.indexOf(word.charAt(i)));
        }

        return answer;
    }
}
