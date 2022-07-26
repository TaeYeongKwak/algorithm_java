package bj11400;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static ArrayList<Integer>[] graph;
    static int[] order;
    static int orderCnt = 1;
    static ArrayList<int[]> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        result = new ArrayList<>();

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
        for (int i = 1; i < V + 1; i++){
            if (order[i] == 0){
                dfs(i, 0);
            }
        }

        Collections.sort(result, (o1, o2) -> (o1[0] == o2[0])? o1[1] - o2[1] : o1[0] - o2[0]);

        bw.write(result.size() + "\n");
        for (int[] r : result){
            bw.write(r[0] + " " + r[1] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int now, int parent){
        order[now] = orderCnt++;
        int ret = order[now];

        for (int next : graph[now]){
            if (next == parent){
                continue;
            }

            if (order[next] == 0){
                int low = dfs(next, now);
                if (low > order[now]){
                    if (next > now){
                        result.add(new int[]{now, next});
                    }else{
                        result.add(new int[]{next, now});
                    }
                }
                ret = Math.min(low, ret);
            }
            else{
                ret = Math.min(ret, order[next]);
            }
        }

        return ret;
    }
}
