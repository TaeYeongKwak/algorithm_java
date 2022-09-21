package pg_양과_늑대;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static List<Integer>[] graph;
    static boolean[][] visited;
    static int sheep;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] info = new int[N];
        for (int i = 0; i < N; i++){
            info[i] = Integer.parseInt(st.nextToken());
        }

        int[][] edge = new int[info.length - 1][2];
        for (int i = 0; i < edge.length; i++){
            st = new StringTokenizer(br.readLine());
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
        }

        Solution s = new Solution();
        System.out.println(s.solution(info, edge));
    }

    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        sheep = 0;
        graph = new List[info.length];
        for (int i = 0; i < info.length; i++){
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        visited = new boolean[info.length][(int) Math.pow(2, 18)];
        dfs(info, new int[]{1, 0}, 0, 1);
        answer = sheep;
        return answer;
    }

    static void dfs(int[] info, int[] animal, int n, int bitMask){
        if (visited[n][bitMask]) return;

        visited[n][bitMask] = true;
        sheep = Math.max(sheep, animal[0]);

        for (int next : graph[n]){
            int sheep = animal[0];
            int wolf = animal[1];
            int x = (int) Math.pow(2, next);

            boolean isVisited = (bitMask & x) > 0;
            if (!isVisited){
                sheep += (info[next] == 0)? 1 : 0;
                wolf += (info[next] == 1)? 1 : 0;
            }

            if (sheep > wolf){
                dfs(info, new int[]{sheep, wolf}, next, bitMask | x);
            }
        }
    }
}
