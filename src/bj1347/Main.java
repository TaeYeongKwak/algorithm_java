package bj1347;

import java.io.*;

public class Main {

    static BufferedWriter bw;
    static int N;
    static boolean[][] visited;

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[101][101];

        char[] commands = br.readLine().toCharArray();

        makeMaze(50, 50, commands);

        bw.flush();
        bw.close();
        br.close();
    }

    static void makeMaze(int x, int y, char[] commands) throws IOException {
        int maxX = x;
        int minX = x;
        int maxY = y;
        int minY = y;

        int direction = 0;
        int nx = x;
        int ny = y;
        visited[ny][nx] = true;
        for (char command : commands){
            if (command == 'F'){
                nx += dx[direction];
                ny += dy[direction];

                maxX = Math.max(maxX, nx);
                minX = Math.min(minX, nx);
                maxY = Math.max(maxY, ny);
                minY = Math.min(minY, ny);
                visited[ny][nx] = true;
            }
            else if (command == 'R'){
                direction = (direction > 2)? 0 : direction + 1;
            }
            else if (command == 'L'){
                direction = (direction < 1)? 3 : direction - 1;
            }
        }

        for (int i = minY; i <= maxY; i++){
            for (int j = minX; j <= maxX; j++){
                bw.write(visited[i][j]? '.' : '#');
            }
            bw.write("\n");
        }
    }
}
