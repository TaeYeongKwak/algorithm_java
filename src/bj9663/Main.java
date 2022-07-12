package bj9663;

import java.util.Scanner;

public class Main {
    static int n;
    static int cnt;
    static int[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new int[n];
        cnt = 0;

        placeQueen(0);

        System.out.println(cnt);
    }

    static void placeQueen(int r){
        if(r == n){
            cnt++;
            return;
        }
        for (int i = 0; i < n; i++){
            visited[r] = i;
            if(check(r)){
                placeQueen(r + 1);
            }
        }
    }

    static boolean check(int s){
        for (int i = 0; i < s; i++) {
            if (visited[s] == visited[i]) {
                return false;
            }

            else if (Math.abs(s - i) == Math.abs(visited[s] - visited[i])) {
                return false;
            }
        }

        return true;
    }

}
