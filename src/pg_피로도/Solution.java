package pg_피로도;

public class Solution {
    static int result;
    static boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        result = 0;
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        answer = result;
        return answer;
    }

    static void dfs(int k, int[][] dungeons, int cnt){
        boolean flag = false;
        for(int i = 0; i < dungeons.length; i++){
            if(dungeons[i][0] > k || visited[i]) continue;
            flag = true;
            visited[i] = true;
            dfs(k - dungeons[i][1], dungeons, cnt + 1);
            visited[i] = false;
        }

        if(!flag){
            result = Math.max(result, cnt);
        }
    }
}
