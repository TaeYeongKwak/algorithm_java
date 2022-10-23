package bj1765;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] friend;
    static List<Integer>[] enemy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        friend = new int[N + 1];
        enemy = new ArrayList[N + 1];
        for (int n = 1; n < N + 1; n++){
            friend[n] = n;
            enemy[n] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            String relation = st.nextToken();
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            if (relation.equals("F")){
                union(p, q);
            }
            else if (relation.equals("E")){
                enemy[p].add(q);
                enemy[q].add(p);
            }
        }

        for (int i = 1; i < N + 1; i++){
            if (enemy[i].size() < 2) continue;
            for (int j = 1; j < enemy[i].size(); j++){
                union(enemy[i].get(j - 1), enemy[i].get(j));
            }
        }

        Set<Integer> check = new HashSet<>();
        for (int n = 1; n < N + 1; n++){
            check.add(find(friend[n]));
        }

        System.out.println(check.size());
    }

    static int find(int x){
        return friend[x] = (x == friend[x])? x : find(friend[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;

        friend[aRoot] = bRoot;
    }
}
