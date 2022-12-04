package pg_기사단원의_무기;

public class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for(int i = 1; i <= number; i++){
            answer += iron(i, limit, power);
        }
        return answer;
    }

    static int iron(int index, int limit, int power){
        int count = 0;
        for(int i = 1; i * i <= index; i++){
            if(i * i == index) count += 1;
            else if(index % i == 0) count += 2;
        }

        if(count > limit) count = power;
        return count;
    }
}
