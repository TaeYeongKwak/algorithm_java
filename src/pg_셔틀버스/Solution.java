package pg_셔틀버스;

import java.util.*;

public class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        PriorityQueue<Time> pq = new PriorityQueue<>();
        Queue<Time> wait = new LinkedList<>();

        for(String time : timetable){
            pq.offer(new Time(time));
        }

        Time busTime = new Time("09:00");
        Time con = new Time("09:00");
        Time time;
        n--;
        while(n >= 0){
            // 대기줄에 사람 도착
            while(!pq.isEmpty()){
                if(busTime.compareTo(pq.peek()) < 0){
                    break;
                }
                wait.offer(pq.poll());
            }

            // 버스 탑승 시작
            if(wait.size() >= m){
                for(int i = 0; i < m; i++){
                    con = wait.poll();
                }
                con.addTime(-1);
            }
            else{
                wait.clear();
                con = new Time(busTime.toString());
            }

            if(n >= 0){
                busTime.addTime(t);
            }

            n--;
        }

        answer = con.toString();
        return answer;
    }
}

class Time implements Comparable<Time>{
    int hour;
    int minute;

    public Time(String time){
        String[] timeInfo = time.split(":");
        this.hour = Integer.parseInt(timeInfo[0]);
        this.minute = Integer.parseInt(timeInfo[1]);
    }

    public void addTime(int t){
        minute += t;
        if(minute >= 60){
            minute -= 60;
            hour += 1;
        }
        else if(minute < 0){
            minute += 60;
            hour -= 1;
        }
    }

    @Override
    public int compareTo(Time o){
        int x = Integer.compare(this.hour, o.hour);
        return (x == 0)? Integer.compare(this.minute, o.minute) : x;
    }

    @Override
    public String toString(){
        return String.format("%02d:%02d", hour, minute);
    }
}
