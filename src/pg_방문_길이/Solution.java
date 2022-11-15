package pg_방문_길이;

public class Solution {
    public int solution(String dirs) {
        int answer = 0;
        char[] path = dirs.toCharArray();
        boolean[][][][] visited = new boolean[11][11][11][11];
        int x = 5; int y = 5;
        for(int i = 0; i < path.length; i++){
            int nx = x;
            int ny = y;
            if(path[i] == 'U'){
                ny--;
            }
            else if(path[i] == 'D'){
                ny++;
            }
            else if(path[i] == 'R'){
                nx++;
            }
            else if(path[i] == 'L'){
                nx--;
            }

            if(0 <= nx && nx < 11 && 0 <= ny && ny < 11){
                if(!visited[x][y][nx][ny] || !visited[nx][ny][x][y]){
                    answer++;
                    visited[x][y][nx][ny] = true;
                    visited[nx][ny][x][y] = true;
                }
                x = nx;
                y = ny;
            }
        }
        return answer;
    }
}
