package bj1722;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] num;
    static boolean[] visited;
    static StringBuilder sb;
    static long[] factorial;
    static List<Integer> numList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        factorial = new long[N + 1];
        factorial[1] = 1;
        for (int i = 2; i < N + 1; i++){
            factorial[i] = factorial[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());

        if (C == 1){
            long k = Long.parseLong(st.nextToken());
            sb = new StringBuilder();
            numList = new ArrayList<>();
            for (int i = 1; i < N + 1; i++) {
                numList.add(i);
            }
            findPermutation(0, factorial[N], N, k);
            System.out.println(sb.toString());
        }else if (C == 2){
            num = new int[N];
            visited = new boolean[N + 1];
            for (int i = 0; i < N; i++){
                num[i] = Integer.parseInt(st.nextToken());
            }
            long result = 0;

            for (int i = 0; i < N; i++){
                for (int j = 1; j < num[i]; j++){
                    if (!visited[j]){
                        result += factorial[N - i - 1];
                    }
                }
                visited[num[i]] = true;
            }

            System.out.println(result + 1);
        }

    }

    static void findPermutation(long left, long right, int n, long order){
        if (n == 1){
            sb.append(numList.get(0));
            return;
        }
        //팩토리얼 구하기
        long fact = factorial[n - 1];

        for (int i = 1; i < n + 1; i++){
            if (left + ((i - 1) * fact) < order && order <= left + (i * fact)){
                sb.append(numList.get(i - 1) + " ");
                numList.remove(i - 1);
                findPermutation(left + ((i - 1) * fact), left + (i * fact), n - 1, order);
            }
        }
    }

}
