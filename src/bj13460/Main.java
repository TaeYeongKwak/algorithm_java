package bj13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, result;
    static char[][] map;
    static Bead red;
    static Bead blue;
    static boolean[][][][] visited;
    static int[] end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = -1;
        visited = new boolean[N + 1][M + 1][N + 1][M + 1];

        map = new char[N + 1][M + 1];
        for (int n = 1; n < N + 1; n++){
            String mapLine = br.readLine();
            for (int m = 1; m < M + 1; m++){
                map[n][m] = mapLine.charAt(m - 1);
                if (map[n][m] == 'R'){
                    red = new Bead(m, n, 'R');
                    map[n][m] = '.';
                }
                else if (map[n][m] == 'B'){
                    blue = new Bead(m, n, 'B');
                    map[n][m] = '.';
                }
                else if(map[n][m] == 'O'){
                    end = new int[]{m, n};
                }
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(red, blue, 0, 'X'));

        while(!queue.isEmpty()){
            Node now = queue.poll();

            if (visited[now.red.y][now.red.x][now.blue.y][now.blue.x]) continue;
            visited[now.red.y][now.red.x][now.blue.y][now.blue.x] = true;

            if (now.blue.x == end[0] && now.blue.y == end[1]){
                result = -1;
                continue;
            }

            if (now.red.x == end[0] && now.red.y == end[1] && now.count <= 10){
                result = now.count;
                if (now.blue.x == end[0] && now.blue.y == end[1] || now.count > 10){
                    result = -1;
                    break;
                }
                break;
            }

            // left or right
            if (now.red.x < now.blue.x){
                red = left(now.red);
                blue = left(now.blue);
            }
            else{
                blue = left(now.blue);
                red = left(now.red);
            }
            map[red.y][red.x] = '.';
            map[blue.y][blue.x] = '.';
            map[end[1]][end[0]] = 'O';
            queue.offer(new Node(red, blue, now.count + 1, 'L'));

            if (now.red.x < now.blue.x){
                blue = right(now.blue);
                red = right(now.red);
            }
            else{
                red = right(now.red);
                blue = right(now.blue);
            }
            map[red.y][red.x] = '.';
            map[blue.y][blue.x] = '.';
            map[end[1]][end[0]] = 'O';
            queue.offer(new Node(red, blue, now.count + 1, 'R'));

            // top or bottom
            if (now.red.y < now.blue.y){
                red = top(now.red);
                blue = top(now.blue);
            }
            else{
                blue = top(now.blue);
                red = top(now.red);
            }
            map[red.y][red.x] = '.';
            map[blue.y][blue.x] = '.';
            map[end[1]][end[0]] = 'O';
            queue.offer(new Node(red, blue, now.count + 1, 'T'));

            if (now.red.y < now.blue.y){
                blue = bottom(now.blue);
                red = bottom(now.red);
            }
            else{
                red = bottom(now.red);
                blue = bottom(now.blue);
            }
            map[red.y][red.x] = '.';
            map[blue.y][blue.x] = '.';
            map[end[1]][end[0]] = 'O';
            queue.offer(new Node(red, blue, now.count + 1, 'B'));

        }

        System.out.println(result);
    }

    static Bead left(Bead bead){
        int nx = bead.x;
        boolean check = true;
        for (int x = bead.x - 1; x > 0; x--){
            if (map[bead.y][x] == 'O'){
                nx = x;
                break;
            }
            if (map[bead.y][x] == 'R' || map[bead.y][x] == 'B' || map[bead.y][x] == '#'){
                nx = x + 1;
                map[bead.y][nx] = bead.color;
                break;
            }
        }
        return new Bead(nx, bead.y, bead.color);
    }

    static Bead right(Bead bead){
        int nx = bead.x;
        for (int x = bead.x + 1; x < M + 1; x++){
            if (map[bead.y][x] == 'O'){
                nx = x;
                break;
            }
            if (map[bead.y][x] == 'R' || map[bead.y][x] == 'B' || map[bead.y][x] == '#'){
                nx = x - 1;
                map[bead.y][nx] = bead.color;
                break;
            }
        }
        return new Bead(nx, bead.y, bead.color);
    }

    static Bead top(Bead bead){
        int ny = bead.y;
        for (int y = bead.y - 1; y > 0; y--){
            if (map[y][bead.x] == 'O'){
                ny = y;
                break;
            }
            if (map[y][bead.x] == 'R' || map[y][bead.x] == 'B' || map[y][bead.x] == '#'){
                ny = y + 1;
                map[ny][bead.x] = bead.color;
                break;
            }
        }
        return new Bead(bead.x, ny, bead.color);
    }

    static Bead bottom(Bead bead){
        int ny = bead.y;
        for (int y = bead.y + 1; y < N + 1; y++){

            if (map[y][bead.x] == 'O'){
                ny = y;
                break;
            }
            if (map[y][bead.x] == 'R' || map[y][bead.x] == 'B' || map[y][bead.x] == '#'){
                ny = y - 1;
                map[ny][bead.x] = bead.color;
                break;
            }
        }
        return new Bead(bead.x, ny, bead.color);
    }
}

class Bead{
    int x;
    int y;
    char color;
    boolean check;

    public Bead(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}

class Node{
    Bead red;
    Bead blue;
    int count;
    char beforeMove;

    public Node(Bead red, Bead blue, int count, char beforeMove) {
        this.red = red;
        this.blue = blue;
        this.count = count;
        this.beforeMove = beforeMove;
    }
}
