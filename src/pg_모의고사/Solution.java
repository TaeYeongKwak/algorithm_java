package pg_모의고사;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};

        int[][] select = {{1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};

        int[] index = {0, 0, 0};

        int[] cnt = new int[3];
        for(int i = 0; i < answers.length; i++){
            for(int j = 0; j < 3; j++){
                if(select[j][index[j]] == answers[i]){
                    cnt[j]++;
                }
                index[j] = (index[j] == select[j].length - 1)? 0 : index[j] + 1;
            }
        }


        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            list.add(new int[]{i + 1, cnt[i]});
        }

        Collections.sort(list, (o1, o2) -> Integer.compare(o2[1], o1[1]));
        int size = 0;
        int max = 0;
        for(int i = 0; i < 3; i++){
            if(list.get(i)[1] >= max){
                size++;
                max = list.get(i)[1];
            }
        }

        answer = new int[size];
        for(int i = 0; i < size; i++){
            answer[i] = list.get(i)[0];
        }

        return answer;
    }
}
