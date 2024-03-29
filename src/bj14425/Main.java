package bj14425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for (int n = 0; n < N; n++){
           set.add(br.readLine());
        }

        int answer = 0;
        for (int m = 0; m < M; m++){
            if (set.contains(br.readLine())) answer++;
        }

        System.out.println(answer);
    }
}
