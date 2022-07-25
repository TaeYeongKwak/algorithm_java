package bj2887;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    }
}

class Planet{
    int x;
    int y;
    int z;

    public int getWeight(Planet p){
        return Math.min(Math.min(Math.abs(x - p.x), Math.abs(y - p.y)), Math.abs(z - p.z));
    }
}
