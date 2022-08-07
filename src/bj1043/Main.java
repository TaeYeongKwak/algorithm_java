package bj1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parent;
    static ArrayList<Integer>[] party;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int truthSize = Integer.parseInt(st.nextToken());
        for (int i = 0; i < truthSize; i++){
            int t = Integer.parseInt(st.nextToken());
            parent[t] = 0;
        }

        party = new ArrayList[M + 1];

        for (int m = 1; m < M + 1; m++){
            party[m] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int partyMember = Integer.parseInt(st.nextToken());
            int last = 0;
            for (int p = 1; p < partyMember + 1; p++){
                int member = Integer.parseInt(st.nextToken());
                party[m].add(member);
                if (p > 1){
                    union(last, member);
                }
                last = member;
            }
        }

        int result = 0;
        for (int m = 1; m < M + 1; m++){
            boolean flag = true;
            for (int member : party[m]){
                if (find(member) == 0){
                    flag = false;
                }
            }
            if (flag){
                result++;
            }
        }

        System.out.println(result);
    }

    static int find(int x){
        return parent[x] = (x == parent[x])? x : find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot > bRoot){
            parent[aRoot] = bRoot;
        }else{
            parent[bRoot] = aRoot;
        }
    }

}
