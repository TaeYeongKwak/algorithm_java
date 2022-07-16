package bj9019;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int startNum, endNum;
    static boolean[] visited;
    static String[] command;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        String[] answer = new String[t];
        for (int i = 0; i < t; i++){
            command = new String[10000];
            visited = new boolean[10000];
            String[] numLine = br.readLine().split(" ");
            startNum = Integer.parseInt(numLine[0]);
            endNum = Integer.parseInt(numLine[1]);

            answer[i] = dslr();
        }

        for (int i = 0; i < t; i++){
            bw.write(answer[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static String dslr(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNum);
        command[startNum] = "";
        visited[startNum] = true;

        while(!queue.isEmpty()){
            int num = queue.poll();
            int[][] list = {{(2 * num) % 10000, 68}, {(num == 0)? 9999 : num - 1, 83}, {(num % 1000) * 10 + (num / 1000), 76}, {(num % 10) * 1000 + (num / 10), 82}};
            for(int[] n : list){
                if (!visited[n[0]]){
                    visited[n[0]] = true;
                    queue.offer(n[0]);
                    command[n[0]] = command[num] + (char)(n[1]);
                }
            }
        }

        return command[endNum];
    }
}
