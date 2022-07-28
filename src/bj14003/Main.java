package bj14003;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] num;
    static int[] numIdx;
    static ArrayList<Integer> list;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[N];
        numIdx = new int[N];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
            int size = list.size() - 1;

            if (size == -1 || num[i] > list.get(size)){
                list.add(num[i]);
                numIdx[i] = list.size()-1;
            }else{
                int x = binarySearch(num[i]);
                list.set(x, num[i]);
                numIdx[i] = x;
            }
        }

        S = list.size() - 1;

        Stack<Integer> result = new Stack<>();
        for (int i = N - 1; i >= 0; i--){
            if (S == numIdx[i]){
                result.push(num[i]);
                S--;
            }
        }

        bw.write(result.size() + "\n");
        while(!result.isEmpty()){
            bw.write(result.pop() + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int binarySearch(int target){
        int left = 0;
        int right = list.size() - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if (list.get(mid) >= target){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }

        return right;
    }

}
