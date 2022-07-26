package bj4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static float[][] stars;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        stars = new float[N + 1][2];
        StringTokenizer st;
        for (int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            stars[i][0] = x;
            stars[i][1] = y;
        }

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++){
            parent[i] = i;
        }

        ArrayList<float[]> edgeList = new ArrayList<>();
        for (int i = 1; i < N + 1; i++){
            for (int j = i + 1; j < N + 1; j++){
                edgeList.add(new float[]{i, j, distance(stars[i], stars[j])});
            }
        }

        Collections.sort(edgeList, (o1, o2) -> Float.compare(o1[2], o2[2]));

        float result = 0;
        int size = 0;
        for (float[] edge : edgeList){
            int aRoot = find((int) edge[0]);
            int bRoot = find((int) edge[1]);

            if (aRoot == bRoot) continue;

            result += edge[2];
            union((int) edge[0], (int) edge[1]);
            size++;

            if (size == N - 1) break;
        }

        System.out.println(result);
    }

    static float distance(float[] star1, float[] star2){
        float x2 = (float) Math.pow(star1[0] - star2[0], 2);
        float y2 = (float) Math.pow(star1[1] - star2[1], 2);
        return (float) Math.sqrt(x2 + y2);
    }

    static int find(int x){
        return parent[x] = (x == parent[x])? x : find(parent[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        parent[aRoot] = bRoot;
    }
}
