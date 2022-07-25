package bj4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static float[][] stars;

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

        ArrayList<int[]> edgeList = new ArrayList<>();
        for (int i = 1; i < N + 1; i++){
            
        }

    }

}
