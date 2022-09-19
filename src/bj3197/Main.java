package bj3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C;
    static boolean[][] isIce;
    static Queue<int[]> ice, lazyIce, water;
    static int[][] index;
    static int[] parent;
    static List<int[]> swans;
    static Queue<int[]> queue;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        isIce = new boolean[R + 1][C + 1];
        index = new int[R + 1][C + 1];
        ice = new LinkedList<>();
        water = new LinkedList<>();
        lazyIce = new LinkedList<>();
        swans = new ArrayList<>();

        int idx = 1;
        for (int r = 1; r < R + 1; r++){
            char[] mapLine = br.readLine().toCharArray();
            for (int c = 1; c < C + 1; c++){
                if (mapLine[c - 1] == 'X'){
                    isIce[r][c] = true;
                }
                else if (mapLine[c - 1] == 'L'){
                    swans.add(new int[]{r, c});
                }
                index[r][c] = idx++;
            }
        }

        parent = new int[idx];
        for (int i = 1; i < idx; i++){
            parent[i] = i;
        }

        queue = new LinkedList<>();
        visited = new boolean[R + 1][C + 1];
        init();
        System.out.println(meltIce());
    }

    static int find(int x){
        return parent[x] = (x == parent[x])? x : find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;
        parent[aRoot] = bRoot;
    }

    static void init(){
        for (int r = 1; r < R + 1; r++){
            for (int c = 1; c < C + 1; c++){
                if(!visited[r][c] && !isIce[r][c]){
                    unionBfs(new int[]{r, c});
                }
            }
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void unionBfs(int[] point){
        queue.clear();
        queue.offer(point);
        visited[point[0]][point[1]] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];

                if (0 < nx && nx < C + 1 && 0 < ny && ny < R + 1){
                    if (!isIce[ny][nx]) {
                        union(index[point[0]][point[1]], index[ny][nx]);
                    }
                    if (!isIce[ny][nx] && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.offer(new int[]{ny, nx});
                    }
                    else if (isIce[ny][nx] && !visited[ny][nx]){
                        visited[ny][nx] = true;
                        ice.offer(new int[]{ny, nx});
                    }
                }
            }
        }
    }

    static int meltIce(){
        int year = 0;
        for (int i = 1; i < R + 1; i++){
            Arrays.fill(visited[i], false);
        }

        while (!ice.isEmpty()){
            int[] icePoint = ice.poll();

            boolean flag = false;
            for (int i = 0; i < 4; i++){
                int nx = icePoint[1] + dx[i];
                int ny = icePoint[0] + dy[i];

                if (0 < nx && nx < C + 1 && 0 < ny && ny < R + 1){
                    if (!isIce[ny][nx]){
                        flag = true;
                    }

                    if (isIce[ny][nx] && !visited[ny][nx]){
                        visited[ny][nx] = true;
                        lazyIce.offer(new int[]{ny, nx});
                    }
                }
            }

            if (flag){
                water.offer(icePoint);
            }

            if (ice.isEmpty()){
                while(!lazyIce.isEmpty()){
                    ice.offer(lazyIce.poll());
                }

                while(!water.isEmpty()){
                    int[] waterPoint = water.poll();
                    isIce[waterPoint[0]][waterPoint[1]] = false;
                    for (int i = 0; i < 4; i++){
                        int nx = waterPoint[1] + dx[i];
                        int ny = waterPoint[0] + dy[i];

                        if (0 < nx && nx < C + 1 && 0 < ny && ny < R + 1){
                            if (!isIce[ny][nx]){
                                union(index[waterPoint[0]][waterPoint[1]], index[ny][nx]);
                            }
                        }
                    }
                }
                year++;

                if (check()){
                    break;
                }
            }
        }

        return year;
    }

    static boolean check(){
        int[] swan1 = swans.get(0);
        int[] swan2 = swans.get(1);

        return find(index[swan1[0]][swan1[1]]) == find(index[swan2[0]][swan2[1]]);
    }
}
