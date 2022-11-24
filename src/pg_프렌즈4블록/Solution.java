package pg_프렌즈4블록;

import java.util.*;

public class Solution {

    static int N, M;
    static Queue<int[]> queue;
    static boolean[][] visited;
    static char[][] map;
    static int result;

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        N = n; M = m;
        queue = new LinkedList<>();
        visited = new boolean[m][n];
        map = new char[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = board[i].charAt(j);
            }
        }

        result = 0;
        while(!broken()){
            down();
        }

        answer = result;
        return answer;
    }

    static int[] dx = {0, 1, 0, 1};
    static int[] dy = {0, 0, 1, 1};

    static boolean broken(){
        int cnt = 0;
        for(int m = 0; m < M; m++){
            Arrays.fill(visited[m], false);
        }

        boolean check;
        for(int i = 0; i < M - 1; i++){
            for(int j = 0; j < N - 1; j++){
                char c = map[i][j];
                if(c == ' ') continue;

                check = true;
                for(int k = 1; k < 4; k++){
                    int nx = j + dx[k];
                    int ny = i + dy[k];

                    if(map[ny][nx] != c){
                        check = false;
                        break;
                    }
                }
                if(check){
                    for(int k = 0; k < 4; k++){
                        int nx = j + dx[k];
                        int ny = i + dy[k];

                        visited[ny][nx] = true;
                    }
                }
            }
        }

        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]) continue;
                map[i][j] = ' ';
                cnt++;
            }
        }

        result += cnt;
        return cnt == 0;
    }

    static void down(){
        for(int x = 0; x < N; x++){
            for(int y = M - 1; y >= 0; y--){
                if(map[y][x] == ' ') continue;
                int ny = y;
                while(ny + 1 < M && map[ny + 1][x] == ' '){
                    map[ny + 1][x] = map[ny][x];
                    map[ny][x] = ' ';
                    ny += 1;
                }
            }
        }
    }
}