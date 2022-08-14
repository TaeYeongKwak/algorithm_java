package bj13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] room = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            room[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        long result = 0;
        for (int i = 0; i < N; i++){
            long temp = (long)room[i] - B;
            result += 1;
            if (temp > 0){
                result += (temp / C);
                if (temp % C > 0){
                    result += 1;
                }
            }
        }
        System.out.println(result);
    }
}
