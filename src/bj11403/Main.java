package bj11403;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] graph;
    static Queue<Integer> queue;
    static int[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = new int[N][N];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++){
            bfs(i);
        }

        for (int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                bw.write(answer[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(int start){
        queue.clear();
        queue.offer(start);

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int i = 0; i < graph[now].length; i++){
                if (answer[start][i] == 0 && graph[now][i] == 1){
                    answer[start][i] = 1;
                    queue.offer(i);
                }
            }
        }
    }
}
