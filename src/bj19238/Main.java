package bj19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] map;
    static int[] taxi;
    static List<Passenger> passengerList;
    static PriorityQueue<Passenger> pq;
    static Queue<int[]> queue;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        taxi = new int[2];
        st = new StringTokenizer(br.readLine());
        taxi[0] = Integer.parseInt(st.nextToken());
        taxi[1] = Integer.parseInt(st.nextToken());

        passengerList = new ArrayList<>();
        passengerList.add(new Passenger());
        passengerList.add(new Passenger());

        for (int i = 1; i < M + 1; i++){
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            passengerList.add(new Passenger(startY, startX, endY, endX));
            map[startY][startX] = i + 1;
        }

        pq = new PriorityQueue<>();
        queue = new LinkedList<>();
        visited = new boolean[N + 1][N + 1];

        getNearPassenger();
        System.out.println(K);
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void bfsInit(){
        queue.clear();
        pq.clear();
        for (int i = 1; i < N + 1; i++){
            Arrays.fill(visited[i], false);
        }
    }

    static void getNearPassenger(){
        bfsInit();
        visited[taxi[0]][taxi[1]] = true;
        queue.offer(new int[]{taxi[0], taxi[1], 0});
        int maxDist = 401;

        while(!queue.isEmpty()){
            int[] now = queue.poll();

            if (maxDist < now[2]){
                break;
            }

            if (map[now[0]][now[1]] > 1){
                Passenger passenger = passengerList.get(map[now[0]][now[1]]);
                if(passenger.isStartPoint(now)){
                    pq.offer(passenger);
                    maxDist = now[2];
                }
            }

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];

                if (0 < nx && nx < N + 1 && 0 < ny && ny < N + 1){
                    if (map[ny][nx] == 1 || visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    queue.offer(new int[]{ny, nx, now[2] + 1});
                }
            }
        }

        if (!pq.isEmpty()){
            if (K < maxDist){
                K = -1;
                return;
            }
            K -= maxDist;
            moveTaxiDestination(pq.poll());
        }
        else{
            if (M > 0){
                K = -1;
                return;
            }
        }
    }

    static void moveTaxiDestination(Passenger passenger){
        bfsInit();
        visited[passenger.startY][passenger.startX] = true;
        queue.offer(new int[]{passenger.startY, passenger.startX, 0});

        boolean check = false;
        while(!queue.isEmpty()){
            int[] now = queue.poll();

            if (now[2] > K){
                check = true;
                break;
            }

            if (passenger.isEndPoint(now)){
                taxi[0] = now[0];
                taxi[1] = now[1];
                K += now[2];
                M--;
                passenger.isArrive = true;
                break;
            }

            for (int i = 0; i < 4; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];

                if (0 < nx && nx < N + 1 && 0 < ny && ny < N + 1){
                    if (map[ny][nx] == 1 || visited[ny][nx]) continue;

                    visited[ny][nx] = true;
                    queue.offer(new int[]{ny, nx, now[2] + 1});
                }
            }
        }

        if (check || !passenger.isArrive){
            K = -1;
            return;
        }
        else if (M >= 1){
            getNearPassenger();
        }
    }

}

class Passenger implements Comparable<Passenger>{
    int startX;
    int startY;
    int endX;
    int endY;
    boolean isArrive;

    public Passenger(){
        this.startX = 0;
        this.startY = 0;
        isArrive = true;
    }

    public Passenger(int startY, int startX, int endY, int endX) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        isArrive = false;
    }

    @Override
    public int compareTo(Passenger o) {
        int x = Integer.compare(startY, o.startY);
        return (x == 0)? Integer.compare(startX, o.startX) : x;
    }

    public boolean isStartPoint(int[] point){
        return (!isArrive && point[0] == startY && point[1] == startX);
    }

    public boolean isEndPoint(int[] point){
        return (!isArrive && point[0] == endY && point[1] == endX);
    }
}
