package pg_가장_가까운_같은_글자;

public class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        answer = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            int x = s.substring(0, i).lastIndexOf(s.charAt(i));
            answer[i] = x == -1? -1 : i - x;
        }
        return answer;
    }
}
