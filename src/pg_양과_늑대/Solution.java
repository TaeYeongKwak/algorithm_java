package pg_양과_늑대;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static List<Integer>[] graph;
    static boolean[][] visited;

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




        return answer;
    }

    static void dfs(int[] info, int[] animal, int n, int bitMask){
        visited[n][bitMask] = true;
        // 해당 노드를 방문했는지 확인
        int x = (int) Math.pow(2, n);
        boolean isVisited = ((bitMask & x) > 0)? true : false;

        for (int next : graph[n]){
            int sheep = animal[0];
            int wolf = animal[1];
            if (!isVisited){
                sheep += (info[next] == 0)? 1 : 0;
                wolf += (info[next] == 1)? 1 : 0;
                bitMask |= x;
            }

        }
    }
}
