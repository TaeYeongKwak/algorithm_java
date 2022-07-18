package bj1039;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static boolean[][] visited;
    static int result;
    static int n, k, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        m = (int)(Math.log10(n) + 1);

        visited = new boolean[1000001][11];
        result = -1;

        bfs(n, k);

        System.out.println(result);
    }

    static void bfs(int n, int k){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n, 0});
        visited[n][0] = true;

        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            if (temp[1] == k){
                result = Math.max(temp[0], result);
            }

            for (int i = 0; i < m; i++){
                for (int j = i + 1; j < m; j++){
                    int num = changeNum(i, j, temp[0]);
                    if (num != -1 && temp[1] + 1 < 11){
                        if (!visited[num][temp[1] + 1] && temp[1] <= k){
                            visited[num][temp[1] + 1] = true;
                            queue.offer(new int[]{num, temp[1] + 1});
                        }
                    }
                }
            }
        }
    }

    static int changeNum(int f, int b, int n){
        StringBuffer sb = new StringBuffer();
        sb.append(n);
        if (f == 0 && sb.charAt(b) == '0')
            return -1;
        char temp = sb.charAt(f);
        sb.setCharAt(f, sb.charAt(b));
        sb.setCharAt(b, temp);
        return Integer.parseInt(sb.toString());
    }
}
