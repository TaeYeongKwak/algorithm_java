package bj15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int n, m;
    static List<int[]> chickenRestPoint;
    static List<int[]> house;
    static boolean[] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        chickenRestPoint = new ArrayList<>();
        house = new ArrayList<>();
        result = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++){
            String[] mapLine = br.readLine().split(" ");
            for (int j = 1; j <= n; j++){
                int mapType = Integer.parseInt(mapLine[j-1]);
                if (mapType == 2){
                    chickenRestPoint.add(new int[]{j, i});
                }else if (mapType == 1){
                    house.add(new int[]{j, i});
                }
            }
        }

        visited = new boolean[chickenRestPoint.size()];

        chickenClosure(0, chickenRestPoint.size());

        System.out.println(result);
    }

    static void chickenClosure(int p, int r){
        if (r == 0){
            return;
        }
        if (r <= m){
            int distance = distance();
            result = Math.min(result, distance);
        }
        for (int i = p; i < chickenRestPoint.size(); i++){
            if (!visited[i]){
                visited[i] = true;
                chickenClosure(i + 1, r - 1);
                visited[i] = false;
            }
        }
        return;
    }

    static int distance(){
        int cityDistance = 0;
        for (int[] housePoint : house){
            int minChickenDistance = Integer.MAX_VALUE;
            for (int i = 0; i < visited.length; i++){
                if (!visited[i]){
                    int[] cPoint = chickenRestPoint.get(i);
                    minChickenDistance = Math.min(Math.abs(cPoint[0] - housePoint[0]) + Math.abs(cPoint[1] - housePoint[1]), minChickenDistance);
                }
            }
            cityDistance += minChickenDistance;
        }
        return cityDistance;
    }
}
