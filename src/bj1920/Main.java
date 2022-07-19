package bj1920;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] numArray = new int[n];
        String[] numArrayStr1 = br.readLine().split(" ");
        for (int i = 0; i < n; i++){
            int x = Integer.parseInt(numArrayStr1[i]);
            numArray[i] = x;
        }

        Arrays.sort(numArray);

        int m = Integer.parseInt(br.readLine());

        String[] numArrayStr2 = br.readLine().split(" ");
        for (int i = 0; i < m; i++){
            int x = Integer.parseInt(numArrayStr2[i]);
            int result = binarySearch(numArray, x);
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int binarySearch(int[] numArray, int n){
        int start = 0;
        int end = numArray.length - 1;
        int middle = (start + end) / 2;

        while(end - start >= 0){
            // middle index에 대한 값이 일치할 경우
            if (numArray[middle] == n){
                return 1;
            }
            // middle 값이 더 클 경우
            if (numArray[middle] > n){
                end = middle - 1;
            }
            // middle 값이 더 작을 경우
            else{
                start = middle + 1;
            }
            middle = (start + end) / 2;
        }
        return 0;
    }
}
