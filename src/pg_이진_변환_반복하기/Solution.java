package pg_이진_변환_반복하기;

public class Solution {

    static int zero, repeat;

    public int[] solution(String s) {
        int[] answer = {};
        trans(s);
        answer = new int[]{repeat, zero};
        return answer;
    }

    static void trans(String s){
        if(s.equals("1")){
            return;
        }

        repeat++;

        char[] x = s.toCharArray();
        int one = 0;
        for(int i = 0; i < x.length; i++){
            one += (x[i] == '1')? 1 : 0;
            zero += (x[i] == '1')? 0 : 1;
        }

        trans(binary(one));
    }

    static String binary(int num){
        StringBuilder sb = new StringBuilder();
        int temp = num;
        while(temp != 0){
            sb.append(temp % 2);
            temp /= 2;
        }

        return sb.reverse().toString();
    }

}
