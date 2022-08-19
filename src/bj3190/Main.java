package bj3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, L;
    static boolean[][] map;
    static boolean[][] visited;
    static int result;
    static Map<Integer, Character> command;
    static Queue<int[]> tails;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = true;
        }

        result = Integer.MAX_VALUE;
        L = Integer.parseInt(br.readLine());
        command = new HashMap<>();

        for (int l = 0; l < L; l++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);

            command.put(time, d);
        }

        tails = new LinkedList<>();
        int[] start = {1, 1};
        tails.offer(start);
        move(0, 1, start);

        System.out.println(result);
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static void move(int time, int direction, int[] head){
        int x = head[1];
        int y = head[0];
        visited[y][x] = true;

        //다음에 이동할 위치 확인
        char d = command.getOrDefault(time, 'X');
        // 왼쪽
        if (d == 'L'){
            direction = (direction == 0)? 3 : direction - 1;
        }
        // 오른쪽
        else if (d == 'D'){
            direction = (direction == 3)? 0 : direction + 1;
        }

        int nx = x + dx[direction];
        int ny = y + dy[direction];
        int[] next = {ny, nx};

        // 탈출 조건
        if (0 >= nx || nx > N || 0 >= ny || ny > N || visited[ny][nx]){
            result = time + 1;
            return;
        }

        tails.offer(next);
        // 사과 확인
        // 사과가 없을 경우
        if (!map[ny][nx]){
            int[] tail = tails.poll();
            visited[tail[0]][tail[1]] = false;
        }
        // 사과가 있을 경우
        else{
            map[ny][nx] = false;
        }

        move(time + 1, direction, next);
    }
}
