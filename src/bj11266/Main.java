package bj11266;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static ArrayList<Integer>[] graph;
    static int orderCnt = 1;
    static int[] order;
    static boolean[] isCut;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        order = new int[V + 1];
        isCut = new boolean[V + 1];

        for (int i = 1; i < V + 1; i++){
            if (order[i] == 0){
                dfs(i, true);
            }
        }

        int result = 0;
        for (int i = 1; i < V + 1; i++){
            if (isCut[i]){
                result++;
            }
        }
        bw.write(result + "\n");

        for (int i = 1; i < V + 1; i++){
            if (isCut[i]){
                bw.write(i + " ");
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

    static int dfs(int now, boolean isRoot){
        int child = 0;
        order[now] = orderCnt++;
        int ret = order[now];

        for (int next : graph[now]){
            //한번도 방문하지 않은 경우
            if (order[next] == 0){
                child++;
                int low = dfs(next, false);
                if (!isRoot && low >= order[now]){
                    isCut[now] = true;
                }
                ret = Math.min(low, ret);
            } 
            //이미 한번 방문한 경우
            else{
                ret = Math.min(ret, order[next]);
            }
        }

        if (isRoot && child >= 2){
            isCut[now] = true;
        }

        return ret;
    }
}
