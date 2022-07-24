package bj2094;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] rainfall;
    static Map<Integer, Integer> rainfallMap;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        rainfall = new int[N + 1][2];
        tree = new int[4 * N];
        rainfallMap = new HashMap<>();
        // 년도별 강수량 입력받기
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            rainfall[i][0] = Integer.parseInt(st.nextToken());
            rainfall[i][1] = Integer.parseInt(st.nextToken());
            rainfallMap.put(rainfall[i][0], i);
        }

        init(1, N, 1);

        M = Integer.parseInt(br.readLine());
        //문제 입력받기
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int Y = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            
            //해당 연도의 정보가 둘다 존재 할 경우
            if (rainfallMap.containsKey(X) && rainfallMap.containsKey(Y)){
                int xTarget = rainfallMap.get(X);
                int yTarget = rainfallMap.get(Y);
                int maxYearRain = query(1, N, 1, yTarget + 1, xTarget - 1);
                if (maxYearRain >= rainfall[xTarget][1] || rainfall[yTarget][1] < rainfall[xTarget][1]){
                    bw.write("false\n");
                }
                else if (yTarget - xTarget == Y - X){
                    bw.write("true\n");
                }else{
                    bw.write("maybe\n");
                }
            }
            // 두 정보가 존재하지 않을 경우
            // X 년도의 정보는 존재하나 Y년도의 정보는 존재하지 않을 경우
            else if (rainfallMap.containsKey(X) && !rainfallMap.containsKey(Y)){
                int xTarget = rainfallMap.get(X);
                int yTarget = binarySearch(Y);
                int maxYearRain = query(1, N, 1, yTarget, xTarget - 1);
                if (maxYearRain >= rainfall[xTarget][1]){
                    bw.write("false\n");
                }else{
                    bw.write("true\n");
                }
            }
            // Y년도의 정보는 존재하나 X년도의 정보는 존재하지 않는 경우
            else if(!rainfallMap.containsKey(X) && rainfallMap.containsKey(Y)){
                int yTarget = rainfallMap.get(Y);
                int xTarget = binarySearch(X);
                int maxYearRain = query(1, N, 1, yTarget + 1, xTarget - 1);

                if (maxYearRain < rainfall[yTarget][1]){
                    bw.write("maybe\n");
                }else{
                    bw.write("false\n");
                }
            }
            // 둘다 존재하지 않는 경우
            else if (!rainfallMap.containsKey(X) && !rainfallMap.containsKey(Y)){
                bw.write("maybe\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int init(int left, int right, int node){
        if (left == right){
            return tree[node] = rainfall[left][1];
        }
        int mid = (left + right) / 2;
        return tree[node] = Math.max(init(left, mid, 2 * node), init(mid + 1, right, 2 * node + 1));
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if (queryLeft > right || left > queryRight){
            return 0;
        }
        else if (queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        else{
            int mid = (left + right) / 2;
            return Math.max(query(left, mid, 2 * node, queryLeft, queryRight), query(mid + 1, right, 2 * node + 1, queryLeft, queryRight));
        }
    }

    static int binarySearch(int year){
        int left = 1;
        int right = N;
        int mid;
        while(left < right){
            mid = (left + right) / 2;
            int midYear = rainfall[mid][0];
            if (midYear < year){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return right;
    }

}
