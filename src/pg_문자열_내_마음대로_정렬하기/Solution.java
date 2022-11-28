package pg_문자열_내_마음대로_정렬하기;

import java.util.*;

public class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};
        Arrays.sort(strings, Comparator.comparingInt((String o) -> o.charAt(n)).thenComparing(o -> o));
        answer = strings;
        return answer;
    }
}
