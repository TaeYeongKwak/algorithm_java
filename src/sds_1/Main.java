package sds_1;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        long[] answer = new long[t];

        for(int k = 0; k < t; k++){
            String[] nmd = br.readLine().split(" ");
            int n = Integer.parseInt(nmd[0]);
            int m = Integer.parseInt(nmd[1]);
            int d = Integer.parseInt(nmd[2]);

            Integer[] weeds = new Integer[n * m];
            int x = 0;
            for(int i = 0; i < n; i++){
                String[] weedLine = br.readLine().split(" ");
                for(int j = 0; j < m; j++){
                    weeds[x++] = Integer.parseInt(weedLine[j]);
                }
            }

            int[] oils = new int[d];
            String[] oilLine = br.readLine().split(" ");
            for(int o = 0; o < d; o++)
                oils[o] = Integer.parseInt(oilLine[o]);

            answer[k] = lawnMowing(weeds, oils);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < t; i++){
            bw.write("#" + (i+1) + " " + answer[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static long lawnMowing(Integer[] weeds, int[] oils){
        long answer = 0;
        Arrays.sort(weeds, Collections.reverseOrder());
        int time = 0;
        for(int k = 0; k < weeds.length; k++) weeds[k] += oils.length;
        for(int o = 0; o < oils.length; o++){
            long sum = 0;
            for(int x = 0; x < oils[o]; x++){
                int grow = oils.length - o;
                if(weeds[time] <= grow){
                    sum += 1;
                }else{
                    sum += (weeds[time] - (grow));
                }
                weeds[time] = grow;
                time++;
                if (time >= weeds.length) time = 0;
            }
            answer += (o + 1) * sum;
        }
        return answer;
    }
}
