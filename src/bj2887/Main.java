package bj2887;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Planet> planetList = new ArrayList<>();
        ArrayList<int[]> edgeList = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planetList.add(new Planet(i, x, y, z));
        }

        Collections.sort(planetList, Comparator.comparingInt(p -> p.x));

        for (int i = 0; i < N - 1; i++){
            int planet1 = planetList.get(i).idx;
            int planet2 = planetList.get(i + 1).idx;
            int weight = Math.abs(planetList.get(i).x - planetList.get(i + 1).x);
            edgeList.add(new int[]{planet1, planet2, weight});
        }

        Collections.sort(planetList, Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < N - 1; i++){
            int planet1 = planetList.get(i).idx;
            int planet2 = planetList.get(i + 1).idx;
            int weight = Math.abs(planetList.get(i).y - planetList.get(i + 1).y);
            edgeList.add(new int[]{planet1, planet2, weight});
        }

        Collections.sort(planetList, Comparator.comparingInt(p -> p.z));

        for (int i = 0; i < N - 1; i++){
            int planet1 = planetList.get(i).idx;
            int planet2 = planetList.get(i + 1).idx;
            int weight = Math.abs(planetList.get(i).z - planetList.get(i + 1).z);
            edgeList.add(new int[]{planet1, planet2, weight});
        }

        Collections.sort(edgeList, Comparator.comparingInt(o -> o[2]));

        parent = new int[N];
        for (int i = 1; i < N; i++){
            parent[i] = i;
        }

        int result = 0;
        int cnt = 0;
        for (int[] edge : edgeList){
            if (find(edge[0]) == find(edge[1]))
                continue;

            result += edge[2];
            union(edge[0], edge[1]);
            cnt++;

            if (cnt == N - 1)
                break;
        }

        System.out.println(result);
    }

    static int find(int x){
        return parent[x] = (x == parent[x])? x : find(parent[x]);
    }

    static void union(int a, int b){
        parent[find(a)] = find(b);
    }
}

class Planet{
    int idx;
    int x;
    int y;
    int z;

    public Planet(int idx, int x, int y, int z) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
