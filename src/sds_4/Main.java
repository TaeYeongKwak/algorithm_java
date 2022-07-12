package sds_4;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, s, e;
    static int[] a;
    static ArrayList<int[]>[] stationList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] answer = new int[t];
        for (int i = 0; i < t; i++){
            String[] nmse = br.readLine().split(" ");
            n = Integer.parseInt(nmse[0]);
            m = Integer.parseInt(nmse[1]);
            s = Integer.parseInt(nmse[2]);
            e = Integer.parseInt(nmse[3]);

            a = new int[m + 1];
            String[] aLine = br.readLine().split(" ");
            for (int j = 1; j < m + 1; j++){
                a[j] = Integer.parseInt(aLine[j-1]);
            }

            stationList = new ArrayList[n + 1];
            for(int j = 1; j < n + 1; j++){
                stationList[j] = new ArrayList<>();
            }

            for(int j = 1; j < m + 1; j++){
                String[] stationNumLine = br.readLine().split(" ");
                for(int k = 0; k < stationNumLine.length; k++){
                    int before, next = 0;
                    int now = Integer.parseInt(stationNumLine[k]);
                    if(k == 0){
                        next = Integer.parseInt(stationNumLine[k+1]);
                        stationList[now].add(new int[]{next, j});
                    }else if(k == stationNumLine.length - 1){
                        before = Integer.parseInt(stationNumLine[k-1]);
                        stationList[now].add(new int[]{before, j});
                    }else{
                        before = Integer.parseInt(stationNumLine[k-1]);
                        next = Integer.parseInt(stationNumLine[k+1]);
                        stationList[now].add(new int[]{before, j});
                        stationList[now].add(new int[]{next, j});
                    }
                }
            }

            answer[i] = getTransferCnt();

        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < t; i++){
            bw.write("#" + (i+1) + " " + answer[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int getTransferCnt(){
        boolean[][] visited = new boolean[n + 1][1001];
        int[] transCnt = new int[n + 1];

        for (int i = 1; i < n + 1; i++){
            transCnt[i] = Integer.MAX_VALUE;
        }

        Queue<int[]> queue = new LinkedList<>();
        int[] startStation = new int[]{s, 0};
        queue.offer(startStation);

        transCnt[s] = 0;

        while(!queue.isEmpty()){
            int[] tempStation = queue.poll();
            for(int[] st : stationList[tempStation[0]]){
                if(tempStation[0] == s){
                    tempStation[1] = st[1];
                    visited[s][st[1]] = true;
                }
                int weight = 0;
                if(st[1] != tempStation[1]){
                    weight = 1;
                }
                if(visited[st[0]][st[1]] && transCnt[tempStation[0]] + weight < transCnt[st[0]]){
                    visited[st[0]][st[1]] = false;
                }
                if(!visited[st[0]][st[1]]){
                    if(transCnt[tempStation[0]] + weight <= transCnt[st[0]]){
                        transCnt[st[0]] = transCnt[tempStation[0]] + weight;
                        queue.offer(st);
                        visited[st[0]][st[1]] = true;
                    }
                }
            }
        }
        return (transCnt[e] == Integer.MAX_VALUE)? -1 : transCnt[e];
    }
}
