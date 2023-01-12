package bj11866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, K));
    }

    public static String solution(int N, int K){
        Queue<Integer> queue = new LinkedList<>();
        for (int n = 1; n <= N; n++){
            queue.offer(n);
        }

        StringBuilder sb = new StringBuilder("<");
        while(!queue.isEmpty()){
            for (int k = 0; k < K - 1; k++){
                queue.offer(queue.poll());
            }
            sb.append(queue.poll() + ", ");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.setCharAt(sb.length() - 1, '>');
        return sb.toString();
    }
}
