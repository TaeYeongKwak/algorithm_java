package bj1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N + 1];
        Map<Integer, Integer> check = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 1; i < N + 1; i++){
            num[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, num[i]);
            check.put(num[i], check.getOrDefault(num[i], 0) + 1);
        }

        int result = 0;
        for (int i = 1; i < N + 1; i++){
            for (int j = i + 1; j < N + 1; j++){
                int sum = num[i] + num[j];
                if (check.containsKey(sum)){
                    int count = check.get(sum);
                    if (num[i] == 0 && num[j] == 0){
                        if (count >= 3){
                            result += count;
                            check.remove(sum);
                        }
                    }
                    else if (num[i] == 0 || num[j] == 0){
                        if (count >= 2){
                            result += count;
                            check.remove(sum);
                        }
                    }
                    else{
                        result += count;
                        check.remove(sum);
                    }
                }
            }
        }

        System.out.println(result);
    }
}
