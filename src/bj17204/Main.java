package bj17204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] select = new int[N];
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++){
            select[i] = Integer.parseInt(br.readLine());
        }

        visited[0] = true;
        int result = 0;
        int idx = 0;
        while(true){
            if (idx == M) break;
            idx = select[idx];
            result++;
            if (visited[idx]){
                result = -1;
                break;
            }
            visited[idx] = true;
        }

        System.out.println(result);
    }

}