package pg_이중우선순위큐;

import java.util.*;

public class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(String operation : operations){
            String[] oper = operation.split(" ");
            int value = Integer.parseInt(oper[1]);
            if(oper[0].equals("I")){
                map.put(value, map.getOrDefault(value, 0) + 1);
                maxHeap.offer(value);
                minHeap.offer(value);
            }
            else if(oper[0].equals("D")){
                PriorityQueue<Integer> selectHeap = (value == 1)? maxHeap : minHeap;
                while(!selectHeap.isEmpty()){
                    int x = selectHeap.poll();
                    if(map.containsKey(x)){
                        int cnt = map.get(x) - 1;
                        map.put(x, cnt);
                        if(cnt == 0){
                            map.remove(x);
                        }
                        break;
                    }
                }
            }
        }

        int max = 0;
        int min = 0;
        while(!maxHeap.isEmpty()){
            int value = maxHeap.poll();

            if(map.containsKey(value)){
                max = value;
                break;
            }
        }

        while(!minHeap.isEmpty()){
            int value = minHeap.poll();

            if(map.containsKey(value)){
                min = value;
                break;
            }
        }

        answer = new int[]{max, min};
        return answer;
    }
}
