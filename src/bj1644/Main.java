package bj1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int N;
    static boolean[] isNotPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init();

        ArrayList<Long> primeSumList = new ArrayList<>();
        long sum = 0;
        for (int i = 1; i < N + 1; i++){
            if (!isNotPrime[i]){
                sum += i;
                primeSumList.add(sum);
            }
        }


        int count = 0;
        int size = primeSumList.size();
        int left = 0;
        int right = 0;
        while(left <= right){
            long value = primeSumList.get(right) - primeSumList.get(left);
            if (value == N){
                count++;
                left++;
            }
            else if (value < N){
                right++;
                if (right >= size){
                    right = size - 1;
                    left++;
                }
            }
            else if (value > N){
                left++;
            }

        }

        System.out.println(count);
    }

    static void init(){
        isNotPrime = new boolean[N + 1];
        for (int i = 2; i < N + 1; i++){
            if (!isNotPrime[i]){
                for (int j = 2; i * j < N + 1; j++){
                    isNotPrime[i * j] = true;
                }
            }
        }
    }
}
