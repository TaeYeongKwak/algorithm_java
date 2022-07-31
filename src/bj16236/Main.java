package bj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int size;
    static int eatCnt;
    static int[] sharkPoint;
    static int[][] sea;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        sea = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int y = 1; y < N + 1; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x < N + 1; x++){
                sea[y][x] = Integer.parseInt(st.nextToken());
                if (sea[y][x] == 9){
                    sharkPoint = new int[]{y, x};
                    sea[y][x] = 0;
                }
            }
        }

        size = 2;
        eatCnt = 0;

        System.out.println(bfs());
    }

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    static int bfs(){
        int result = 0;
        visited = new boolean[N + 1][N + 1];
        //물고기를 먹는 순서를 지키기 위해서 우선순위 큐를 사용
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(sharkPoint[1], sharkPoint[0], 0));
        visited[sharkPoint[0]][sharkPoint[1]] = true;

        while (!queue.isEmpty()){
            Edge now = queue.poll();

            // 해당 공간에 상어가 먹을 수 있는 물고기가 있을 경우
            if (1 <= sea[now.y][now.x] && sea[now.y][now.x] < size){
                //물고기를 먹었다면 해당 물고기를 먹느라 걸린 시간을 결과값으로 저장하고 시간을 초기화
                result += now.time;
                now.time = 0;

                // 먹은 물고기의 수를 올리고, 해당 타일에 물고기를 없앤다.
                eatCnt++;
                sea[now.y][now.x] = 0;

                // 만약 먹은 물고기의 숫자가 상어의 크기와 같다면 상어의 크기를 올려주고 물고기를 먹은 횟수를 0으로 초기화
                if (eatCnt == size){
                    size++;
                    eatCnt = 0;
                }

                // 지금까지의 방문기록을 초기화하고 지금 공간만 true로 변경
                for (int i = 1; i < N + 1; i++){
                    Arrays.fill(visited[i], false);
                }
                visited[now.y][now.x] = true;
                queue.clear();
            }

            for (int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 < nx && nx < N + 1 && 0 < ny && ny < N + 1){
                    if (!visited[ny][nx] && 0 <= sea[ny][nx] && sea[ny][nx] <= size){
                        visited[ny][nx] = true;
                        queue.offer(new Edge(nx, ny, now.time + 1));
                    }
                }
            }
        }

        return result;
    }
}

class Edge implements Comparable<Edge>{
    int x;
    int y;
    int time;

    public Edge(int x, int y, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public int compareTo(Edge o) {
        int i = Integer.compare(this.time, o.time);
        if (i == 0){
            int j = Integer.compare(this.y, o.y);
            if (j == 0){
                return Integer.compare(this.x, o.x);
            }
            return j;
        }
        return i;
    }
}
