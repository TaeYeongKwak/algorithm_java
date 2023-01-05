package bj1002;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            bw.write(solution(x1, y1, r1, x2, y2, r2) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int x1, int y1, int r1, int x2, int y2, int r2){
        double distance = getDistance(x1, y1, x2, y2);
        if(x1 == x2 && y1 == y2 && r1 == r2) {
            return -1;
        }
        else if(distance > r1 + r2) {
            return 0;
        }
        else if(distance < Math.abs(r2 - r1)) {
            return 0;
        }
        else if(distance == Math.abs(r2 - r1)) {
            return 1;
        }
        else if(distance == r1 + r2) {
            return 1;
        }
        else {
            return 2;
        }
    }

    static double getDistance(int x1, int y1, int x2, int y2){
        int x = (int) Math.pow(x1 - x2 , 2);
        int y = (int) Math.pow(y1 - y2 , 2);
        return Math.sqrt(x + y);
    }
}
