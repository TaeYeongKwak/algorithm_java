package bj2696;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    static ArrayList<Integer> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        answer = new ArrayList<>();

        for (int i = 0; i < t; i++){
            int m = Integer.parseInt(br.readLine());
            max.clear();
            min.clear();
            answer.clear();
            int cnt = 0;
            if(m > 10){
                while(true){
                    String[] numLine = br.readLine().split(" ");
                    insertHeap(max, min, numLine, cnt);
                    cnt += numLine.length;
                    if (cnt == m) break;
                }
            }else{
                String[] numLine = br.readLine().split(" ");
                insertHeap(max, min, numLine, cnt);
            }
            bw.write(answer.size() + "\n");
            for (int a : answer){
                bw.write(a + " ");
            }
            bw.write("\n");
        }

        bw.flush();

        bw.close();
        br.close();
    }

    static void insertHeap(PriorityQueue<Integer> max, PriorityQueue<Integer> min, String[] numLine, int cnt){
        for (int j = 1; j <= numLine.length; j++){
            int x = Integer.parseInt(numLine[j-1]);
            if (cnt == 0 && j == 1){
                max.offer(x);
            }else{
                if(max.peek() < x){
                    min.offer(x);
                }else{
                    max.offer(x);
                }

                if (min.size() > max.size()){
                    max.offer(min.poll());
                }else if (max.size() - min.size() >= 2){
                    min.offer(max.poll());
                }
            }

            if (j % 2 == 1){
                answer.add(max.peek());
            }
        }
    }
}
