package bj10775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int G, P;
    static int[] docking;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        docking = new int[G + 1];
        docking[0] = -1;

        int cnt = 0;
        for (int i = 1; i < P + 1; i++){
            int dg = Integer.parseInt(br.readLine());
            int nonDockingGateNum = find(dg);
            if (nonDockingGateNum == -1){
                break;
            }
            else{
                docking[nonDockingGateNum] = (nonDockingGateNum == 1)? 1 : nonDockingGateNum - 1;
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static int find(int x){
        if (x == docking[x]){
            return -1;
        }
        else if (docking[x] == 0){
            return x;
        }
        return docking[x] = find(docking[x]);
    }
}
