package bj1764;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> set = new HashSet<>();
        for(int n = 0; n < N; n++){
            set.add(br.readLine());
        }

        PriorityQueue<String> answer = new PriorityQueue<>();
        for (int m = 0; m < M; m++){
            String s = br.readLine();
            if (set.contains(s)){
                answer.offer(s);
            }
        }

        bw.write(answer.size() + "\n");
        while(!answer.isEmpty()){
            bw.write(answer.poll());
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
