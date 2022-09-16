package bj1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> negativeNum = new ArrayList<>();
        List<Integer> positiveNum = new ArrayList<>();

        for (int n = 0; n < N; n++){
            int x = Integer.parseInt(br.readLine());
            if (x <= 0){
                negativeNum.add(x);
            }
            else{
                positiveNum.add(x);
            }
        }

        Collections.sort(negativeNum, Collections.reverseOrder());
        Collections.sort(positiveNum);

        sum = 0;
        cal(negativeNum);
        cal(positiveNum);

        System.out.println(sum);
    }

    static void cal(List<Integer> numList){
        for (int i = numList.size() - 1; i >= 0; i -= 2){
            int a = numList.get(i);
            if (i - 1 >= 0){
                int b = numList.get(i - 1);
                sum += (a * b > a + b)? a * b : a + b;
            }
            else{
                sum += a;
            }
        }
    }
}
