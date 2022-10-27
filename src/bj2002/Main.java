package bj2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();
        int[] order = new int[N];
        for (int i = 0; i < N; i++){
            String car = br.readLine();
            map.put(car, i);
        }

        for (int i = 0; i < N; i++){
            String car = br.readLine();
            order[i] = map.get(car);
        }

        int cnt = 0;
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                if (order[i] > order[j]){
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
