package pg_주차_요금_계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] fees = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++){
            fees[i] = Integer.parseInt(st.nextToken());
        }

        int N = Integer.parseInt(br.readLine());
        String[] records = new String[N];
        for (int i = 0; i < N; i++){
            records[i] = br.readLine();
        }

        Solution s = new Solution();
        int[] result = s.solution(fees, records);
        for (int r : result){
            System.out.print(r + " ");
        }
        System.out.println();
    }

    static Map<String, Info> vehicleInfo;

    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        vehicleInfo = new TreeMap<>();
        StringTokenizer st;
        for (String record : records){
            st = new StringTokenizer(record);
            int time = getMinute(st.nextToken());
            String vehicleNum = st.nextToken();
            String state = st.nextToken();

            Info info = vehicleInfo.getOrDefault(vehicleNum, new Info(vehicleNum, time));
            if (state.equals("IN")){
                info.in(time);
            }
            else if (state.equals("OUT")){
                info.out(time);
            }
            vehicleInfo.put(vehicleNum, info);
        }

        answer = new int[vehicleInfo.size()];
        String[] keySet = vehicleInfo.keySet().toArray(new String[0]);
        for (int i = 0; i < vehicleInfo.size(); i++){
            String key = keySet[i];
            Info info = vehicleInfo.get(key);
            if (info.isIn){
                info.out(1439);
            }

            answer[i] = info.calculateMoney(fees);
        }

        return answer;
    }

    static int getMinute(String time){
        String[] hm = time.split(":");
        int h = Integer.parseInt(hm[0]);
        int m = Integer.parseInt(hm[1]);

        return (h * 60) + m;
    }
}

class Info{
    String vehicleNum;
    int inTime;
    int timeSum;
    boolean isIn;

    public Info(String vehicleNum, int inTime) {
        this.vehicleNum = vehicleNum;
        this.inTime = inTime;
        timeSum = 0;
        isIn = true;
    }

    public void out(int outTime){
        timeSum += outTime - this.inTime;
        isIn = false;
    }

    public void in(int inTime){
        this.inTime = inTime;
        isIn = true;
    }

    public int calculateMoney(int[] fees){
        int result = fees[1];
        if (timeSum <= fees[0]){
            return result;
        }
        timeSum -= fees[0];
        int k = timeSum / fees[2];
        int mod = timeSum % fees[2];
        k += (mod > 0)? 1 : 0;
        result += k * fees[3];
        return result;
    }
}
