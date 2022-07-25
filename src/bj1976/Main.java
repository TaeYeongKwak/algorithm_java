package bj1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            parent[i] = i;
        }

        for (int i = 1; i < N + 1; i++){
            String[] edgeLine = br.readLine().split(" ");
            for (int j = 1; j < N + 1; j++){
                int e = Integer.parseInt(edgeLine[j - 1]);
                if (e == 1){
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean isPossible = true;
        int nowCity = Integer.parseInt(st.nextToken());
        for (int i = 1; i < M; i++){
            int nextCity = Integer.parseInt(st.nextToken());

            int nowRoot = find(nowCity);
            int nextRoot = find(nextCity);

            if (nowRoot != nextRoot){
                isPossible = false;
                break;
            }
            nowCity = nextCity;
        }

        System.out.println(isPossible? "YES" : "NO");
    }

    static int find(int x){
        if (x == parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        parent[aRoot] = bRoot;
    }
}
