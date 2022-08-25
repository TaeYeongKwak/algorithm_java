package bj1626;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static int[] parent;
    static int[][] edgeArray;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        edgeArray = new int[E][3];

        for (int e = 0; e < E; e++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeArray[e] = new int[]{from, to, weight};
        }

        Arrays.sort(edgeArray, Comparator.comparing(o -> o[2]));
        init();

        boolean flag = false;
        int edgeCnt = 0;
        int eIdx = 0;
        while (edgeCnt < V - 1){
            int a = edgeArray[eIdx][0];
            int b = edgeArray[eIdx][1];
            int w = edgeArray[eIdx][2];
            eIdx++;

            int aRoot = find(a);
            int bRoot = find(b);

            if (aRoot == bRoot && !flag){
                System.out.println("--------------------------------------------");
                flag = true;
                init();
                union(a, b);
                result = w;
                System.out.println(w);
                edgeCnt = 1;
                eIdx = 0;
            }
            else if (aRoot != bRoot){
                union(a, b);
                edgeCnt++;
                result += w;
                System.out.println(w);
            }


            if(eIdx >= E){
                result = -1;
                break;
            }
        }

        if (!flag){
            result = -1;
        }

        System.out.println(result);
    }

    static void init(){
        result = 0;
        for (int v = 1; v < V + 1; v++){
            parent[v] = v;
        }
    }

    static int find(int x){
        return parent[x] = (x == parent[x])? x : find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;

        if (aRoot > bRoot){
            parent[aRoot] = bRoot;
        }else{
            parent[bRoot] = aRoot;
        }
    }
}
