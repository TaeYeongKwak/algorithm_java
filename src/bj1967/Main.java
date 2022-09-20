package bj1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<int[]>[] graph;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, weight});
        }

        max = 0;
        getMaxWeight(1);
        System.out.println(max);
    }

    static int getMaxWeight(int node){
        int maxRootWeight[] = {0, 0};
        for (int[] next : graph[node]){
            int x = getMaxWeight(next[0]) + next[1];
            if (x >= maxRootWeight[0]){
                maxRootWeight[1] = maxRootWeight[0];
                maxRootWeight[0] = x;
            }else if (x < maxRootWeight[0] && x > maxRootWeight[1]){
                maxRootWeight[1] = x;
            }
        }
        max = Math.max(max, maxRootWeight[0] + maxRootWeight[1]);
        return maxRootWeight[0];
    }
}
