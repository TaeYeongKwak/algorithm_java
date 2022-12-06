package pg_합승_택시_요금;

public class Solution {

    static int[][] dist;
    final static int MAX_VALUE = 20000001;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = MAX_VALUE;
        init(n, fares);

        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for(int i = 1; i < n + 1; i++){
            answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }

        return answer;
    }

    static void init(int n, int[][] fares){
        dist = new int[n + 1][n + 1];

        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(i == j) {
                    dist[i][j] = 0;
                    continue;
                }
                dist[i][j] = MAX_VALUE;
            }
        }

        for(int[] fare : fares){
            int from = fare[0];
            int to = fare[1];
            int money = fare[2];
            dist[from][to] = money;
            dist[to][from] = money;
        }
    }
}
