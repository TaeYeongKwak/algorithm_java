package bj9376;

import java.io.*;
import java.util.*;

public class Main {

    static int H, W;
    static char[][] map;
    static ArrayList<int[]> doorPath;
    static ArrayList<int[]> outPath;
    static ArrayList<int[]> personPath;
    static PriorityQueue<int[]> heap;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        doorPath = new ArrayList<>();
        outPath = new ArrayList<>();
        heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        personPath = new ArrayList<>();

        for (int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            dp = new int[H][W];

            doorPath.clear();
            outPath.clear();
            personPath.clear();

            for (int i = 0; i < H; i++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            for (int h = 0; h < H; h++){
                char[] mapLine = br.readLine().toCharArray();
                for (int w = 0; w < W; w++){
                    map[h][w] = mapLine[w];
                    // 양 끝쪽이면서 벽이 아닐경우
                    if ((0 == w || w == W - 1 || 0 == h || h == H - 1) && map[h][w] != '*'){
                        outPath.add(new int[]{h, w});
                    }
                    // 해당 좌표에 문이 있을 경우
                    if (map[h][w] == '#'){
                        doorPath.add(new int[]{h, w});
                    }
                    // 죄수가 있을 경우
                    else if (map[h][w] == '$'){
                        personPath.add(new int[]{h, w});
                    }
                }
            }

//            for (int[] outPoint : outPath){
//                dijkstra(outPoint);
//            }

            for (int[] personPoint : personPath){
                dijkstra(personPoint);
            }

            System.out.println("--------------------------------------------");
            for (int h = 0; h < H; h++){
                for (int w = 0; w < W; w++){
                    if (dp[h][w] == Integer.MAX_VALUE){
                        System.out.print(0 + " ");
                    }else{
                        System.out.print(dp[h][w] + " ");
                    }
                }
                System.out.println();
            }

            int[] person1 = personPath.get(0);
            int[] person2 = personPath.get(1);
            int result = Math.max(dp[person1[0]][person1[1]], dp[person2[0]][person2[1]]);
            System.out.println(dp[person1[0]][person1[1]] + " " + dp[person2[0]][person2[1]]);
//            bw.write(result + "\n");

        }

        bw.flush();
        bw.close();
        br.close();

    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void dijkstra(int[] start){
        heap.clear();
        int w = (map[start[0]][start[1]] == '#')? 1 : 0;
        heap.offer(new int[]{start[0], start[1], w});
        dp[start[0]][start[1]] = w;

        while(!heap.isEmpty()){
            int[] now = heap.poll();

            if (dp[now[0]][now[1]] < now[2]) continue;

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];
                if (0 <= nx && nx < W && 0 <= ny && ny < H && map[ny][nx] != '*'){
                    w = (map[ny][nx] == '#')? 1 : 0;
                    if (dp[ny][nx] > now[2] + w){
                        dp[ny][nx] = now[2] + w;
                        heap.offer(new int[]{ny, nx, now[2] + w});
                    }
                }
            }
        }
    }
}
