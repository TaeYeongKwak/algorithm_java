package pg_베스트앨범;

import java.util.*;

public class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, GenreInfo> map = new HashMap<>();
        GenreInfo gi;
        for(int i = 0; i < genres.length; i++){
            if(map.containsKey(genres[i])){
                gi = map.get(genres[i]);
                gi.sum += plays[i];
                gi.pq.offer(new int[]{i, plays[i]});
            }
            else{
                gi = new GenreInfo(genres[i]);
                gi.sum += plays[i];
                gi.pq.offer(new int[]{i, plays[i]});
                map.put(genres[i], gi);
            }
        }

        List<String> list = new ArrayList<>();
        for(String genre : map.keySet()){
            list.add(genre);
        }

        Collections.sort(list, (o1, o2) -> Integer.compare(map.get(o2).sum, map.get(o1).sum));

        List<Integer> result = new ArrayList<>();
        int index = 0;
        PriorityQueue<int[]> pq;
        int cnt;
        for(int i = 0; i < list.size(); i++){
            pq = map.get(list.get(i)).pq;
            cnt = 0;
            while(!pq.isEmpty() && cnt < 2){
                cnt++;
                result.add(pq.poll()[0]);
            }
        }

        answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}

class GenreInfo{
    String genre;
    int sum;
    PriorityQueue<int[]> pq;

    public GenreInfo(String genre){
        this.genre = genre;
        sum = 0;
        pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
    }
}
