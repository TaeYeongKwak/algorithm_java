package bj3020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, H;
    static int[] up, down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new int[H + 1];
        down = new int[H + 1];

        for (int i = 1; i < N + 1; i++){
            int stone = Integer.parseInt(br.readLine());
            if (i % 2 == 0){
                up[H - stone + 1] += 1;
            }
            else{
                down[stone] += 1;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int min = 200000;

        int sum = 0;
        for (int i = 1; i < H + 1; i++){
            if (up[i] > 0){
                sum += up[i];
            }
            up[i] = sum;
        }

        sum = 0;
        for (int i = H; i > 0; i--){
            if (down[i] > 0){
                sum += down[i];
            }
            down[i] = sum + up[i];
            map.put(down[i], map.getOrDefault(down[i], 0) + 1);
            min = Math.min(down[i], min);
        }

        System.out.println(min + " " + map.get(min));
    }
}
