package sds_3;

import java.io.*;

public class Main {
    static int n;
    static int[][] MBTI = {{1,0,0,1,0,1,1,0}, {1,0,0,1,0,1,0,1}, {1,0,0,1,1,0,1,0}, {1,0,0,1,1,0,0,1}, {1,0,1,0,0,1,1,0}, {1,0,1,0,0,1,0,1}, {1,0,1,0,1,0,1,0}, {1,0,1,0,1,0,0,1}, //ENFJ, ENFP, ENTJ, ENTP, ESFJ, ESFP, ESTJ, ESTP
             {0,1,0,1,0,1,1,0}, {0,1,0,1,0,1,0,1}, {0,1,0,1,1,0,1,0}, {0,1,0,1,1,0,0,1}, {0,1,1,0,0,1,1,0}, {0,1,1,0,0,1,0,1}, {0,1,1,0,1,0,1,0}, {0,1,1,0,1,0,0,1}}; // INFJ, INFP, INTJ, INTP, ISFJ, ISFP, ISTJ, ISTP
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] answer = new int[t];
        for (int i = 0; i < t; i++){
            n = Integer.parseInt(br.readLine());
            int[][] cost = new int[17][2];
            String[] costLine = br.readLine().split(" ");
            for(int j = 1; j < 17; j++){
                cost[j][0] = j;
                cost[j][1] = Integer.parseInt(costLine[j-1]);
            }

            answer[i] = minCost(cost);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < t; i++){
            bw.write("#" + (i+1) + " " + answer[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int minCost(int[][] cost){
        sum = Integer.MAX_VALUE;
        boolean[] visited = new boolean[16];

        for(int r = 1; r < 17; r++){
            combination(cost, visited, 1, r);
        }

        return sum;
    }

    static boolean check(int[] nCheck){
        boolean result = true;
        for(int i = 0; i < 8; i++){
            if(nCheck[i] < n) {
                result = false;
                break;
            }
        }
        return result;
    }

    static void combination(int[][] cost, boolean[] visited, int start, int r){
        if (r == 0){
            int tempSum = 0;
            int[] nCheck = {0, 0, 0, 0, 0, 0, 0, 0};
            for (int i = 1; i < 17; i++){
                if(visited[i-1]){
                    for(int j = 0; j < 8; j++){
                        nCheck[j] += MBTI[cost[i][0]-1][j];
                    }
                    tempSum += cost[i][1];
                }
            }
            if(check(nCheck) && sum > tempSum){
                sum = tempSum;
            }
            return;
        } else{
            for (int i = start; i < 17; i++){
                visited[i-1] = true;
                combination(cost, visited, i + 1, r - 1);
                visited[i-1] = false;
            }
        }
    }
}
